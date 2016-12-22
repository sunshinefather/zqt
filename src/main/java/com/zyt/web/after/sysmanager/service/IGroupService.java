package com.zyt.web.after.sysmanager.service;

import java.util.List;

import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.sysmanager.bean.Group;
import com.zyt.web.publics.module.sysmanager.bean.User;

/**
 * 
 * @author LiuChuang
 * @description 操作用户组业务层
 * @version 1.0
 * @date 2014-3-12
 */
public interface IGroupService {
	/**
	 * 
	 *@Description: 获取<Group>列表
	 *@param paginationAble
	 *@return List<Group>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-12上午9:30:30
	 * 
	 * Modification History:
	 *     Date		      Author		   Version		Description
	 * ---------------------------------------------------------*
	 * 2014-3-12	     LiuChuang		v1.0.0		   新建
	 */
	List<Group> findList(PaginationAble paginationAble,User user);
	
	/**
	 * 
	 *@Description: 保存<Group>
	 *@param entity
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-12上午9:30:45
	 * 
	 * Modification History:
	 *     Date		      Author		   Version		Description
	 * ---------------------------------------------------------*
	 * 2014-3-12	     LiuChuang		v1.0.0		   新建
	 */
	Integer save(Group entity);
	
	/**
	 * 
	 *@Description: 删除<Group>
	 *@param entity
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-12上午9:31:42
	 * 
	 * Modification History:
	 *     Date		      Author		   Version		Description
	 * ---------------------------------------------------------*
	 * 2014-3-12	     LiuChuang		v1.0.0		   新建
	 */
	Integer delete(Group entity);
	
	/**
	 * 
	 *@Description: 更新<Group>
	 *@param entity
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-12上午9:31:54
	 * 
	 * Modification History:
	 *     Date		      Author		   Version		Description
	 * ---------------------------------------------------------*
	 * 2014-3-12	     LiuChuang		v1.0.0		   新建
	 */
	Integer update(Group entity);
	
	/**
	 * 
	 *@Description: 根据权限获取用户组
	 *@param userId
	 *@return List<Group>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-18上午10:47:44
	 */
	List<Group> findGroupList(String userId);
	
	/**
	 * 
	 *@Description: 根据ID查询用户组
	 *@param id
	 *@return Group
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-19下午5:57:38
	 */
	Group findGroupById(String id);
	
	/**
	 * 
	 *@Description: 保存用户组与角色关联关系
	 *@param entity
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-19下午6:03:17
	 */
	Integer saveGroupRoleLink(Group group);
	
	/**
	 * 
	 *@Description: 删除用户组与角色之间的关联关系
	 *@param group
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-19下午6:12:35
	 */
	Integer deleteGroupRoleLink(Group group);
	
	/**
	 * 
	 *@Description: 转换用户组状态
	 *@param state
	 *@param ids
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-20上午9:10:52
	 */
	Integer transform(Integer state, String[] ids);
	
	/**
	 * 
	 *@Description: 批量删除用户组
	 *@param ids
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-20上午9:14:09
	 */
	Integer deleteBatch(String[] ids);
	/**
	 * 根据区域Id查询改区域下所有用户组
	 * @param regionId 区域id
	 * @return
	 */
	List<Group> findGroupByRegion(String regionId);
	
}
