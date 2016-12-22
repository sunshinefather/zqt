package com.zyt.web.after.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyt.web.after.cms.service.IContentService;
import com.zyt.web.publics.base.AbstractServiceImpl;
import com.zyt.web.publics.base.BaseDAO;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.cms.bean.CmsAtt;
import com.zyt.web.publics.module.cms.bean.Content;
import com.zyt.web.publics.module.cms.dao.CmsAttDao;
import com.zyt.web.publics.module.cms.dao.ContentDao;
import com.zyt.web.publics.utils.UUIDUtils;

/**
 * @Description:内容管理业务层实现类
 * @ClassName:  ContentServiceImpl
 * @author: sunshine  
 */
 @Service("contentService")
public class ContentServiceImpl extends AbstractServiceImpl<Content> implements IContentService {
    
    @Resource
	private ContentDao contentDao;
    
    @Resource
    private CmsAttDao cmsAttDao;
	
	@Override
	public BaseDAO<Content> dao() {
		return contentDao;
	}
	
    @Override
	public void setId(Content t, String id) {
         t.setId(id);
	}
	
	@Override
	public Integer insert(Content content) {
		int j=0;
		List<CmsAtt> atts = content.getAtts();
		if(atts!=null && !atts.isEmpty()){
			for(CmsAtt att:atts){
				att.setId(UUIDUtils.getUUID());
				att.setContentId(content.getId());
				cmsAttDao.insert(att);
			}
		}
		
		j=super.insert(content);
		return j;
	}

	@Override
	public Integer update(Content content) {
		int j=0;
		List<CmsAtt> atts = content.getAtts();
		if(atts!=null && !atts.isEmpty()){
			CmsAtt _parament=new CmsAtt();
			_parament.setContentId(content.getId());
			List<CmsAtt> _atts =cmsAttDao.loadList(_parament);
			int ct =0;
			if(_atts!=null && !_atts.isEmpty()){
				ct=_atts.size();
				String[] ids=new String[ct]; 
				for(int i=0;i<ct;i++){
					ids[i]=_atts.get(i).getId();
				}
				cmsAttDao.delete(ids);
			}
			for(CmsAtt att:atts){
				att.setId(UUIDUtils.getUUID());
				att.setContentId(content.getId());
				cmsAttDao.insert(att);
			}
		}
		j=super.update(content);
		return j;
	}

	@Override
	public Content findObjectById(String id) {
		return super.findObjectById(id);
	}

	@Override
	public List<Content> findList(PaginationAble paginationAble) {
		return super.findList(paginationAble);
	}

	@Override
	public List<Content> queryList(Content content) {
		return super.queryList(content);
	}

	@Override
	public Integer delete(String[] ids) {
		return super.delete(ids);
	}

	@Override
	public Integer validate(Content content) {
		return super.validate(content);
	}
}
