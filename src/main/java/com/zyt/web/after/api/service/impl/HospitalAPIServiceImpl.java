package com.zyt.web.after.api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zyt.web.after.api.service.HospitalAPIService;
import com.zyt.web.after.config.service.SysConfigService;
import com.zyt.web.after.hospital.service.HospitalService;
import com.zyt.web.after.sysmanager.service.IUserService;
import com.zyt.web.publics.base.JsonEntity;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.config.bean.SysConfig;
import com.zyt.web.publics.module.hospital.bean.Hospital;
import com.zyt.web.publics.module.sysmanager.bean.User;

/**
 * 
 * @author LiuChuang
 * @description 组织机构接口服务层
 * @version 1.0
 * @date 2015年4月16日
 */
@Service
public class HospitalAPIServiceImpl implements HospitalAPIService{
	@Autowired
	private HospitalService hospitalService;
	@Autowired
	private IUserService userService;
	@Autowired
	private SysConfigService sysConfgService;
	
	@Override
	public String getChildHospitalByUserId(String token) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("status", 0);
		if(StringUtils.isNotBlank(token)){
			 ServletContext sc = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getServletContext();
			 Object obj= sc.getAttribute(token);
			if(obj != null && obj instanceof User){
				User user = (User)obj;
				String organizationId = user.getExtUser().getOrgId();
				if(StringUtils.isNotBlank(organizationId)){
					Hospital organization = hospitalService.getById(organizationId);
					List<Hospital> organizations = new ArrayList<Hospital>();
					if(organization != null){
						if (StringUtils.isBlank(organization.getParentsId())) //顶级机构
							organizations = hospitalService.getChildOrganizationById(organizationId);
						else {//非顶级
							String tempIds[] = organization.getParentsId().split(","); 
							organizationId = tempIds[tempIds.length -1];
							organizations = hospitalService.getChildOrganizationById(organizationId);
						}
						List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
						for (Hospital org : organizations) {
							Map<String,Object> data = new HashMap<String,Object>();
							data.put("orgId",org.getHospitalId());
							data.put("orgName",org.getHospitalName());
							data.put("hashChild",org.getHasChild());
							datas.add(data);
						}
						result.put("status",1);
						result.put("data", datas);
					}
				}
			}else{
				result.put("msg","用户登录已失效！");
			}
		}else{
			result.put("msg","参数错误！");
		}
		return JSON.toJSONString(result);
	}

	@Override
	public String getChildHospitalById(String orgId) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("status", 0);
		
		if(StringUtils.isNotBlank(orgId)){
			List<Hospital> organizations = hospitalService.getChildOrganizationById(orgId);
			List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
			for (Hospital organization : organizations) {
				Map<String,Object> data = new HashMap<String,Object>();
				data.put("orgId",organization.getHospitalId());
				data.put("orgName",organization.getHospitalName());
				data.put("hashChild",organization.getHasChild());
				datas.add(data);
			}
			result.put("status",1);
			result.put("data", datas);
		}else{
			result.put("msg","参数错误");
		}
		return JSON.toJSONString(result);
	}

	@Override
	public String getFirstLayer() {
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			List<Hospital> organizations = hospitalService.getFirstLayer();
			
			List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
			if(organizations != null){
				for (Hospital organization : organizations) {
					Map<String,Object> data = new HashMap<String,Object>();
					data.put("orgId",organization.getHospitalId());
					data.put("orgName",organization.getHospitalName());
					data.put("hashChild",organization.getHasChild());
					datas.add(data);
				}
			}
			result.put("status",1);
			result.put("data",datas);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg","系统异常");
			result.put("status",0);
		}
		return JSON.toJSONString(result);
	}

	@Override
	public JsonEntity findAll(PaginationAble page,User users) {
		JsonEntity jsonEntity = new JsonEntity();
		
		List<Hospital> list = hospitalService.findAll(page);
		
		List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
		for (Hospital hospital : list) {
			Map<String,Object> data = new HashMap<String, Object>();
			data.put("companyId",hospital.getHospitalId());
			data.put("companyName",hospital.getHospitalName());
			datas.add(data);
		}
		page.setResults(datas);
		jsonEntity.setData(page);
		return jsonEntity;
	}

	@Override
	public JsonEntity findById(String hospitalId) {
		JsonEntity jsonEntity = new JsonEntity();
		if(StringUtils.isNotBlank(hospitalId)){
			Map<String,Object> configMap = new HashMap<String, Object>();
			
			SysConfig sysConfig = sysConfgService.getConfigById("grade");
			if(sysConfig != null){
				String value = sysConfig.getConfigValue();
				if("json".equalsIgnoreCase(sysConfig.getConfigType()) && StringUtils.isNotBlank(value)){
					JSONObject jsonObject = JSONObject.parseObject(value);
					for (String k : jsonObject.keySet()) {
						configMap.put(k, jsonObject.get(k));
					}
				}
			}
			
			Map<String,Object> data = new HashMap<String,Object>();
			Hospital hospital = hospitalService.getById(hospitalId);
			data.put("hospitalId",hospital.getHospitalId());
			data.put("hospitalName",hospital.getHospitalName());
			data.put("introduction",hospital.getIntroduction());
			data.put("logo",hospital.getLogoImage() != null?hospital.getLogoImage().getWebAdd():"");
			data.put("address", hospital.getAddress());
			data.put("lat",hospital.getLat());
			data.put("lng",hospital.getLng());
			data.put("grade",configMap.get(hospital.getGrade()));
			data.put("contactPhone",hospital.getContactPhone());
			jsonEntity.setData(data);
		}else{
			jsonEntity.setStatus(JsonEntity.paramerror);
			jsonEntity.setMsg("参数不正确");
		}
		return jsonEntity;
	}
	
}
