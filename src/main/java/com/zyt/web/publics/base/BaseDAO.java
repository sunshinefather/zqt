package com.zyt.web.publics.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
/**
 * 
 * @ClassName:  BaseDAO   
 * @Description:TODO   
 * @author: sunshine  
 * @date:   2014年6月23日 上午12:08:42
 * @param <T>
 */
public interface BaseDAO<T> {
	/**
	 * 分页查询
	 * @param condition
	 * @param rowBounds
	 * @return
	 */
	public List<T> findList(PaginationAble paginationAble,RowBounds row);
	
	/**
	 * 查询列表,常用于导出
	 * @param condition
	 * @param rowBounds
	 * @return
	 */
	public List<T> loadList(T t);

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
	 * 更具ID查询
	 * @param id
	 * @return
	 */

	public T findObjectById(@Param("id")String id);
	
	/**
	 * 删除
	 * @param ids
	 */
	public Integer delete(String[] ids);
	
	/**
	 * 验证重复用于Ajax
	 * @param t
	 * @return
	 */
	public Integer validate (T t);
	
	public 	Integer maxid();
}
