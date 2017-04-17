package com.zyt.web.publics.module.sysmanager.dao; 

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.sysmanager.bean.ExtUser;
import com.zyt.web.publics.module.sysmanager.bean.User;



/**
 * 
 * @author LiuChuang
 * @description 操作用户表数据DAO层
 * @version 1.0
 * @date 2014-3-11
 */
public interface UserDao {
	
	/**
	 * 根据 userName 验证用户是否存在
	 * @param userName 
	 * @return 0=false:不存在 1=true：存在
	 */
	Integer validateUser(String userName);
	/**
	 * 
	 *@Description: 根据用户名查询用户
	 *@param userName
	 *@param app 是否app登录 为true时只会查询会员 为false 只会查询非会员用户，为null查询全部
	 *@return T
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-11上午11:41:54
	 * 
	 * Modification History:
	 *     Date		      Author		   Version		Description
	 * ---------------------------------------------------------*
	 * 2014-3-11	     LiuChuang		v1.0.0		   新建
	 */
	User findUserByName(@Param("userName")String userName,@Param("app")Boolean app);
	
	/**
	 * 
	 *@Description: 保存用户
	 *@param entity
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-11上午11:45:12
	 * 
	 * Modification History:
	 *     Date		      Author		   Version		Description
	 * ---------------------------------------------------------*
	 * 2014-3-11	     LiuChuang		v1.0.0		   新建
	 */
	Integer save(User entity);
	
	/**
	 * 
	 *@Description: 删除用户
	 *@param entity
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-11上午11:45:28
	 * 
	 * Modification History:
	 *     Date		      Author		   Version		Description
	 * ---------------------------------------------------------*
	 * 2014-3-11	     LiuChuang		v1.0.0		   新建
	 */
	Integer delete(User entity);
	
	/**
	 * 
	 *@Description: 更新用户
	 *@param entity
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-11上午11:45:46
	 * 
	 * Modification History:
	 *     Date		      Author		   Version		Description
	 * ---------------------------------------------------------*
	 * 2014-3-11	     LiuChuang		v1.0.0		   新建
	 */
	Integer update(User entity);
	
	/**
	 * 
	 *@Description: 更新用户扩展
	 *@param entity
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-17下午6:00:40
	 */
	Integer updateExt(User entity);
	
	/**
	 * 
	 *@Description: 查询列表
	 *@param paginationAble
	 *@param row
	 *@return List<User>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-17上午11:08:53
	 */
	List<User> findList(PaginationAble paginationAble,RowBounds row);
	/**
	 * 
	 *@Description: 保存扩展用户
	 *@param entity
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-17下午4:31:45
	 */
	Integer saveExt(User entity);
	
	/**
	 * 
	 *@Description: 禁用用户
	 *@param ids
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 * @param state 
	 *@date: 2014-3-17下午5:31:37
	 */
	Integer transform(@Param("state")Integer state, @Param("ids")String ...ids);
	
	/**
	 * 
	 *@Description: 根据ID查找用户
	 *@param id
	 *@return T
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-17下午5:44:20
	 */
	User findUserById(String id);
	
	/**
	 * 
	 *@Description: 删除用户扩展
	 *@param entity
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-17下午5:57:44
	 */
	Integer deleteExt(User entity);
	
	/**
	 * 
	 *@Description: 保存用户与用户组关联关系
	 *@param entity
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-18上午9:56:18
	 */
	Integer saveUserGroupLink(User entity);
	
	/**
	 * 
	 *@Description: 删除用户与用户组关联关系
	 *@param entity
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-20上午11:35:46
	 */
	Integer deleteUserGroupLink(User entity);
	/**
	 * 根据token获取用户
	 * @param token token
	 * @return User
	 */
	User findUserByToken(String token);
	/**
	 * 根据用户Id设置用户token为null
	 * @param id 用户Id
	 * @return 受影响的行数
	 */
	Integer updateByToken(String id);
	/**
	 * 根据用户名和密码查询用户
	 * @param userName
	 * @param passWord
	 * @return
	 */
	User findUserByNameAndPassWord(Map<String, Object> params);
	/**
	 * 根据机构Id获取机构下的用户
	 * @param map 机构和子属机构Id
	 * @return
	 */
	List<User> findUserByAll(Map<String, Object> map);
	/**
	 * 根据机构id和用户姓名获取该机构和其所有子级机构的用户
	 * @param map
	 * @return
	 */
	List<User> findUserByOrgAndName(Map<String, Object> map);
	/**
	 * 根据用户id数组查询用户
	 * @param map
	 * @return
	 */
	List<ExtUser> findUserByIds(Map<String, Object> map);
	/**
	 * 根据机构id分页获取当前机构下的用户
	 * @param orgId 机构id
	 * @return
	 */
	List<ExtUser> findUserByOrgId(PaginationAble paginationAble, RowBounds rowBounds);
	
	/**
	 * 
	 *@Description: 修正密码
	 *@param userId
	 *@param oldPwd
	 *@param newPwd
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-7-4下午11:40:42
	 */
	Integer changePassword(@Param("userId")String userId, @Param("oldPwd")String oldPwd, @Param("newPwd")String newPwd);

	/**
	 * 根据机构id获取当前机构下的所有用户
	 * @param orgId 机构id
	 * @return
	 */
	List<ExtUser> findUserByOrgId(Map<String, Object> map);
	
	/**
	 * 根据机构id获取当前机构下的所有用户不分页
	 * @param orgId 机构id
	 * @return
	 */
	List<User> findUserByOrgIdNoPage(Map<String, Object> map);
	
	/**
	 * 根据机构id获取当前机构下的所有用户
	 * @param id 机构id
	 * @return
	 */
	List<ExtUser> findUserByOrgIds(Map<String, Object> map);
	/**
	 * 
	 *@Description: 查找所有用户
	 *@param param
	 *@return List<User>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-7-11下午4:04:18
	 */
	List<User> findAllList(@Param("param")Map<String, Object> param);
	/**
	 * 根据分类或者分类下的专家用户
	 * @param professional
	 * @return
	 */
	List<User> getUsersByProfessional(String professional);
	/**
	 * 获取专家类型列表
	 * @return
	 */
	List<String> getProfessionals();
	/**
	 * 获取会员列表
	 * @param paginationAble
	 * @param row
	 * @return
	 */
	List<User> findMemberList(PaginationAble paginationAble,RowBounds row);
	/**
	 * 获取咨询人员列表
	 * @param paginationAble
	 * @param row
	 * @return
	 */
	List<User> findProList(PaginationAble paginationAble,RowBounds row);
	/**
	 * 获取会计列表
	 * @param paginationAble
	 * @param row
	 * @return
	 */
	List<User> findAccountantList(PaginationAble paginationAble,RowBounds row);
	
	/**
	 * 
	 *@Description: 根据ID查询用户
	 *@param ids
	 *@return List<User>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年3月6日下午2:20:13
	 */
	List<User> findUserByIds2(@Param("ids") String[] ids);
	
	/**
	 * 获取全部会员用户Ids
	 * @return
	 */
	List<String> findAllMembers();
	
	/**
	 * 
	 *@Description: 根据第三方提供的用户唯一值
	 *@param openId
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年7月2日上午11:29:00
	 */
	User findUserByOpenId(String openId);
	
	/**
	 * 
	 *@Description: 查询绑定此医院的APP用户数量
	 *@param hospitalId
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年7月20日下午4:37:35
	 */
	Integer selectCount(String hospitalId);
	
	/**
	 * 
	 *@Description: 根据权限获取所有类型的用户
	 *@param paginationAble
	 *@param rowBounds
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年8月31日下午5:52:33
	 */
	List<User> findUsersByPage(PaginationAble paginationAble, RowBounds rowBounds);
}
