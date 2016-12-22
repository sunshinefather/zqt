package com.zyt.web.after.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyt.web.after.cms.service.ICmsAttService;
import com.zyt.web.publics.base.AbstractServiceImpl;
import com.zyt.web.publics.base.BaseDAO;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.cms.bean.CmsAtt;
import com.zyt.web.publics.module.cms.dao.CmsAttDao;

/**
 * @Description:cms附件业务层实现类
 * @ClassName:  CmsAttServiceImpl
 * @author: sunshine  
 */
 @Service("cmsAttService")
public class CmsAttServiceImpl extends AbstractServiceImpl<CmsAtt> implements ICmsAttService {
    
    @Resource
	private CmsAttDao cmsAttDao;
	
	@Override
	public BaseDAO<CmsAtt> dao() {
		return cmsAttDao;
	}
	
    @Override
	public void setId(CmsAtt t, String id) {
         t.setId(id);
	}
	
	@Override
	public Integer insert(CmsAtt cmsAtt) {
		return super.insert(cmsAtt);
	}

	@Override
	public Integer update(CmsAtt cmsAtt) {
		return super.update(cmsAtt);
	}

	@Override
	public CmsAtt findObjectById(String id) {
		return super.findObjectById(id);
	}

	@Override
	public List<CmsAtt> findList(PaginationAble paginationAble) {
		return super.findList(paginationAble);
	}

	@Override
	public List<CmsAtt> queryList(CmsAtt cmsAtt) {
		return super.queryList(cmsAtt);
	}

	@Override
	public Integer delete(String[] ids) {
		return super.delete(ids);
	}

	@Override
	public Integer validate(CmsAtt cmsAtt) {
		return super.validate(cmsAtt);
	}
}
