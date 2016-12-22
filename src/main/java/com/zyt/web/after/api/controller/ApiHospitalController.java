package com.zyt.web.after.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyt.web.after.api.service.HospitalAPIService;
import com.zyt.web.publics.base.JsonEntity;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.sysmanager.bean.User;

/**
 * 
 * @author LiuChuang
 * @description 医院相关接口
 * @version 1.0
 * @date 2015年4月16日
 */
@Controller
@RequestMapping("/api/company/")
public class ApiHospitalController extends ApiController {
	@Autowired
	private HospitalAPIService hospitalAPIService;
	
	/**
	 * 
	 *@Description: 根据用户ID获取该用户所在机构的顶级机构下的第一级子机构
	 *@param request
	 *@return String
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月16日下午12:02:10
	 */
   @RequestMapping(value = "getChildHospitalByUserId",method=RequestMethod.GET,produces={"text/plain;charset=utf-8"})
   @ResponseBody
   public String getChildOrganizationByUserId(HttpServletRequest request) {
           String token = request.getParameter("token");
           return hospitalAPIService.getChildHospitalByUserId(token);
   }
   
   /**
    * 
    *@Description: 根据机构ID获取此机构下的第一级子机构
    *@param request
    *@return String
    *@version: v1.0.0
    *@author: LiuChuang
    *@date: 2015年4月16日下午1:47:34
    */
   @RequestMapping(value = "getChildOrganizationById",method=RequestMethod.GET,produces={"text/plain;charset=utf-8"})
   @ResponseBody
   public String getChildOrganizationById(HttpServletRequest request) {
           String orgId = request.getParameter("orgId");
           return hospitalAPIService.getChildHospitalById(orgId);
   }
   
   /**
    * 
    *@Description: 获取第一级机构
    *@return String
    *@version: v1.0.0
    *@author: LiuChuang
    *@date: 2015年4月24日下午1:48:23
    */
   @RequestMapping(value="getFirstLayer",method=RequestMethod.GET,produces={"text/plain;charset=utf-8"})
   @ResponseBody
   public String getFirstLayer(){
	   return hospitalAPIService.getFirstLayer();
   }
   
   /**
    * 
    *@Description: 查询所有医院
    *@param page
    *@return
    *@version: v1.0.0
    */
   @RequestMapping(value="findAll",method=RequestMethod.GET)
   public @ResponseBody JsonEntity findAll(PaginationAble page,String token){
	   User user = super.getUser(token);
	   if(user!=null && "0".equals(user.getType())){
		   page.setWhereParameters(getParams());
		   return hospitalAPIService.findAll(page,user);
	   }return new JsonEntity();
   }
   
   @RequestMapping(value="findById/{companyId}",method=RequestMethod.GET)
   public @ResponseBody JsonEntity findAll(@PathVariable String companyId){
	   return hospitalAPIService.findById(companyId);
   }
   
}
