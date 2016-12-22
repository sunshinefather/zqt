package com.zyt.web.after.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyt.web.after.product.service.IProductService;
import com.zyt.web.publics.base.AbstractServiceImpl;
import com.zyt.web.publics.base.BaseDAO;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.product.bean.Product;
import com.zyt.web.publics.module.product.dao.ProductDao;

/**
 * @Description:企业产品业务层实现类
 * @ClassName:  ProductServiceImpl
 * @author: sunshine  
 */
 @Service("productService")
public class ProductServiceImpl extends AbstractServiceImpl<Product> implements IProductService {
    
    @Resource
	private ProductDao productDao;
	
	@Override
	public BaseDAO<Product> dao() {
		return productDao;
	}
	
    @Override
	public void setId(Product t, String id) {
         t.setId(id);
	}
	
	@Override
	public Integer insert(Product product) {
		return super.insert(product);
	}

	@Override
	public Integer update(Product product) {
		return super.update(product);
	}

	@Override
	public Product findObjectById(String id) {
		return super.findObjectById(id);
	}

	@Override
	public List<Product> findList(PaginationAble paginationAble) {
		return super.findList(paginationAble);
	}

	@Override
	public List<Product> queryList(Product product) {
		return super.queryList(product);
	}

	@Override
	public Integer delete(String[] ids) {
		return super.delete(ids);
	}

	@Override
	public Integer validate(Product product) {
		return super.validate(product);
	}
}
