package com.zyt.web.publics.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
/**
 * 
 * @ClassName:  AbstractServiceImpl   
 * @Description:TODO   
 * @author: sunshine  
 * @date:   2014年6月23日 上午12:08:27
 * @param <T>
 * @param <DAO>
 */
public abstract class AbstractServiceImpl<T> implements BaseService<T> {

	public abstract BaseDAO<T> dao();
	
    public abstract void setId(T t,String id);
    
	@Override
	public Integer insert(T t) {
		/*
		Integer id=dao().maxid();
		if(null==id){
			id=0;
		}
		setId(t,""+(id+1));
		*/
		return dao().insert(t);
	}

	@Override
	public Integer update(T t) {
		return dao().update(t);
	}

	@Override
	public T findObjectById(String id) {
		return dao().findObjectById(id);
	}

	@Override
	public List<T> findList(PaginationAble paginationAble) {
		return dao().findList(paginationAble,new RowBounds(paginationAble.getCurrentResult(), paginationAble.getPageSize()));
		
	}
    
	@Override
	public List<T> queryList(T t) {
		return dao().loadList(t);
	}
	@Override
	public Integer delete(String[] ids) {
		return dao().delete(ids);
		
	}

	@Override
	public Integer validate(T t) {
		return dao().validate(t);
	}
}
