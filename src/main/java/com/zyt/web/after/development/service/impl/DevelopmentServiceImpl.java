package com.zyt.web.after.development.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyt.web.after.development.service.IDevelopmentService;
import com.zyt.web.publics.base.AbstractServiceImpl;
import com.zyt.web.publics.base.BaseDAO;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.development.bean.Development;
import com.zyt.web.publics.module.development.dao.DevelopmentDao;

/**
 * @Description:企业发展管理业务层实现类
 * @ClassName:  DevelopmentServiceImpl
 * @author: sunshine  
 */
 @Service("developmentService")
public class DevelopmentServiceImpl extends AbstractServiceImpl<Development> implements IDevelopmentService {
    
    @Resource
	private DevelopmentDao developmentDao;
	
	@Override
	public BaseDAO<Development> dao() {
		return developmentDao;
	}
	
    @Override
	public void setId(Development t, String id) {
         t.setId(id);
	}
	
	@Override
	public Integer insert(Development development) {
		return super.insert(development);
	}

	@Override
	public Integer update(Development development) {
		return super.update(development);
	}

	@Override
	public Development findObjectById(String id) {
		return super.findObjectById(id);
	}

	@Override
	public List<Development> findList(PaginationAble paginationAble) {
		return super.findList(paginationAble);
	}

	@Override
	public List<Development> queryList(Development development) {
		return super.queryList(development);
	}

	@Override
	public Integer delete(String[] ids) {
		return super.delete(ids);
	}

	@Override
	public Integer validate(Development development) {
		return super.validate(development);
	}

	@Override
	public List<Development> getYearExport(Map<String,String> map) {
		return developmentDao.getYearExport(map);
	}
}
