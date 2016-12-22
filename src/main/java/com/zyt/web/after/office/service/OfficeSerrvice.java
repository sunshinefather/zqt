package com.zyt.web.after.office.service;

import java.util.List;

import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.office.bean.Office;
import com.zyt.web.publics.module.sysmanager.bean.User;

public interface OfficeSerrvice {
	/**
	 * 
	 *@Description: 保存
	 *@param office
	 *@return int
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月20日下午5:09:51
	 */
	public int save(Office office);
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
	 *@param office
	 *@return int
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月20日下午5:13:57
	 */
	public int update(Office office);
	/**
	 * 
	 *@Description: 分页查询岗位信息
	 *@param paginationAble
	 *@param user
	 *@return List<Office>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月20日下午5:15:03
	 */
	public List<Office> findList(PaginationAble paginationAble, User user);
	
	/**
	 * 
	 *@Description: 根据ID查询职务
	 *@param id
	 *@return Office
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月22日下午4:21:58
	 */
	public Office getById(String id);
	
	/**
	 * 
	 *@Description: 根据组织机构查询职务
	 *@param orgIds
	 *@return List<Office> 职务列表
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月23日下午1:46:37
	 */
	public List<Office> getByOrgIds(String [] orgIds);
	
	/**
	 * 
	 *@Description: 根据用户查询此用户所在单位的所有的职务
	 *@param user
	 *@return List<Office>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月24日上午11:14:27
	 */
	public List<Office> getByUser(User user);
}
