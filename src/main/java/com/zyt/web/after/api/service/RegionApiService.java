package com.zyt.web.after.api.service;

import com.zyt.web.publics.base.JsonEntity;

public interface RegionApiService {
	
	/**
	 * 
	 *@Description: 获取行政区划
	 *@param parentId 行政区划ID
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年7月28日上午11:11:06
	 */
	JsonEntity getRegion(String parentId);
	
	/**
	 * 
	 *@Description: 根据父级ID查询子级第一级行政区划
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 * @param regionName 
	 *@date: 2015年7月28日上午11:36:03
	 */
	JsonEntity getRegionByParentId(String parentId, String regionName);
	
}
