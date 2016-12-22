package com.zyt.web.after.sysmanager.service;

import java.util.List;
import java.util.Map;

import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.sysmanager.bean.Resource;
import com.zyt.web.publics.module.sysmanager.bean.Role;
import com.zyt.web.publics.module.sysmanager.bean.User;

/**
 * 
 * @author LiuChuang
 * @description 操作角色业务层
 * @version 1.0
 * @date 2014-3-11
 */
public interface IRoleService {
	/**
	 * 
	 *@Description: 根据区域查询角色列表，当区域为空并且该用户是系统管理员时，查询出所有的角色
	 *@param region 区域对象
	 *@return List<Role> 角色列表
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-11下午2:34:15
	 * 
	 * Modification History:
	 *     Date		      Author		   Version		Description
	 * ---------------------------------------------------------*
	 * 2014-3-11	     LiuChuang		v1.0.0		   新建
	 */
	List<Role> findList(PaginationAble paginationAble,User user);
	
	/**
	 * 
	 *@Description: 保存角色
	 *@param role
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-11下午2:35:27
	 * 
	 * Modification History:
	 *     Date		      Author		   Version		Description
	 * ---------------------------------------------------------*
	 * 2014-3-11	     LiuChuang		v1.0.0		   新建
	 */
	Integer save(Role role);
	
	
	/**
	 * 
	 *@Description: 保存角色与用户组关联关系
	 *@param role
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-19下午3:32:18
	 */
	Integer saveRoleGroupLink(Role role);
	
	/**
	 * 
	 *@Description: 保存角色与资源关联关系
	 *@param role
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-19下午3:33:52
	 */
	Integer saveRoleResourceLink(Role role);
	
	/**
	 * 
	 *@Description: 删除角色
	 *@param role
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-11下午2:36:12
	 * 
	 * Modification History:
	 *     Date		      Author		   Version		Description
	 * ---------------------------------------------------------*
	 * 2014-3-11	     LiuChuang		v1.0.0		   新建
	 */
	Integer delete(Role role);
	
	/**
	 * 
	 *@Description: 更新角色
	 *@param role
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-11下午2:36:55
	 * 
	 * Modification History:
	 *     Date		      Author		   Version		Description
	 * ---------------------------------------------------------*
	 * 2014-3-11	     LiuChuang		v1.0.0		   新建
	 */
	Integer update(Role role);
	
	/**
	 *@Description: 查询所有的角色
	 *@return List<Role>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-17上午9:34:30
	 */
	List<Role> findAllRoles();
	
	/**
	 * 
	 *@Description: 根据ID查询角色
	 *@param roleId
	 *@return Role
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-19下午1:26:17
	 */
	Role findRoleById(String roleId);
	
	/**
	 * 
	 *@Description: 转换角色状态
	 *@param state
	 *@param ids
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-19下午1:34:42
	 */
	Integer transform(Integer state, String	 ...ids);
	
	/**
	 * 
	 *@Description: 删除角色与用户关联关系
	 *@param role
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-19下午3:51:54
	 */
	Integer deleteRoleGroupLink(Role role);
	
	/**
	 * 
	 *@Description: 批量删除角色
	 *@param split
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-19下午4:43:57
	 */
	Integer deleteBatch(String[] split);
	
	/**
	 * 
	 *@Description: 根据权限获取已经绑定的资源
	 *@param role
	 *@return List<Resource>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-20下午6:00:41
	 */
	List<Resource> getResourcesByRole(Role role);
	
	/**
	 * 
	 *@Description: 根据用户查询角色列表
	 *@param user
	 *@return List<Role>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-24下午5:29:37
	 */
	List<Role> findRolesByUser(User user);
	
	/**
	 * 
	 *@Description: 通过AJAX的方式搜索角色
	 *@param roleName
	 *@param regionId
	 *@param user
	 *@return List<Role>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-7-10下午3:05:27
	 */
	List<Role> search(Map<String, Object> param, User user);
}
