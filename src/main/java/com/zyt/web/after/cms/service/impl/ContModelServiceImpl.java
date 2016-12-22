package com.zyt.web.after.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyt.web.after.cms.service.IContModelService;
import com.zyt.web.publics.base.AbstractServiceImpl;
import com.zyt.web.publics.base.BaseDAO;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.cms.bean.ContModel;
import com.zyt.web.publics.module.cms.dao.ContModelDao;
import com.zyt.web.publics.utils.UUIDUtils;

/**
 * @Description:内容分类业务层实现类
 * @ClassName:  ContModelServiceImpl
 * @author: sunshine  
 */
 @Service("contModelService")
public class ContModelServiceImpl extends AbstractServiceImpl<ContModel> implements IContModelService {
    
    @Resource
	private ContModelDao contModelDao;
	
	@Override
	public BaseDAO<ContModel> dao() {
		return contModelDao;
	}
	
    @Override
	public void setId(ContModel t, String id) {
         t.setId(id);
	}
	
	@Override
	public Integer insert(ContModel contModel) {
		contModel.setId(UUIDUtils.getUUID());
		return super.insert(contModel);
	}

	@Override
	public Integer update(ContModel contModel) {
		return super.update(contModel);
	}

	@Override
	public ContModel findObjectById(String id) {
		return super.findObjectById(id);
	}

	@Override
	public List<ContModel> findList(PaginationAble paginationAble) {
		return super.findList(paginationAble);
	}

	@Override
	public List<ContModel> queryList(ContModel contModel) {
		return super.queryList(contModel);
	}

	@Override
	public Integer delete(String[] ids) {
		return super.delete(ids);
	}

	@Override
	public Integer validate(ContModel contModel) {
		return super.validate(contModel);
	}
}
