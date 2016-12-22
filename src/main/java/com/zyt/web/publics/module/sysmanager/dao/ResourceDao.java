package com.zyt.web.publics.module.sysmanager.dao;

import java.util.List;
import java.util.Map;

import com.zyt.web.publics.module.sysmanager.bean.Resource;

/**
 * @author 系统资源
 */
public interface ResourceDao {

	/**
	 * 
	 *@Description: 根据条件获取资源
	 *@param params
	 *@return List<Resource>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年3月27日上午9:57:06
	 */
	public List<Resource> getResources(Map<String, Object> params);

	/**
	 * @描述 TODO 根据Id获取资源信息
	 * @author maliang
	 * @time 2014-3-11 下午3:01:09
	 * @version
	 * @param resourceId
	 * @return
	 */
	public Resource getResourceById(String resourceId);

	/**
	 * @描述 TODO 添加系统资源
	 * @author maliang
	 * @time 2014-3-12 上午10:33:14
	 * @version
	 * @param resource
	 * @return
	 */
	public int insertResource(Resource resource);

	/**
	 * @描述 TODO 根据Id删除资源信息
	 * @author maliang
	 * @time 2014-3-12 下午4:27:08
	 * @version
	 * @param resourceId
	 * @return
	 */
	public int deleteResourceById(String resourceId);

	/**
	 * 获取角色对应的资源
	 * 
	 * @Title: findResourceByRole
	 * @Description: 
	 * @param: @param params({"roles":""},{"display":""})
	 * @param: @return
	 * @return: List<Resource>
	 * @throws <p>
	 *         实现根据角色ID获取对应的资源信息
	 */
	public List<Resource> findResourceByRoleIds(Map<String, Object> params);

	/**
	 * @描述:TODO 获取角色对应的资源信息
	 * @auto:maliang
	 * @time:2014-3-19
	 * @param roleId
	 * @return
	 */
	public List<Resource> findResourceByRoleId(String roleId);

	/**
	 * @描述 TODO 根据较色信息获取前面两级菜单
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
	 * 更新资源
	 * @Title: updateResource   
	 * @Description: 
	 * @param: @param resource
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int updateResource(Resource resource);
}
