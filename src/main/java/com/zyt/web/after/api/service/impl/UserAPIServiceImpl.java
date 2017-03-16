package com.zyt.web.after.api.service.impl;
 
import httl.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.alibaba.fastjson.JSON;
import com.zyt.web.after.api.service.UserAPIService;
import com.zyt.web.after.sysmanager.service.IUserService;
import com.zyt.web.publics.module.sysmanager.bean.User;
import com.zyt.web.publics.utils.UUIDUtils;

@Service
public class UserAPIServiceImpl implements UserAPIService {
	@Autowired
	private IUserService userService;
	
	@Transactional
	@Override
	public String signUp(String userName, String password) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", 0);
		try {
			if (StringUtils.isNotBlank(userName)
					&& StringUtils.isNotBlank(password)) {
				User user = userService.findUserByName(userName,null);
				if (user != null) {
					user = userService.findUserById(user.getId());//防止用户组丢失问题
					if(user.isEnabled()){
						if(password.equals(user.getPassword())){
							user.getExtUser().setLastActiveTime(new Date());
							user.setToken(UUIDUtils.getUUID());
							userService.update(user);
							result.put("token", user.getToken());
							result.put("nickname", StringUtils.isBlank(user.getExtUser().getNickName())?"":user.getExtUser().getNickName());
							result.put("username", user.getUsername());
							result.put("userId", user.getId());
							result.put("userType", user.getType());
							result.put("photo", user.getExtUser().getAvatarImage() == null ? user.getExtUser().getAvatar()==null?"":user.getExtUser().getAvatar() : user.getExtUser().getAvatarImage().getWebAdd());
							result.put("orgId", user.getExtUser().getOrgId() == null?"":user.getExtUser().getOrgId());
							result.put("position", user.getExtUser().getPositionBean() == null ? "" : user.getExtUser().getPositionBean().getPositionName());
							result.put("mobile", user.getExtUser().getMobile() == null ? "" : user.getExtUser().getMobile());
							result.put("mobile2", user.getExtUser().getMobile2() == null ? "" : user.getExtUser().getMobile2());
							result.put("fax", user.getExtUser().getFax() == null ? "" : user.getExtUser().getFax());
							result.put("fax2", user.getExtUser().getFax2() == null ? "" : user.getExtUser().getFax2());
							result.put("tel", user.getExtUser().getTel() == null ? "" : user.getExtUser().getTel());
							result.put("tel2", user.getExtUser().getTel2() == null ? "" : user.getExtUser().getTel2());
							result.put("name", user.getExtUser().getFullName() == null ? "" : user.getExtUser().getFullName());
							result.put("orgName",user.getExtUser().getOrganization() == null?"":user.getExtUser().getOrganization().getHospitalName());
							result.put("hashSend",user.isHasSend());
							result.put("gender",user.getExtUser().getGender() == null?"":user.getExtUser().getGender());
							((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getServletContext().setAttribute(user.getToken(),user);
							}else{
								result.put("msg", "用户密码不正确");	
							}
					}else{
						result.put("msg", "用户禁止登陆!");	
					}
				} else{
					result.put("msg", "用户名未注册!");
				}
			} else{
				result.put("msg", "用户名或密码不能为空!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.clear();
			result.put("status",0);
			result.put("msg", "系统异常!");
		}
		return JSON.toJSONString(result);
	}
	

	@Transactional
	@Override
	public String signOut(String token) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 0);
		try {
			if (token != null && !"".equals(token)) {
				User user = userService.findUserByToken(token);
				if (user != null) {
					userService.updateByToken(user.getId());
					((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getServletContext().removeAttribute(token);
					map.put("status", 1);
				} else
					map.put("msg", "用户不存在!");
			} else
				map.put("msg", "用户令牌不能为空!");
		} catch (Exception e) {
			map.put("msg", "系统异常!");
		}
		return JSON.toJSONString(map);
	}

	@Override
	public String modifyPassWord(String token, String passWord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 0);
		try {
			if (token != null && !"".equals(token)) {
				User user = userService.findUserByToken(token);
				if (user != null) {
					user.setPassword(passWord);
					userService.update(user);
					map.put("status", 1);
				} else{
					map.put("msg", "用户不存在!");
				}
			} else{
				map.put("msg", "用户令牌不能为空!");
			}
		} catch (Exception e) {
			map.put("msg", "系统异常!");
		}
		return JSON.toJSONString(map);
	}

	@Override
	public String validateUser(String userName) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", 0);
		if (StringUtils.isNotBlank(userName)) {
			Integer rows = userService.validateUser(userName);
			if (rows >= 1)
				result.put("allowed", false);
			else
				result.put("allowed", true);
			result.put("status", 1);
		} else result.put("msg", "参数不能为空!");
		return JSON.toJSONString(result);
	}

	@Override
	public String modifyPassword(String userName, String newPassword) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", 0);
		User user = userService.findUserByName(userName,null);
		if(user == null){
			result.put("msg", "用户名不存在!");
		}else{
			user.setPassword(newPassword);
			userService.update(user);
			result.put("status", 1);
		}
		return JSON.toJSONString(result);
	}
	
}
