package com.zyt.web.after.api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyt.web.after.api.service.RegionApiService;
import com.zyt.web.after.sysmanager.service.IRegionService;
import com.zyt.web.publics.base.JsonEntity;
import com.zyt.web.publics.module.sysmanager.bean.Region;

@Service
public class RegionApiServiceImpl implements RegionApiService{
	@Autowired
	private IRegionService regionService;
	
	@Override
	public JsonEntity getRegion(String parentId) {
		JsonEntity jsonEntity = new JsonEntity();
		List<Region> list = null;
		if(StringUtils.isNotBlank(parentId)){
			list = regionService.getRegionByParents(parentId);
		}else{
		   list = regionService.findList(false);
		}
		if(list != null){
			List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
			for (Region region : list) {
				if(StringUtils.isNotBlank(parentId) && parentId.equals(region.getId())){
					continue;
				}
				Map<String,Object> data = new HashMap<String, Object>();
				data.put("regionId",region.getId());
				data.put("regionName",region.getRegionName());
				data.put("parentId",region.getParentId());
				data.put("parentIds",region.getFormatCode());
				List<Region> child = regionService.getRegionByParentId(region.getId(),null);
				data.put("hasChild",child==null?false:child.size()==0?false:true);
				result.add(data);
			}
			jsonEntity.setData(result);
		}
		return jsonEntity;
	}

	@Override
	public JsonEntity getRegionByParentId(String parentId,String regionName) {
		JsonEntity jsonEntity = new JsonEntity();
		if(StringUtils.isBlank(parentId)){
			Region region = regionService.getRegionByName("成都市");
			parentId = region.getParentId();
		}
		List<Region> list = regionService.getRegionByParentId(parentId,regionName);
		if(list != null){
			List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
			for (Region region : list) {
				Map<String,Object> data = new HashMap<String, Object>();
				data.put("regionId",region.getId());
				data.put("regionName",region.getRegionName());
				data.put("parentId",region.getParentId());
				data.put("parentIds",region.getFormatCode());
				List<Region> child = regionService.getRegionByParentId(region.getId(),null);
				data.put("hasChild",child==null?false:child.size()==0?false:true);
				result.add(data);
			}
			jsonEntity.setData(result);
		}
		return jsonEntity;
	}
	
}
