package com.zyt.web.after.sysmanager.service;

import java.io.Serializable;
import java.util.List;

import com.zyt.web.publics.module.sysmanager.bean.Region;

/**
 * 区域业务层
 * 
 * @ClassName: IRegionService
 * @Description:
 * @author: sunshine
 * @date: 2014年3月12日 上午11:01:59
 */
public interface IRegionService {
	/**
	 * 查询所有区域数据
	 * 
	 * @param paginationAble
	 *            分页封装参数
	 * @return 区域数据集合
	 */
	List<Region> findList(boolean isgetRoot);

	/**
	 * 保存更新区域数据
	 * 
	 * @param region
	 * @return
	 */
	int save(Region region);

	/**
	 * 删除区域数据
	 * 
	 * @param region
	 *            区域数据
	 * @return
	 */
	int delete(Region region);

	/**
	 * 获取单条区域数据
	 * 
	 * @param id
	 * @return获取区域数据
	 */
	Region getRegionById(Serializable id);

	/**
	 * 
	 * @Title: getRegionsByUserId
	 * @Description: 获取用户区域及子区域
	 * @param userId
	 * @return: List<Region>
	 * @throws
	 */
	List<Region> getRegionsByUserId(String userId);

	/**
	 * 根据用户Id获取用户所在的组所属于的区域和子属区域
	 * 
	 * @param userId
	 *            用户组
	 * @return
	 */
	String[] getChildRegionsByUserId(String userId);


	/**
	 * 根据id获取该区域和该区域下所有区域
	 * 
	 * @param id
	 *            区域id
	 * @return List<Region>
	 */
	List<Region> getRegionByParents(String id);
	
	/**
	 * 
	 *@Description: 根据父级ID查询子级第一级行政区划
	 *@param parentId
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年7月28日上午11:43:09
	 */
	List<Region> getRegionByParentId(String parentId,String regionName);
	
	/**
	 * 
	 *@Description: 根据行政区划名称查询
	 *@param regionName
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年7月29日下午2:13:39
	 */
	Region getRegionByName(String regionName);
}
