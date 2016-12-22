package com.zyt.web.publics.base.mybatis;


import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;

/**
 * 对基础的SqlSessionDaoSupport 进行包装
 * @author Hadoop
 * TODO 
 */
public class UltraSqlSessionDaoSuport<T> extends SqlSessionDaoSupport {
	@Autowired
	public SqlSessionTemplate sqlSession;
	
	public List<T> paginationAndSort(String statement,PaginationAble paginationAble){
		return this.getSqlSession().selectList(statement,paginationAble.getWhereParameters(),new RowBounds(paginationAble.getCurrentResult(), paginationAble.getPageSize()));
	}
	
}
