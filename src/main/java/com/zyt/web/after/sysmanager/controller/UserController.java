package com.zyt.web.after.sysmanager.controller;

import httl.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyt.web.after.config.service.SysConfigService;
import com.zyt.web.after.hospital.service.HospitalService;
import com.zyt.web.after.position.service.PositionService;
import com.zyt.web.after.sysmanager.service.IRegionService;
import com.zyt.web.after.sysmanager.service.IUserService;
import com.zyt.web.publics.annotations.AfterMethod;
import com.zyt.web.publics.annotations.LogType;
import com.zyt.web.publics.base.BaseController;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.sysmanager.bean.User;
import com.zyt.web.publics.utils.JSONUtils;

/**
 * 
 * @author LiuChuang
 * @description 用户控制器
 * @version 1.0
 * @date 2014-3-12
 */
@Controller
@RequestMapping("/after/user/")
public class UserController extends BaseController {

	@Autowired
	private IUserService userService;
	@Autowired
	SysConfigService configService;
	@Autowired
	HospitalService hospitalService;
	@Autowired
	IRegionService regionService;
	@Autowired
	private PositionService positionService;

	/**
	 * 系统用户列表
	 * @param model
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String list(Model model, PaginationAble page) throws Exception {
		page.setWhereParameters(getParams(model));
		List<User> users = userService.findList(page,getUser());
		model.addAttribute("list", users);
		model.addAttribute("page", page);
		return "after/sysmanger/user/list";
	}
	
	/**
	 * 
	 *@Description: TODO
	 *@param model
	 *@param page
	 *@return
	 *@throws Exception
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年8月31日下午4:13:00
	 */
	@RequestMapping(value = "json/list", method = RequestMethod.GET)
	public String jsonList(Model model,PaginationAble page) throws Exception {
		page.setWhereParameters(getParams());
		List<User> users = userService.findUsersByPage(page,getUser());
		model.addAttribute("users", users);
		return "jsonView";
	}

	/**
	 * 
	 * @Description: 增加用户
	 * @param model
	 * @return String
	 * @version: v1.0.0
	 * @author: LiuChuang
	 * @date: 2014-3-17下午2:24:09
	 */
	@RequestMapping("add")
	public String add(Model model) {
		User user = getUser();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isRoot", false);
		params.put("type", user.getType());
		if ("100".equals(user.getType())) {// 区域用户
			String[] regionIds = regionService.getChildRegionsByUserId(user
					.getId());
			params.put("regionIds", regionIds);
		} else
			params.put("userId", user.getId());
		model.addAttribute("orgList", hospitalService.findList(params));
		model.addAttribute("positions",positionService.getByUser(getUser()));
		return "after/sysmanger/user/edit";
	}

	/**
	 * 
	 * @Description: 编辑用户
	 * @param model
	 * @return String
	 * @version: v1.0.0
	 * @author: LiuChuang
	 * @date: 2014-3-17下午5:41:16
	 */
	@RequestMapping("edit/{id}")
	public String edit(Model model, @PathVariable String id) {
		User user = userService.findUserById(id);
		model.addAttribute("obj", user);
		User loginUser = getUser();
		// 处理排序（选择了的机构默认展示在第一个下拉选择中）
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isRoot", false);
		params.put("type", loginUser.getType());
		if ("100".equals(loginUser.getType())) {// 区域用户
			String[] regionIds = regionService.getChildRegionsByUserId(loginUser.getId());
			params.put("regionIds", regionIds);
		} else{
			params.put("userId", loginUser.getId());
		}
		model.addAttribute("positions",positionService.getByUser(getUser()));
		model.addAttribute("orgList", hospitalService.findList(params));
		return "after/sysmanger/user/edit";
	}

	/**
	 * 
	 * @Description: 保存用户
	 * @param user
	 * @version: v1.0.0
	 * @author: LiuChuang
	 * @date: 2014-3-17下午4:21:31
	 */
	@RequestMapping("saveOrUpdate")
	@AfterMethod(value = "保存/更新用户信息", type = LogType.SYSTEM)
	public @ResponseBody Integer saveOrUpdate(User user) {
		if (StringUtils.isBlank(user.getId()))
			return userService.save(user);
		else{
			if(user.getExtUser().getOrgId() == null)//把某机构用户重置为其他类型的用户时，需要去掉该值
				user.getExtUser().setOrgId("");
			return userService.update(user);
		}
	}

	/**
	 * 
	 * @Description: 转换用户账号状态
	 * @param request
	 * @param ids
	 *            要禁用的用户IDs，多个用逗号隔开
	 * @version: v1.0.0
	 * @author: LiuChuang
	 * @date: 2014-3-17下午5:11:47
	 */
	@RequestMapping("transform")
	@AfterMethod(value = "启用/禁用用户账户", type = LogType.SYSTEM)
	public @ResponseBody
	Integer transform(String ids, Integer state) {
		Assert.notNull(ids);
		return userService.transform(state, ids.split(","));
	}
	
	/**
	 * 
	 *@Description: 修改密码
	 *@param userId 用户ID
	 *@param oldPwd 旧密码
	 *@param newPwd 新密码
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-7-4下午5:33:04
	 */
	@RequestMapping(value="changePassword/{userId}/{oldPwd}/{newPwd}",method=RequestMethod.POST)
	@AfterMethod(value = "修改密码", type = LogType.SYSTEM)
	public void changePassword(@PathVariable String userId,@PathVariable String oldPwd,@PathVariable String newPwd,HttpServletResponse resp){
		try {
			Integer row = userService.changePassword(userId,oldPwd,newPwd);
			JSONUtils.Success(row, resp);
		} catch (Exception e) {
			JSONUtils.Failure("原密码不正确", resp);
		}
	}
	
	@RequestMapping("/updatePwd")
	@AfterMethod(value = "修改密码", type = LogType.SYSTEM)
	public void updatePwd(@RequestParam(required=true,value="userId")String userId,@RequestParam(required=true,value="oldPwd") String oldPwd,@RequestParam(required=true,value="newPwd") String newPwd,HttpServletResponse resp){
		try {
			Integer row = userService.changePassword(userId,oldPwd,newPwd);
			JSONUtils.Success(row, resp);
		} catch (Exception e) {
			JSONUtils.Failure("原密码不正确", resp);
		}
	}
	
	/**
	 * 
	 *@Description: 修改密码，支持跨域访问
	 *@param userId
	 *@param oldPwd
	 *@param newPwd
	 *@param model
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年7月16日下午4:38:25
	 */
	@RequestMapping(value="changePasswordCallback/{userId}/{oldPwd}/{newPwd}",method=RequestMethod.GET)
	@AfterMethod(value = "修改密码", type = LogType.SYSTEM)
	public String changePasswordCallback(@PathVariable String userId,@PathVariable String oldPwd,@PathVariable String newPwd,Model model){
			model.addAttribute("row",userService.changePassword(userId,oldPwd,newPwd));
			return "jsonView";
	}
	
	/**
	 * 
	 *@Description: 跳转修改密码页面
	 *@return String
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-7-5上午12:26:47
	 */
	@RequestMapping(value="toChangePassword",method=RequestMethod.GET)
	public String toChangePassword(){
		
		return "after/navigate/changePwd";
	}
	

	/**
	 * 删除用户
	 * @param ids Ids 数组字符串
	 * @param type 删除的用户类型 1：系统用户、2：会员、3：会计、4：咨询人员
	 * @return
	 */
	@RequestMapping("delete/{ids}/{type}")
	public String delete(@PathVariable String ids, @PathVariable String type) {
		User user = getUser();
		if (user != null) 
			userService.delete(ids);
		String url = null;
		switch (type) {
		case "1":
			url = "redirect:/after/user/list";
			break;
		case "2":
			url = "redirect:/after/member/list";
			break;
		case "3":
			url = "redirect:/after/accountant/list";
			break;
		case "4":
			url = "redirect:/after/professional/list";
			break;
		}
		return url;
	}

}
