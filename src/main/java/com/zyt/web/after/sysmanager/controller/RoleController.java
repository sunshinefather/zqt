package com.zyt.web.after.sysmanager.controller;

import httl.util.StringUtils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyt.web.after.sysmanager.service.IRoleService;
import com.zyt.web.publics.annotations.AfterMethod;
import com.zyt.web.publics.annotations.LogType;
import com.zyt.web.publics.base.BaseController;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.sysmanager.bean.Resource;
import com.zyt.web.publics.module.sysmanager.bean.Role;

/**
 * 
 * @author LiuChuang
 * @description 角色控制器
 * @version 1.0
 * @date 2014-3-11
 */
@Controller
@RequestMapping("/after/role")
public class RoleController extends BaseController{
	 @Autowired
	 private IRoleService roleService;
	 
	 /**
	  * 
	  *@Description: 角色列表
	  *@param region 区域实体
	  *@param model
	  *@return String
	  *@version: v1.0.0
	  *@author: LiuChuang
	 * @throws Exception 
	  *@date: 2014-3-11下午3:10:04
	  */
	 @RequestMapping("/list")
	 public String list(Model model,PaginationAble page) throws Exception{
		 page.setWhereParameters(getParams(model));
		 model.addAttribute("page",page);
		 model.addAttribute("list", roleService.findList(page,getUser()));
		 return "after/sysmanger/role/list";
	 }
	 
	 /**
	  * 
	  *@Description: 增加角色
	  *@param model
	  *@return String
	  *@version: v1.0.0
	  *@author: LiuChuang
	  *@date: 2014-3-17上午10:12:39
	  */
	 @RequestMapping("/add")
	 public String add(Model model){
		 return "after/sysmanger/role/edit";
	 }
	 
	 /**
	  * 
	  *@Description: 编辑角色
	  *@param model
	  *@param id
	  *@return String
	  *@version: v1.0.0
	  *@author: LiuChuang
	  *@date: 2014-3-19下午1:32:43
	  */
	 @RequestMapping("/edit/{id}")
	 public String edit(Model model,@PathVariable String id){
		 model.addAttribute("obj",roleService.findRoleById(id));
		 return "after/sysmanger/role/edit";
	 }
	 
	 /**
	  * 
	  *@Description: 保存角色
	  *@param role
	  *@return Integer
	  *@version: v1.0.0
	  *@author: LiuChuang
	  *@date: 2014-3-19下午1:24:44
	  */
	 @RequestMapping("/saveOrUpdate")
	 @AfterMethod(value = "保存/更新角色", type = LogType.SYSTEM)
	 public @ResponseBody Integer saveOrUpdate(Role role){
		 if(StringUtils.isBlank(role.getId()))
			 return roleService.save(role);
		 else
			 return roleService.update(role);
	 }
	 
	 /**
	  * 
	  *@Description: 转换角色状态
	  *@param ids
	  *@param state
	  *@return Integer
	  *@version: v1.0.0
	  *@author: LiuChuang
	  *@date: 2014-3-19下午1:33:51
	  */
	 @RequestMapping("/transform")
	 @AfterMethod(value = "启用/禁用角色", type = LogType.SYSTEM)
	 public @ResponseBody Integer transform(String ids, Integer state){
		 Assert.notNull(ids);
		 Assert.notNull(state);
		return  roleService.transform(state,ids.split(","));
	 }
	 
	 /**
	  * 
	  *@Description: 删除角色
	  *@param ids
	  *@return Integer
	  *@version: v1.0.0
	  *@author: LiuChuang
	  *@date: 2014-3-19下午4:41:56
	  */
	 @RequestMapping("/deleteBatch")
	 @AfterMethod(value = "删除角色", type = LogType.SYSTEM)
	 public @ResponseBody Integer deleteBatch(String ids){
		 Assert.notNull(ids);
		 return roleService.deleteBatch(ids.split(","));
	 }
	 
	 /**
	  * 
	  *@Description: 根据用户角色查询该角色的资源
	  *@param role
	  *@version: v1.0.0
	  *@author: LiuChuang
	  *@date: 2014-3-20下午5:56:33
	  */
	 @RequestMapping("/getResourcesByRole")
	 public @ResponseBody List<Resource> getResourcesByRole(Role role){
		return roleService.getResourcesByRole(role);
	 }
	 
	 /**
	  * 
	  *@Description: 跳转到搜索页面
	  *@param model
	  *@return String
	  *@version: v1.0.0
	  *@author: LiuChuang
	  *@date: 2014-7-10下午1:42:38
	  */
	 @RequestMapping(value="/toSearch",method = RequestMethod.GET)
	 public String toSearch(Model model){
		 return "after/sysmanger/role/listModal";
	 }
	 
	 /**
	  * 
	  *@Description: 通过AJAX的方式搜索角色
	  *@param roleName 角色名
	  *@param regionId  区域ID
	  *@return List<Mother>
	  *@version: v1.0.0
	  *@author: LiuChuang
	  *@date: 2014-4-28下午2:37:00
	  */
	 @RequestMapping(value="/search",method = {RequestMethod.GET,RequestMethod.POST})
	 public @ResponseBody List<Role>  search(Model model){
		 	
			return roleService.search(getParams(model),getUser());
	 }
	 
}
