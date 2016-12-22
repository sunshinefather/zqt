package com.zyt.web.after.api.service.impl;

import httl.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zyt.web.after.api.service.MailListAPIService;
import com.zyt.web.after.config.service.SysConfigService;
import com.zyt.web.after.hospital.service.HospitalService;
import com.zyt.web.after.sysmanager.service.IUserService;
import com.zyt.web.publics.module.hospital.bean.Hospital;
import com.zyt.web.publics.module.image.bean.Image;
import com.zyt.web.publics.module.sysmanager.bean.ExtUser;
import com.zyt.web.publics.module.sysmanager.bean.User;

@Service
public class MailListAPIServiceImpl implements MailListAPIService {
	@Autowired
	HospitalService hospitalService;
	@Autowired
	IUserService userService;
	@Autowired
	SysConfigService configService;

	@Override
	public String getList(String token,String orgId) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", 0);
		if (StringUtils.isNotBlank(token)) {
			User user = userService.findUserByToken(token);
			
			List<Hospital> orgs = null;
			
			if(StringUtils.isNotBlank(orgId)){
				orgs = hospitalService.getOrganizationByParentsId(orgId);
			}else{
				if (user != null) {
					String organizationId = user.getExtUser().getOrgId();
					Hospital organization = hospitalService.getById(organizationId);
					if (organization != null) {
						if (StringUtils.isBlank(organization.getParentsId())) //顶级机构
							orgs = hospitalService.getOrganizationByParentsId(organizationId);
						else {//非顶级
							String tempIds[] = organization.getParentsId().split(","); 
							organizationId = tempIds[tempIds.length -1];
							orgs = hospitalService.getOrganizationByParentsId(organizationId);
						}
					}
				}
			}

			if (orgs != null && !orgs.isEmpty()) {
				List<Object> resultList = new ArrayList<Object>();
				
				//机构排序
				Collections.sort(orgs, new Comparator<Hospital>() {
					@Override
					public int compare(Hospital o1, Hospital o2) {
						if(o1.getSort() > o2.getSort()){
							return 1;
						}else if(o1.getSort() < o2.getSort()){
							return  -1;
						}else{
							return 0;
						}
					}
				});
				
				
				
				for (Hospital org : orgs) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("orgId", org.getHospitalId());
					params.put("userId", user.getId());
					List<User> users = userService.findUserByOrgIdNoPage(params);
					
					String orgName = "";
					if(StringUtils.isNotBlank(org.getParentsId())){
						List<Hospital>  list =  hospitalService.getOrganizationByIds(org.getParentsId().split(","));
						for (Hospital organization:list) {
							if(StringUtils.isBlank(organization.getParentsId()) || "0".equals(organization.getParentsId())){
								continue;
							}
							if("".equals(orgName)){
								orgName = orgName + organization.getHospitalName();
							} else {
								orgName = orgName + "." + organization.getHospitalName();
							}
						}
					}
					if("".equals(orgName)){
						orgName = org.getHospitalName();
					}else{
						orgName = orgName + "." + org.getHospitalName();
					}
					
					Map<String, Object> reMap = new HashMap<String, Object>();
					reMap.put("orgId", org.getHospitalId());
					reMap.put("orgName", orgName);
					reMap.put("regionName",org.getRegion() == null?"":org.getRegion().getRegionName());
					reMap.put("parentId", StringUtils.isBlank(org.getParentId()) ? "0" : org.getParentId());
					reMap.put("orgSort",org.getSort());
					
					List<Map<String, Object>> reU = new ArrayList<Map<String, Object>>();
					if (users != null && !users.isEmpty()) {
						//机构排序
						Collections.sort(users, new Comparator<User>() {
							@Override
							public int compare(User o1, User o2) {
								if(o1.getExtUser() != null && o2.getExtUser() != null){
									if(o1.getExtUser().getSort() > o2.getExtUser().getSort()){
										return 1;
									}else if(o1.getExtUser().getSort() < o2.getExtUser().getSort()){
										return  -1;
									}
								}
								return 0;
							}
							
						});
						
						for (User u : users) {
							ExtUser extUser = u.getExtUser();
							if(extUser == null)
								continue;
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("userId", extUser.getId());
							map.put("userName",u.getUsername());
							map.put("fullName", extUser.getFullName());
							map.put("orgName", orgName);
							map.put("regionName",org.getRegion() == null?"":org.getRegion().getRegionName());
							String position = extUser.getPosition();
							if (StringUtils.isNotBlank(position) && extUser.getPositionBean() != null) {
									position = extUser.getPositionBean().getPositionName();
							} else{
									position = "暂无职位";
							}
							map.put("position", position);
							map.put("avatar", extUser.getAvatarImage() == null ? "" : extUser.getAvatarImage().getWebAdd());
							map.put("userSort",extUser.getSort());
							reU.add(map);
						}
					}
					reMap.put("users",reU);
					resultList.add(reMap);
				}
				if (resultList != null && !resultList.isEmpty()){
					result.put("data", resultList);
				}else{
					result.put("data", "[]");
				}
				result.put("status", 1);
			} else{
				result.put("msg", "暂无机构数据!");
			}
		} else{
			result.put("msg", "用户登录失效!");
		}
		return JSON.toJSONString(result);
	}

	@Override
	public String getUserById(String userId) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", 0);
		result.put("msg", "");
		try {
			User user = userService.findUserById(userId);
			if (user != null) {
				Map<String, Object> map = new HashMap<>();
				if (user.getExtUser() != null) {
					map.put("id", user.getId());
					map.put("name", user.getExtUser().getFullName());
					
					if (StringUtils.isNotBlank(user.getExtUser().getPosition()) && user.getExtUser().getPositionBean() != null) {
						map.put("position", user.getExtUser().getPositionBean().getPositionName());
					} else{
						map.put("position", "暂无职位");
					}
					map.put("avatar", "");
					map.put("mobile", "");
					map.put("mobile2", "");
					map.put("description", "");
					map.put("gender", "");
					map.put("orgName", "");
					map.put("regionName","");
					if (user.getExtUser() != null) {
						map.put("avatar",user.getExtUser().getAvatarImage() == null ? "": user.getExtUser().getAvatarImage().getWebAdd());
						if (!StringUtils.isBlank(user.getExtUser().getHideMobile())
								&& user.getExtUser().getHideMobile().equals("0")) {// 当用户没有隐藏电话号码是可以看见其电话号码
							map.put("mobile", user.getExtUser().getMobile());
							map.put("mobile2",user.getExtUser().getMobile2());
						}
						map.put("description", user.getExtUser().getDescriptions() == null ? "" : user.getExtUser().getDescriptions());
						map.put("gender", user.getExtUser().getGender());
						if (user.getExtUser().getOrganization() != null
								&& user.getExtUser().getOrganization().getHospitalName() != null)
							map.put("orgName", user.getExtUser().getOrganization().getHospitalName());
						
						if(user.getExtUser().getOrganization() != null && user.getExtUser().getOrganization().getRegion() != null){
							map.put("regionName",user.getExtUser().getOrganization().getRegion().getRegionName());
						}
					}
					map.put("fax", user.getExtUser().getFax() == null ? "" : user.getExtUser().getFax());
					map.put("fax2", user.getExtUser().getFax2() == null ? "" : user.getExtUser().getFax2());
					map.put("tel", user.getExtUser().getTel() == null ? "" : user.getExtUser().getTel());
					map.put("tel2", user.getExtUser().getTel2() == null ? "" : user.getExtUser().getTel2());
					result.put("status", 1);
					result.put("user", map);
				} else {
					result.put("msg", "获取用户信息失败！");
				}
			} else {
				result.put("msg", "该用户不存在！");
			}
		} catch (Exception e) {
			result.put("msg", "系统异常！");
		}
		return JSON.toJSONString(result);
	}

	@Override
	public Map<String, Object> updateHideMobile(String token, String hideMobile) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", 0);
		result.put("msg", "操作成功！");
		try {
			if (!StringUtils.isBlank(token) && !StringUtils.isBlank(hideMobile)) {
				User user = userService.findUserByToken(token);
				if (user != null && user.getExtUser() != null) {
					if (!StringUtils.isBlank(user.getExtUser().getHideMobile())
							&& user.getExtUser().getHideMobile()
									.equals(hideMobile)) {
						// 状态和数据库存入的状态一直，无需修改！
						if (user.getExtUser().getHideMobile().equals("1")) {
							result.put("msg", "你已经隐藏了电话号码，无需再次隐藏！");
						} else {
							result.put("msg", "你已经开放了电话号码，无需再次开放！");
						}
					} else {
						user.getExtUser().setHideMobile(hideMobile);
						userService.update(user);
						result.put("status", 1);
					}
				} else {
					result.put("msg", "没有获取到用户信息！");
				}
			} else {
				result.put("msg", "参数错误！");
			}
		} catch (Exception e) {
			result.put("msg", "系统异常！");
		}
		return result;
	}

	@Override
	public Map<String, Object> updateAvatar(String token, String avatar,String avatarUrl) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", 0);
		result.put("msg", "操作成功！");
		try {
			if (!StringUtils.isBlank(token) && !StringUtils.isBlank(avatar)) {
				User user = userService.findUserByToken(token);
				if (user != null && user.getExtUser() != null) {
						user.getExtUser().setAvatar(avatar);
						if(user.getExtUser().getAvatarImage() == null){
							Image avatarImage = new Image();
							user.getExtUser().setAvatarImage(avatarImage);
						}
						user.getExtUser().getAvatarImage().setWebAdd(avatarUrl);
						userService.update(user);
						result.put("status", 1);
				} else {
					result.put("msg", "用户登录失效！");
				}
			} else {
				result.put("msg", "参数错误！");
			}
		} catch (Exception e) {
			result.put("msg", "系统异常！");
		}
		return result;
	}
}
