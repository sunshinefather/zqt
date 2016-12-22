package com.zyt.web.publics.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * @author LiuChuang
 * @description JSON工具类
 * @version 1.0
 * @date 2014-3-12
 */
public class JSONUtils {
	
public static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
	
	/**
     * 
     *@Description: 利用 <JSONSerializer>把JavaBean转换为JSON字符串
     *@param o 需要转换的对象
     *@param filterProperties 转换对象中需要过滤的属性
     *@return String 转换后的字符串
     *@version: v1.0.0
     *@author: LiuChuang
     *@date: 2014-2-19下午4:11:09
     */
	public static String toJSONString(Object o,String... filterProperties){
		return toJSONStringWithDateFormat(o,JSONUtils.dateFormat, filterProperties);
	}
	
	/**
	 * 
	 *@Description: 利用 <JSONSerializer>把JavaBean转换为JSON字符串，同时会以传入的日期格式化字符串对日期进行格式化
	 *@param o 需要转换的对象
	 *@param dateFormat 日期格式化字符串
	 *@param filterProperties 转换对象中需要过滤的属性
	 *@return String 转换后的字符串
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-4-19下午1:47:20
	 */
	public  static String toJSONStringWithDateFormat(Object o,String dateFormat,String...filterProperties){
		SerializeWriter sw=new SerializeWriter();
		JSONSerializer jl=new JSONSerializer(sw);
		jl.config(SerializerFeature.WriteDateUseDateFormat,true);
		jl.setDateFormat(dateFormat);
		jl.config(SerializerFeature.DisableCircularReferenceDetect, true);
		for(final String filterProperty:filterProperties){
			jl.getPropertyFilters().add(new PropertyFilter(){
				public boolean apply(Object arg0, String name, Object arg2) {
					if(filterProperty.equals(name))
						return false;
					else
						return true;
				}});
		}
		jl.write(o);
		return jl.toString();
	}
	
	/**
	 * 
	 *@Description: 把对象转换为JSON后，返回给前端
	 *@param resp <HttpServletResponse>
	 *@param o 返回的对象
	 *@param filterProperties 需要过滤对象中的某些属性
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-2-19下午4:16:30
	 *请尽量使用spring mvc提供的<@ResponseBody>注解的方式
	 */
	public static void  response(HttpServletResponse resp,Object o,String... filterProperties){
		try {
			resp.setContentType("application/json;charset=utf-8");
			PrintWriter writer = resp.getWriter();
			writer.write(toJSONString(o, filterProperties));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 *@Description: 操作成功时调用的方法
	 *@param o 返回的对象
	 *@param resp
	 *@param filterStrings 需要过滤的属性
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-2-19下午4:20:33
	 *请尽量使用spring mvc提供的<@ResponseBody>注解的方式
	 */
	public static void Success(Object o,HttpServletResponse resp,String... filterStrings){
		Map<String, Object> resMap= new HashMap<String, Object>();
		resMap.put("status", 200);
		resMap.put("result",o);
		response(resp,resMap,filterStrings);
	}
	
	/**
	 * 
	 *@Description: 操作失败时调用的方法
	 *@param err_msg 失败信息
	 *@param resp 
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-2-19下午4:21:52
	 * 请尽量使用spring mvc提供的<@ResponseBody>注解的方式
	 */
	public static void Failure(String err_msg,HttpServletResponse resp){
		Map<String, Object> resMap= new HashMap<String, Object>();
		resMap.put("status", 500);
		resMap.put("msg",err_msg);
		response(resp,resMap);
	}
}
