package com.zyt.web.after.sysmanager.controller;
import httl.util.StringUtils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyt.web.after.sysmanager.service.IGroupService;
import com.zyt.web.publics.annotations.AfterMethod;
import com.zyt.web.publics.annotations.LogType;
import com.zyt.web.publics.base.BaseController;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.sysmanager.bean.Group;

/**
 * 
 * @author LiuChuang
 * @description 用户组控制器
 * @version 1.0
 * @date 2014-3-12
 */
@Controller
@RequestMapping("/after/group")
public class GroupController extends BaseController {
	@Autowired
	private IGroupService groupService;
	
	/**
	 * 
	 *@Description: 查询用户组
	 *@param model
	 *@param page
	 *@return String
	 *@version: v1.0.0
	 *@author: LiuChuang
	 * @throws Exception 
	 *@date: 2014-3-12下午2:41:43
	 * 
	 * Modification History:
	 *     Date		      Author		   Version		Description
	 * ---------------------------------------------------------*
	 * 2014-3-12	     LiuChuang		v1.0.0		   新建
	 */
	 @RequestMapping("/list")
	 public String list(Model model,PaginationAble page) throws Exception{
		 page.setWhereParameters(getParams(model));
		 model.addAttribute("page",page);
		 model.addAttribute("list", groupService.findList(page,getUser()));
		 return "after/sysmanger/group/list";
	 }
	 
	 /**
	  * 
	  *@Description: 根据用户ID查询用户组列表
	  *@param id 用户ID
	  *@return List<Group>
	  *@version: v1.0.0
	  *@author: LiuChuang
	  *@date: 2014-3-19下午4:00:39
	  */
	 @RequestMapping("/findGroupList")
	 public @ResponseBody List<Group> findGroupList(String id){
		 return groupService.findGroupList(id);
	 }
	 
	 /**
	  * 
	  *@Description: 支持跨域请求根据用户ID查询用户组列表
	  *@param model
	  *@param id
	  *@return
	  *@version: v1.0.0
	  *@author: Kevin
	  *@date: 2015年7月16日上午11:20:52
	  */
	 @RequestMapping("/findGroupListCallback")
	 public String findGroupListCallback(Model model,String id){
		 model.addAttribute("groups",groupService.findGroupList(id));
		 return "jsonView";
	 }
	 
	 /**
	  * 
	  *@Description: 增加用户组
	  *@param model
	  *@return String
	  *@version: v1.0.0
	  *@author: LiuChuang
	  *@date: 2014-3-19下午5:56:27
	  */
	 @RequestMapping("/add")
	 public String add(Model model){
		 return "after/sysmanger/group/edit";
	 }
	 
	 /**
	  * 
	  *@Description: 编辑用户组
	  *@param model
	  *@param id
	  *@return String
	  *@version: v1.0.0
	  *@author: LiuChuang
	  *@date: 2014-3-19下午5:57:17
	  */
	 @RequestMapping("/edit/{id}")
	 public String edit(Model model,@PathVariable String id){
		 model.addAttribute("obj",groupService.findGroupById(id));
		 return "after/sysmanger/group/edit";
	 }
	 
	 @RequestMapping("/saveOrUpdate")
	 @AfterMethod(value = "保存/更新用户组", type = LogType.SYSTEM)
	 public @ResponseBody Integer saveOrUpdate(Group group){
		 if(StringUtils.isBlank(group.getId()))
			 return groupService.save(group);
		 else
			 return groupService.update(group);
	 }
	 
	 /**
	  * 
	  *@Description: 转换用户组状态
	  *@param ids
	  *@param state
	  *@return Integer
	  *@version: v1.0.0
	  *@author: LiuChuang
	  *@date: 2014-3-20上午9:13:16
	  */
	 @RequestMapping("/transform")
	 @AfterMethod(value = "启用/禁用用户组", type = LogType.SYSTEM)
	 public @ResponseBody Integer transform(String ids, Integer state){
		 Assert.notNull(ids);
		 Assert.notNull(state);
		return  groupService.transform(state,ids.split(","));
	 }
	 
	 /**
	  * 
	  *@Description: 批量删除用户组
	  *@param ids
	  *@return Integer
	  *@version: v1.0.0
	  *@author: LiuChuang
	  *@date: 2014-3-20上午9:17:15
	  */
	 @RequestMapping("/deleteBatch")
	 @AfterMethod(value = "删除用户组", type = LogType.SYSTEM)
	 public @ResponseBody Integer deleteBatch(String ids){
		 Assert.notNull(ids);
		 return groupService.deleteBatch(ids.split(","));
	 }
	 
}
