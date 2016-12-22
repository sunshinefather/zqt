package com.zyt.web.after.sysmanager.controller;

import httl.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zyt.web.after.hospital.service.HospitalService;
import com.zyt.web.after.sysmanager.service.IGroupService;
import com.zyt.web.after.sysmanager.service.IRegionService;
import com.zyt.web.after.sysmanager.service.IUserService;
import com.zyt.web.publics.base.BaseController;
import com.zyt.web.publics.module.hospital.bean.Hospital;
import com.zyt.web.publics.module.sysmanager.bean.Group;
import com.zyt.web.publics.module.sysmanager.bean.Region;
import com.zyt.web.publics.module.sysmanager.bean.User;
import com.zyt.web.publics.utils.HibernateValidatorFactoryUtils;
import com.zyt.web.publics.utils.JSONUtils;

/**
 * 区域控制器
 * 
 * @ClassName: RegionController
 * @Description:
 * @author: sunshine
 * @date: 2014年3月12日 下午1:35:28
 */
@Controller
@RequestMapping(value = "/after/region")
public class RegionController extends BaseController{
	@Autowired
	IRegionService regionService;
	@Autowired
	IGroupService groupService;
	@Autowired
	IUserService userService;
	@Autowired
	HospitalService hospitalService;

	@RequestMapping(value = "/index")
    public String index(){
    	return "after/sysmanger/region/index";
    }
	
	
	@RequestMapping("/listregion")
	public void list(HttpServletRequest request,
			HttpServletResponse respose) {
		List<Region> list=regionService.findList(true);
		JSONUtils.Success(list, respose);
	}

	@RequestMapping("/saveregion")
	public void save(Region region, HttpServletRequest request,
			HttpServletResponse respose) throws Exception {
		int sa = 0;
		StringBuilder str = new StringBuilder();
		Map<String,Object> map =HibernateValidatorFactoryUtils.verifyObject(region);
		if(map==null || map.size()==0){
			String id=region.getId();
			sa = regionService.save(region);
			if (sa == 0) {
				if (StringUtils.isEmpty(id)) {
					str.append("保存失败");
				} else {
					str.append("更新失败");
				}
				JSONUtils.Failure(str.toString(), respose);
			} else {
				JSONUtils.Success(region, respose);
			}
		}
		else {
			int ct = 0;
			for (Object msg : map.values()) {
				if (ct++ != 0) {
					str.append(",");
				}
				str.append(msg.toString());
			}
			JSONUtils.Failure(str.toString(), respose);
		}
	}

	@RequestMapping("/removeregion")
	public void remove(Region region, HttpServletRequest request,
			HttpServletResponse respose) throws Exception {
		int sa = 0;
		String str="";
		List<Group> groups = groupService.findGroupByRegion(region.getId());
		if(groups.size()>0){
			for (Group group : groups) {
				str +=  group.getGroupName()+",";
			}
			str =  "删除失败！<br/>该区域下有用户组：<br/>"+str.substring(0, str.length()-1);
			JSONUtils.Failure(str.toString(), respose);
		}else{
			sa = regionService.delete(region);
			if (sa == 0) {
				str = "删除失败";
				JSONUtils.Failure(str.toString(), respose);
			} else {
				str = "删除成功";
				JSONUtils.Success(str.toString(), respose);
			}
		}
	}
	
	@RequestMapping("/getRegionsByUserId")
	public void getRegionsByUserId(String userId,HttpServletRequest request,
			HttpServletResponse respose){
		List<Region> list=new ArrayList<Region>();
		User user = userService.findUserById(userId);
		if(user.getType().equals("200")){
			if(user!=null && user.getExtUser() !=null){//用户为空
				if(!StringUtils.isBlank(user.getExtUser().getOrgId())){//用户id不为空
					Hospital organization =  hospitalService.getById(user.getExtUser().getOrgId());
					list=regionService.getRegionByParents(organization.getRegionId());
				}
			}
		}else{
			list=regionService.getRegionsByUserId(userId);
		}
		JSONUtils.Success(list, respose);
	}
}