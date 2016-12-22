package com.zyt.web.after.navigate.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zyt.web.after.sysmanager.service.IUserService;
import com.zyt.web.publics.base.BaseController;

/**
 * @description 后台导航控制器
 * @version 1.0
 */
@Controller
public class NavigateController extends BaseController{
	
	@Autowired
	private IUserService userService;
	
	/**
	 * 
	 *@Description: 跳转到首页
	 */
	@RequestMapping("/dashboard")
	public String navigate(Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String pwd= getUser().getPassword();
		if("111111".equals(pwd)){
			model.addAttribute("changePwd","1");
		}
		return "after/navigate/frame";
	 }
	
	/**
	 * 
	 *@Description: 首页
	 *@param model
	 *@return String
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年10月19日下午11:29:05
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request,HttpServletResponse response){
		
		return "after/navigate/home";
	}
	
	/**
	 * 
	 *@Description: 登录
	 *@param request
	 *@param response
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年10月13日上午9:58:30
	 */
	@RequestMapping("/navigate")
	public String login(HttpServletRequest request,HttpServletResponse response){
		return "after/login/navigate";
	}
	
	/**
	 * 
	 *@Description: 引导页
	 *@param request
	 *@param response
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年10月13日上午9:58:30
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		return "after/login/index";
	}
	
	/**
	 * 
	 *@Description: 登录失败显示页面
	 *@param request
	 *@param response 
	 *@return String
	 *@throws Exception
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年2月12日上午10:57:43
	 */
	@RequestMapping("/failure")
	public String failure(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		return "after/access/failure";
	}
	
	/**
	 * 
	 *@Description: 展示没权限的页面
	 *@param request
	 *@param response
	 *@return String
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年2月12日上午10:58:35
	 */
	@RequestMapping("/denied")
	public String denied(HttpServletRequest request,HttpServletResponse response){
		return "after/access/denied";
	}
}
