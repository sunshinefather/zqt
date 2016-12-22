package com.zyt.web.after.api.service;

import java.util.Map;
 

/**
 * @description 通讯录API接口
 * @author ZLM
 * @date 2014-06-18
 * @version 1.0
 */
public interface MailListAPIService {
	/**
	 * 获取机构和会员信息
	 * @param token 用户token
	 * @param orgId 
	 * @return 
	 * {'status':1(Integer),data:[],msg:'信息'(String)}
	 * {'status':0(Integer),msg:'失败信息'(String)}
	 */
	String getList(String token, String orgId);
	/**
	 * 获取用户的详细信息
	 * @param userId 用户id
	 * @return 
	 * {'status':1(Integer),data:[],msg:'信息'(String)}
	 * {'status':0(Integer),msg:'失败信息'(String)}
	 */
	String getUserById(String userId);
	/**
	 * 用户隐藏或者不隐藏电话号码
	 * @param token 用户token
	 * @param hideMobile 是否隐藏电话号码（0：不隐藏，1：隐藏）;
	 * @return
	 */
	Map<String, Object> updateHideMobile(String token,String hideMobile);
	
	/**
	 * 保存用户头像地址
	 * @param token 用户token
	 * @param avatar 用户头像ID
	 * @param avatarUrl 用户头像全地址
	 * @return
	 */
	Map<String, Object> updateAvatar(String token,String avatar,String avatarUrl);
	
}
