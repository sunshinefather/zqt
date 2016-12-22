package com.zyt.web.after.api.service;

 
/**
 * @description 登录API
 * @author YuJ
 * @date 2014-04-18
 * @version 1.0
 */
public interface UserAPIService {
	/**
	 * 登录
	 * @param userName 用户名
	 * @param password 密码
	 * @return {status:1,objInfo:{'userName':'用户名',token:'用户token'}}
	 */
	String signUp(String userName, String password);
	/**
	 * 注销
	 * @param token 用户标示
	 * @return {status:1} or {status:0,msg:'异常消息'};
	 */
	String signOut(String token);
	/**
	 * 根据用户token 和 密码更改用户密码
	 * @param token 用户token
	 * @param passWord 用户密码
	 * @return {status:1} or {status:0,msg:'异常消息'};
	 */
	String modifyPassWord(String token, String passWord);
	
	/**
	 * 验证用户是否存在
	 * @param userName 用户名
	 * @return {status:1,allowed:true or false} or {status:0,msg:'异常消息'};
	 */
	String validateUser(String userName);
	/**
	 * 
	 *@Description: 修改密码
	 *@param userName
	 *@param newPassword
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年6月29日下午2:27:26
	 */
	String modifyPassword(String userName, String newPassword);
	
}
