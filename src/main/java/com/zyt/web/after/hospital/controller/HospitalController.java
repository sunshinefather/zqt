package com.zyt.web.after.hospital.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyt.web.after.config.service.SysConfigService;
import com.zyt.web.after.hospital.service.HospitalService;
import com.zyt.web.after.sysmanager.service.IRegionService;
import com.zyt.web.after.sysmanager.service.IUserService;
import com.zyt.web.publics.base.BaseController;
import com.zyt.web.publics.base.JsonEntity;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.config.bean.SysConfig;
import com.zyt.web.publics.module.hospital.bean.Hospital;
import com.zyt.web.publics.module.sysmanager.bean.Region;
import com.zyt.web.publics.module.sysmanager.bean.User;
import com.zyt.web.publics.utils.JSONUtils;

@Controller
@RequestMapping(value = "after/hospital")
public class HospitalController extends BaseController {

	@Autowired
	private HospitalService hospitalService;
	
	@Autowired
	IRegionService regionService;
	
	@Autowired
	IUserService userService;
	
	@Resource
	private SysConfigService sysConfigService;
	
	@RequestMapping(value = "/index")
	public String index() {
		return "after/hospital/list";
	}

	@RequestMapping("/listorgan")
	public String list(Model model,PaginationAble page) {
		User user = getUser();
		page.setWhereParameters(getParams(model));
		Map<String,Object> params = page.getWhereParameters();
		params.put("userType", user.getType());
		if(!"0".equals(user.getType())){
			Hospital organization = hospitalService.getById(user.getExtUser().getOrgId());
			model.addAttribute("obj", organization);
			return "after/hospital/add";
		}else{
			List<Hospital> list =  hospitalService.findListByPage(page,user);
			model.addAttribute("list", list);
			model.addAttribute("page",page);
			return "after/hospital/query";
		}
	}

	@RequestMapping(value = "/listByRegion")
	public String listByRegion(Model model,String regionId) {
		if(StringUtils.isEmpty(regionId)){
			regionId="0";
		}
		List<Hospital> list = null;
		User user = getUser();
		User user2 = userService.findUserById(user.getId());
		if(user2.getType().equals("200")){//机构用户
			list = hospitalService.getOrganizationByParentsId(user2.getExtUser().getOrgId());//查询所有机构下的子机构
			List<Hospital> listhh = hospitalService
					.findOrganizationByRegoinId(regionId);
			if(listhh !=null && listhh.size()>0){
				for (int k = list.size()-1; k >=0; k--) {
					Integer i = 0;
					for (int j = 0; j < listhh.size(); j++) {
						if(listhh.get(j).getHospitalId().equals(list.get(k).getHospitalId())){
							i++;
						}
					}
					if(i<=0){
						list.remove(k);
					}
				}
				model.addAttribute("list", list);
			}else{
			}
		}else{
			list = hospitalService.findOrganizationByRegoinId(regionId);
			model.addAttribute("list", list);
		}
		return "after/hospital/query";
	}

	@RequestMapping("/getOrgionByUserId")
	public void getRegionsByUserId(HttpServletResponse respose) {
		User user = getUser();
		Map<String, Object> params = new HashMap<String, Object>();
		List<Hospital> list= null;
		params.put("type", user.getType());
			if ("100".equals(user.getType())) {// 区域用户
			String[] regionIds = regionService.getChildRegionsByUserId(user
					.getId());
			params.put("regionIds", regionIds);
		} else
			params.put("userId", user.getId());
		list = hospitalService.findList(params);
		JSONUtils.Success(list, respose);
	}
	
	@RequestMapping("/getOrgionByRegOrParentId")
	public void getOrgionByRegOrOrg(String regionId,String parentId,
			HttpServletResponse respose) {
		List<Hospital> list =  null;
		if("0".equals(parentId) || parentId == null){
			User user = getUser();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("type", user.getType());
			if ("100".equals(user.getType())) {// 区域用户
				String[] regionIds = regionService.getChildRegionsByUserId(user
						.getId());
				params.put("regionIds", regionIds);
			} else
			list = hospitalService.getByParentId(parentId);
		}else{
			list= hospitalService.getByParentId(parentId);
			list.add(hospitalService.getById(parentId));
		}
		JSONUtils.Success(list, respose);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model,Hospital organ) throws Exception {
		  hospitalService.save(organ);
		return "redirect:listorgan";
	}

	@RequestMapping(value = "/add")
	public String add(Model model) {
		List<String> list=new ArrayList<String>();
		SysConfig scf = sysConfigService.getConfigById("industry_categorys");
		if(scf!=null && StringUtils.isNotEmpty(scf.getConfigValue())){
			String[] objs= scf.getConfigValue().split(",");
			for(String obj: objs){
				String[] str= obj.split(":");
				if(str.length==2){
					list.add(str[1].replaceAll("\"",""));
				}
			}
		}
		model.addAttribute("listcaty",list);
		return "after/hospital/add";
	}

	@RequestMapping(value = "/edit/{id}")
	public String query(Model model, @PathVariable String id) {
		List<String> list=new ArrayList<String>();
		SysConfig scf = sysConfigService.getConfigById("industry_categorys");
		if(scf!=null && StringUtils.isNotEmpty(scf.getConfigValue())){
			String[] objs= scf.getConfigValue().split(",");
			for(String obj: objs){
				String[] str= obj.split(":");
				if(str.length==2){
					list.add(str[1].replaceAll("\"",""));
				}
			}
		}
		model.addAttribute("listcaty",list);
		Hospital organization = hospitalService.getById(id);
		model.addAttribute("obj", organization);
		return "after/hospital/add";
	}

	/**
	 * 修改
	 * 
	 * @param hospital
	 * @param regoin
	 * @param contactName
	 * @param contactPhone
	 * @param gender
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updatedata(Hospital hospital,HttpServletRequest request,Model model) throws Exception {
		hospitalService.updateHospital(hospital);
		return "after/hospital/list";
	}

	/**
	 * 删除
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public JsonEntity delete(String ids) throws IOException {
		JsonEntity js= new JsonEntity();
		boolean rt=hospitalService.deleteByIds(ids.split("`"));
		js.setCode(rt?1:0);
		return js;
	}
	
	@RequestMapping("/getRegionByOrgId")
	public void getRegionByOrgId(String orgId, HttpServletResponse respose) {
		String id = hospitalService.getById(orgId).getRegionId();
		List<Region> list = null ;
		list = regionService.getRegionByParents(id);
		JSONUtils.Success(list, respose);
	}
	
	@RequestMapping("/contrastOrganization")
	public void getOrganizatioByRegionId(String regId,String orgId, HttpServletResponse respose) {
		List<Hospital> list = hospitalService.findOrganizationByRegoinId(regId);
		Boolean i = false;
		for (Hospital organization : list) {
			if(organization.getHospitalId().equals(orgId)){
				i=true;
			}
		}
		if(i == false){
			JSONUtils.Success("1", respose);
		}
	}
	
	/**
	 * 
	 *@Description: 验证区域0是否属于区域1
	 *@param regionId0 区域ID
	 *@param regionId1 区域ID
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年6月25日上午11:25:11
	 */
	@RequestMapping(value="/valid",method=RequestMethod.GET)
	public @ResponseBody boolean valid(String regionId0,String regionId1){
		boolean result = false;
		if(StringUtils.isNotBlank(regionId0) && StringUtils.isNotBlank(regionId1)){
			List<Region>  list =  regionService.getRegionByParents(regionId1);
			for (Region region : list) {
				if(regionId0.equals(region.getId())){
					result = true;
					break;
				}
			}
		}
		return result;
	}
	
	/**
	 * 
	 *@Description: 验证编码是否唯一
	 *@param hospitalCode
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年10月14日下午1:27:17
	 */
	@RequestMapping(value="/valid/{hospitalCode}",method=RequestMethod.GET)
	public @ResponseBody boolean validCode(@PathVariable("hospitalCode") String hospitalCode,String hospitalId){
		return hospitalService.validCode(hospitalCode,hospitalId);
	}
	
	/**
	 * 
	 *@Description: 打开模式窗口
	 *@param model
	 *@param page
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年10月15日上午10:23:43
	 */
	@RequestMapping("/modalList")
	public String modalList(Model model,PaginationAble page) {
		User user = getUser();
		// 根据用户类型获取获取数据（如果为区域用户，那么需要获取区域以及子属区域下的所有机构（包括子属机构），如果为机构用户那么需要获取机构下和子属机构，如果为特殊用户那么获取全部数据）
		page.setWhereParameters(getParams(model));
		Map<String,Object> params = page.getWhereParameters();
		params.put("userType", user.getType());
		Object regionId = page.getWhereParameters().get("regionId");
		if(regionId != null){//根据行政区域树进行筛选
			List<Region> list = regionService.getRegionByParents(regionId.toString());
			params.put("limitRegions",list);
		}
		if ("100".equals(user.getType())) {// 区域用户
			String[] regionIds = regionService.getChildRegionsByUserId(user.getId());
			params.put("regionIds", regionIds);
		}else if(Integer.valueOf(user.getType()) >= 200 && Integer.valueOf(user.getType()) < 300 ){//机构用户
			String[] orgIds = hospitalService.getChildOrgsByUserId(user.getId());
			params.put("orgIds",orgIds);
		}
		List<Hospital> list =  hospitalService.findListByPage(page,user);
		model.addAttribute("list", list);
		model.addAttribute("page",page);
		return "after/hospital/modalList";
	}
	
}
