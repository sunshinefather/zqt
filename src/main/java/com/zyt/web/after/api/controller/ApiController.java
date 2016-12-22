package com.zyt.web.after.api.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.zyt.web.publics.module.sysmanager.bean.User;

public class ApiController{
	
	/**
	 * 
	 *@Description: 异常捕获，并按着接口返回错误信息
	 *@param re
	 *@param request
	 *@param response
	 *@return ModelAndView
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014年9月10日上午10:30:23
	 */
 	@ExceptionHandler(value = { Exception.class })
    public ModelAndView handleException(RuntimeException re, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("status", 0);
        model.put("msg", "系统异常");
        return new ModelAndView("jsonView",model);
    }
 	

 	/**
 	 * 
 	 *@Description: 获取参数集合
 	 *@return
 	 *@version: v1.0.0
 	 *@author: Kevin
 	 *@date: 2015年6月16日下午2:41:04
 	 */
 	protected Map<String, Object> getParams()  {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String[]> params = request.getParameterMap();
        if (params != null && params.size() > 0) {
            Map<String, Object> nativeMap = new LinkedHashMap<String, Object>();
            for (String paramKey : params.keySet()) {
            	String v = request.getParameter(paramKey);
            	if(request.getMethod().equalsIgnoreCase("get")){
                    try {
						v = new String(v.trim().getBytes("ISO-8859-1"), "utf-8");
					} catch (UnsupportedEncodingException e) {
						v =  request.getParameter(paramKey);
					}
                }
                nativeMap.put(paramKey, v);
            }
            return nativeMap.size() == 0 ? null : nativeMap;
        }
        return new HashMap<String, Object>();
    }
 	
 	/**
 	 * 
 	 *@Description: 从缓存中获取用户信息
 	 *@param token
 	 *@return
 	 *@version: v1.0.0
 	 *@author: Kevin
 	 *@date: 2015年7月20日下午4:25:54
 	 */
 	public User getUser(String token){
 		User user  = null;
 		if(StringUtils.isNotBlank(token)){
 			Object obj = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getServletContext().getAttribute(token);
          if(obj!=null && obj instanceof User){
        	  user = (User) obj;
        	  }
          }
 		return user;
 	}
 	
}
