package com.zyt.web.publics.module.sysmanager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.sysmanager.bean.Role;
import com.zyt.web.publics.module.sysmanager.bean.User;

/**
 * 
 * @author LiuChuang
 * @description 操作角色表数据DAO层
 * @version 1.0
 * @date 2014-3-11
 */
public interface RoleDao {
	/**
	 * 
	 *@Description: 获取区域下的角色，当区域为空时，获取所有角色
	 *@param region 行政化区域对象
	 *@return List<T> 角色实体列表
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-11上午10:56:31
	 * 
	 * Modification History:
	 *     Date		      Author		   Version		Description
	 * ---------------------------------------------------------*
	 * 2014-3-11	     LiuChuang		v1.0.0		   新建
	 */
	List<Role> findList(PaginationAble paginationAble,RowBounds row);
	
	/**
	 * 
	 *@Description: 保存角色实体
	 *@param entity
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-11上午10:57:31
	 * 
	 * Modification History:
	 *     Date		      Author		   Version		Description
	 * ---------------------------------------------------------*
	 * 2014-3-11	     LiuChuang		v1.0.0		   新建
	 */
	Integer save(Role entity);
	
	/**
	 * 
	 *@Description: 保存角色实体与用户组实体之间的关系
	 *@param entity
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-12下午1:41:33
	 * 
	 * Modification History:
	 *     Date		      Author		   Version		Description
	 * ---------------------------------------------------------*
	 * 2014-3-12	     LiuChuang		v1.0.0		   新建
	 */
	Integer saveRoleGroupLink(Role entity);
	
	/**
	 * 
	 *@Description: 删除角色实体
	 *@param entity
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-11上午10:57:58
	 * 
	 * Modification History:
	 *     Date		      Author		   Version		Description
	 * ---------------------------------------------------------*
	 * 2014-3-11	     LiuChuang		v1.0.0		   新建
	 */
	Integer delete(Role entity);
	
	/**
	 * 
	 *@Description: 删除角色实体与用户组实体之间的关联
	 *@param entity
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-12下午1:32:30
	 * 
	 * Modification History:
	 *     Date		      Author		   Version		Description
	 * ---------------------------------------------------------*
	 * 2014-3-12	     LiuChuang		v1.0.0		   新建
	 */
	Integer deleteRoleGroupLink(Role entity);
	
	/**
	 * 
	 *@Description: 更新角色实体
	 *@param entity
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-11上午10:58:27
	 * 
	 * Modification History:
	 *     Date		      Author		   Version		Description
	 * ---------------------------------------------------------*
	 * 2014-3-11	     LiuChuang		v1.0.0		   新建
	 */
	Integer update(Role entity);
	/**
	 * 获取用户对应的角色
	 * @Title: findRolesByUser   
	 * @Description: 
	 * @param: @param user
	 * @param: @return      
	 * @return: List<Role>      
	 * @throws
	 */
	List<Role> findRolesByUser(User user);
	
	/**
	 * 
	 *@Description: 获取系统所有角色
	 *@return List<Role>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-17上午9:29:20
	 * 
	 * Modification History:
	 *     Date		      Author		   Version		Description
	 * ---------------------------------------------------------*
	 * 2014-3-17	     LiuChuang		v1.0.0		   新建
	 */
	List<Role> findAllRoles();
	
	/**
	 * 
	 *@Description: 根据ID查询角色实体
	 *@param roleId
	 *@return T
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-19下午1:27:40
	 */
	Role findRoleById(String roleId);
	
	/**
	 * 
	 *@Description: 转换用户状态
	 *@param state
	 *@param ids
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-19下午1:35:47
	 */
	Integer transform(@Param("state")Integer state, @Param("ids")String ...ids);
	
	/**
	 * 
	 *@Description: 保存角色与资源关联关系
	 *@param entity
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-19下午3:34:51
	 */
	Integer saveRoleResourceLink(Role entity);
	
	/**
	 * 
	 *@Description: 批量删除角色
	 *@param ids
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-19下午4:45:05
	 */
	Integer deleteBatch(@Param("ids")String[] ids);
	
	
	/**
	 * 
	 *@Description: 删除角色与资源的关联关系
	 *@param entity
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-20下午5:14:06
	 */
	Integer deleteRoleResourceLink(Role entity);
	
	/**
	 * 
	 *@Description: 搜索角色
	 *@param roleName
	 *@param regionId
	 *@param orgId
	 *@return List<Role>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-7-10下午3:06:40
	 */
	List<Role> search(@Param("param")Map<String,Object> param);
}
