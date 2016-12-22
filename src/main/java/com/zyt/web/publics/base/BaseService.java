package com.zyt.web.publics.base;

import java.util.List;

import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
/**
 * 
 * @ClassName:  BaseService   
 * @Description:TODO   
 * @author: sunshine  
 * @date:   2014年6月23日 上午12:08:50
 * @param <T>
 */
public interface BaseService<T> {
	
	/**
	 * 插入
	 * @param t
	 */
	public Integer insert(T t);
	
	/**
	 * 修改
	 * @param t
	 */
	public Integer update(T t);
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public T findObjectById(String id);
	
	/**
	 * 分页查询(带条件)
	 * @param paginationAble
	 * @return
	 */
	public List<T> findList(PaginationAble paginationAble);
	
	/**
	 * 不分页查询(带条件)
	 * @param t
	 * @return
	 */
	public List<T> queryList(T t);
	
	/**
	 *  删除(支持批量删除)
	 * @param ids
	 * @return
	 */
	public Integer delete(String[] ids);
	
	/**
	 * Ajax校验
	 * @param t
	 * @return
	 */
	public Integer validate(T t);
}
