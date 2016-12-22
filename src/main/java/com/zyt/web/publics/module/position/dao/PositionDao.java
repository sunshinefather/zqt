package com.zyt.web.publics.module.position.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.position.bean.Position;

/**
 * 
 * @author LiuChuang
 * @description 岗位DAO层
 * @version 1.0
 * @date 2015年4月20日
 */
public interface PositionDao {
	/**
	 * 
	 *@Description: 保存
	 *@param position
	 *@return int
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月20日下午5:09:51
	 */
	public int save(Position position);
	/**
	 * 
	 *@Description: 批量删除
	 *@param ids
	 *@return int
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月20日下午5:13:22
	 */
	public int delete(@Param("ids")String [] ids);
	
	/**
	 * 
	 *@Description: 更新岗位
	 *@param position
	 *@return int
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月20日下午5:13:57
	 */
	public int update(Position position);
	/**
	 * 
	 *@Description: 分页查询岗位信息
	 *@param paginationAble
	 *@param row
	 *@return List<Position>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月20日下午5:15:03
	 */
	public List<Position> findList(PaginationAble paginationAble,RowBounds row);
	
	/**
	 * 
	 *@Description: 根据ID查询岗位
	 *@param id
	 *@return Position
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月22日下午4:23:58
	 */
	public Position getById(String id);
	
	/**
	 * 
	 *@Description: 根据组织机构查询岗位
	 *@param orgIds
	 *@return List<Position>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月23日下午6:49:50
	 */
	public List<Position> getByOrgIds(@Param("orgIds")String[] orgIds);
	/**
	 * 
	 *@Description: 根据用户权限获取岗位
	 *@param params
	 *@return List<Position>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月24日上午11:54:07
	 */
	public List<Position> getByUser(Map<String, Object> params);
}
