package com.zyt.web.after.agreement.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyt.web.after.agreement.service.IAgreementService;
import com.zyt.web.publics.base.AbstractServiceImpl;
import com.zyt.web.publics.base.BaseDAO;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.agreement.bean.Agreement;
import com.zyt.web.publics.module.agreement.dao.AgreementDao;

/**
 * @Description:协议约定业务层实现类
 * @ClassName:  AgreementServiceImpl
 * @author: sunshine  
 */
 @Service("agreementService")
public class AgreementServiceImpl extends AbstractServiceImpl<Agreement> implements IAgreementService {
    
    @Resource
	private AgreementDao agreementDao;
	
	@Override
	public BaseDAO<Agreement> dao() {
		return agreementDao;
	}
	
    @Override
	public void setId(Agreement t, String id) {
         t.setId(id);
	}
	
	@Override
	public Integer insert(Agreement agreement) {
		return super.insert(agreement);
	}

	@Override
	public Integer update(Agreement agreement) {
		return super.update(agreement);
	}

	@Override
	public Agreement findObjectById(String id) {
		return super.findObjectById(id);
	}

	@Override
	public List<Agreement> findList(PaginationAble paginationAble) {
		return super.findList(paginationAble);
	}

	@Override
	public List<Agreement> queryList(Agreement agreement) {
		return super.queryList(agreement);
	}

	@Override
	public Integer delete(String[] ids) {
		return super.delete(ids);
	}

	@Override
	public Integer validate(Agreement agreement) {
		return super.validate(agreement);
	}
}
