package com.zyt.web.after.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyt.web.after.api.service.RegionApiService;
import com.zyt.web.publics.base.JsonEntity;

/**
 * 
 * @author Kevin
 * @description 行政区划接口控制器
 * @version 1.0
 * @date 2015年7月28日
 */
@Controller
@RequestMapping("/api/region/")
public class ApiRegionController extends ApiController {
	@Autowired
	private RegionApiService regionApiService;
	
	/**
	 * 
	 *@Description: 获取行政区划数据
	 *@param parentId
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年7月28日上午11:07:21
	 */
	@RequestMapping(value = "getRegion",method=RequestMethod.GET)
	public @ResponseBody JsonEntity getRegion(String parentId){
		
		return regionApiService.getRegion(parentId);
	}
	
	/**
	 * 
	 *@Description: 根据父级ID查询子级第一级行政区划
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年7月28日上午11:34:16
	 */
	@RequestMapping(value = "getRegionByParentId",method=RequestMethod.POST)
	public @ResponseBody JsonEntity getRegionByParentId(String parentId,String regionName){
		
		return regionApiService.getRegionByParentId(parentId,regionName);
	}
}
