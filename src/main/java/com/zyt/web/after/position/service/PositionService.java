package com.zyt.web.after.position.service;

import java.util.List;

import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.position.bean.Position;
import com.zyt.web.publics.module.sysmanager.bean.User;

public interface PositionService {
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
	public int delete(String [] ids);
	
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
	 *@param user
	 *@return List<Position>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月20日下午5:15:03
	 */
	public List<Position> findList(PaginationAble paginationAble, User user);
	
	/**
	 * 
	 *@Description: 根据ID查询岗位
	 *@param id
	 *@return Position
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月22日下午4:21:58
	 */
	public Position getById(String id);
	
	/**
	 * 
	 *@Description: 根据组织机构查询岗位
	 *@param orgIds
	 *@return List<Position> 岗位列表
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月23日下午1:46:37
	 */
	public List<Position> getByOrgIds(String [] orgIds);
	
	/**
	 * 
	 *@Description: 根据用户查询此用户所在单位的所有的岗位
	 *@param user
	 *@return List<Position>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月24日上午11:14:27
	 */
	public List<Position> getByUser(User user);
}
