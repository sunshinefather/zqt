package com.zyt.web.after.api.service;

import com.zyt.web.publics.base.JsonEntity;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.sysmanager.bean.User;

/**
 * 
 * @author LiuChuang
 * @description 机构相关接口服务层
 * @version 1.0
 * @date 2015年4月16日
 */
public interface HospitalAPIService {
	
	/**
	 * 
	 *@Description: 根据用户ID获取该用户所在机构的顶级机构下的第一级子机构
	 *@param token 登录令牌
	 *@return String
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月16日下午12:05:32
	 */
	public String getChildHospitalByUserId(String token);
	
	/**
	 * 
	 *@Description: 根据机构ID获取此机构下的第一级子机构
	 *@param orgId 机构ID
	 *@return String
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月16日下午1:48:20
	 */
	public String getChildHospitalById(String orgId);
	
	/**
	 * 
	 *@Description: 获取第一级机构
	 *@return String
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月24日下午1:49:05
	 */
	public String getFirstLayer();
	
	/**
	 * 
	 *@Description: 查询所有医院
	 *@param page
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 * @param user 
	 *@date: 2015年6月18日下午2:15:56
	 */
	public JsonEntity findAll(PaginationAble page, User user);
	
	/**
	 * 
	 *@Description: 根据ID查询医院
	 *@param hospitalId
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年6月18日下午2:44:33
	 */
	public JsonEntity findById(String hospitalId);
	
}
