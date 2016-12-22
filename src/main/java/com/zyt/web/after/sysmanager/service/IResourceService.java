package com.zyt.web.after.sysmanager.service;

import java.util.List;
import java.util.Map;

import com.zyt.web.publics.module.sysmanager.bean.Resource;
import com.zyt.web.publics.module.sysmanager.bean.Role;

/**
 * 
 * @author maliang 目前标注过时的方法，为未启用的方法
 * 
 */
public interface IResourceService {

	/**
	 * 
	 *@Description: 获取系统资源
	 *@param resourceType 资源类型
	 *@param project 资源所属项目标识
	 *@return List<Resource>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年3月26日下午5:59:37
	 */
	public List<Resource> getResources(String resourceType,String projects);


	/**
	 * @描述 TODO 根据ID获取
	 * @author maliang
	 * @time 2014-3-12 下午5:04:04
	 * @version
	 * @param resourceId
	 * @return
	 */
	public Resource getResourceById(String resourceId);

	/**
	 * @描述 TODO 添加系统资源,如果添加成功将这个资源添加到缓存中
	 * @author maliang
	 * @time 2014-3-12 上午10:30:15
	 * @version
	 * @param resource
	 * @return
	 */
	public Map<String, Object> insertResource(Resource resource);

	/**
	 * @描述 TODO 根据ID删除资源信息
	 * @author maliang
	 * @time 2014-3-12 下午4:26:30
	 * @version
	 * @param resourceId
	 * @return
	 */
	public Map<String, Object> deleteResourceById(String resourceId);

	/**
	 * 
	 *@Description: 根据角色ID获取对应的资源信息
	 *@param roles 角色列表
	 *@param resourceType 资源类型
	 *@param projects 项目标识
	 *@return List<Resource>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年3月27日上午9:53:59
	 */
	public List<Resource> findResourceByRoleIds(List<Role> roles, String resourceType,String projects);

	/**
	 * @描述 TODO 根据角色ID获取资源信息
	 * @author maliang
	 * @time 2014-3-20 下午4:00:40
	 * @version v1.0
	 * @param roleId
	 * @return
	 */
	public List<Resource> findResourceByRoleId(String roleId);

	/**
	 * @描述 TODO 根据较色信息获取一级菜单
	 * @author maliang
	 * @time 2014-3-20 上午10:49:45
	 * @version v1.0
	 * @param roleId
	 * @return
	 */
	public List<Resource> findFirstLevelResourceByRoleId(String roleId);

	/**
	 * @描述 TODO 根据资源Id 获取下级的所有资源信息
	 * @author maliang
	 * @time 2014-3-20 上午10:51:07
	 * @version v1.0
	 * @param resourceId
	 * @return
	 */
	public List<Resource> findNextLevelResourceByResourceId(String resourceId);
	/**
	 * 修改系统资源
	 * @Title: updateResource   
	 * @Description: 
	 * @param: @param resource
	 * @param: @return      
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public Map<String, Object> updateResource(Resource resource);

}
