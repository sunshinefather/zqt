package com.zyt.web.publics.module.sysmanager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.sysmanager.bean.Group;

/**
 * 
 * @author LiuChuang
 * @description 操作用户组表
 * @version 1.0
 * @date 2014-3-12
 */
public interface GroupDao {
	/**
	 * 
	 *@Description: 获取<T>列表
	 *@param paginationAble
	 *@return List<T>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-12上午9:30:30
	 * 
	 * Modification History:
	 *     Date		      Author		   Version		Description
	 * ---------------------------------------------------------*
	 * 2014-3-12	     LiuChuang		v1.0.0		   新建
	 */
	List<Group> findList(PaginationAble paginationAble,RowBounds row);
	
	/**
	 * 
	 *@Description: 保存<T>
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
	 *@Description: 保存用户组实体与角色实体之间的关联
	 *@param entity
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-12下午1:55:46
	 * 
	 * Modification History:
	 *     Date		      Author		   Version		Description
	 * ---------------------------------------------------------*
	 * 2014-3-12	     LiuChuang		v1.0.0		   新建
	 */
	Integer saveGroupRoleLink(Group entity);
	
	/**
	 * 
	 *@Description: 删除<T>
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
	 * Role
	 *@Description: 删除用户组实体与角色实体之间的关联
	 *@param entity
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-12下午1:37:47
	 * 
	 * Modification History:
	 *     Date		      Author		   Version		Description
	 * ---------------------------------------------------------*
	 * 2014-3-12	     LiuChuang		v1.0.0		   新建
	 */
	Integer deleteGroupRoleLink(Group entity);
	
	/**
	 * 
	 *@Description: 更新<T>
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
	 *@Description: 根据权限获取用户组列表
	 *@param param
	 *@return List<T>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-18上午11:52:54
	 */
	List<Group> findGroupList(Map<String, Object> param);
	
	/**
	 * 
	 *@Description: 根据ID查询用户组
	 *@param id
	 *@return T
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-19下午5:58:23
	 */
	Group findGroupById(String id);
	
	/**
	 * 
	 *@Description: 转换用户组状态
	 *@param state
	 *@param ids
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-20上午9:11:42
	 */
	Integer transform(@Param("state")Integer state, @Param("ids")String[] ids);
	
	/**
	 * 
	 *@Description: 批量删除用户组
	 *@param ids
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-20上午9:14:47
	 */
	Integer deleteBatch(@Param("ids")String[] ids);
	/**
	 * 根据区域Id查询改区域下所有用户组
	 * @param regionId 区域id
	 * @return
	 */
	List<Group> findGroupByRegion(String regionId);
	
}
