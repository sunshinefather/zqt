package com.zyt.web.after.support.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyt.web.after.support.service.IObtainSupportService;
import com.zyt.web.publics.base.AbstractServiceImpl;
import com.zyt.web.publics.base.BaseDAO;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.support.bean.ObtainSupport;
import com.zyt.web.publics.module.support.dao.ObtainSupportDao;

/**
 * @Description:企业获得支持业务层实现类
 * @ClassName:  ObtainSupportServiceImpl
 * @author: sunshine  
 */
 @Service("obtainSupportService")
public class ObtainSupportServiceImpl extends AbstractServiceImpl<ObtainSupport> implements IObtainSupportService {
    
    @Resource
	private ObtainSupportDao obtainSupportDao;
	
	@Override
	public BaseDAO<ObtainSupport> dao() {
		return obtainSupportDao;
	}
	
    @Override
	public void setId(ObtainSupport t, String id) {
         t.setId(id);
	}
	
	@Override
	public Integer insert(ObtainSupport obtainSupport) {
		return super.insert(obtainSupport);
	}

	@Override
	public Integer update(ObtainSupport obtainSupport) {
		return super.update(obtainSupport);
	}

	@Override
	public ObtainSupport findObjectById(String id) {
		return super.findObjectById(id);
	}

	@Override
	public List<ObtainSupport> findList(PaginationAble paginationAble) {
		return super.findList(paginationAble);
	}

	@Override
	public List<ObtainSupport> queryList(ObtainSupport obtainSupport) {
		return super.queryList(obtainSupport);
	}

	@Override
	public Integer delete(String[] ids) {
		return super.delete(ids);
	}

	@Override
	public Integer validate(ObtainSupport obtainSupport) {
		return super.validate(obtainSupport);
	}
}
