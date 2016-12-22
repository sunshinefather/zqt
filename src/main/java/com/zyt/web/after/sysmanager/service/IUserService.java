package com.zyt.web.after.sysmanager.service;
 
import java.util.List;
import java.util.Map;

import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.sysmanager.bean.ExtUser;
import com.zyt.web.publics.module.sysmanager.bean.User;

/**
 * 
 * @author LiuChuang
 * @description 操作用户服务层
 * @version 1.0
 * @date 2014-3-11
 */
public interface IUserService {
	/**
	 * 
	 *@Description: 根据用户名查询用户对象
	 *@param userName
	 *@param app 是否app登录 为true时只会查询会员 为false 只会查询非会员用户，为null查询全部
	 *@return User
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-11下午6:06:28
	 * 
	 * Modification History:
	 *     Date		      Author		   Version		Description
	 * ---------------------------------------------------------*
	 * 2014-3-11	     LiuChuang		v1.0.0		   新建
	 */
	User findUserByName(String userName,Boolean app);
	
	/**
	 * 根据 userName 验证用户是否存在
	 * @param id 修改的时候需要排除当前id
	 * @param userName 
	 * @return 0=false:不存在 1=true：存在
	 */
	Integer validateUser(String userName);
	
	/**
	 * 
	 *@Description: 保存用户
	 *@param user
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-11下午6:07:05
	 * 
	 * Modification History:
	 *     Date		      Author		   Version		Description
	 * ---------------------------------------------------------*
	 * 2014-3-11	     LiuChuang		v1.0.0		   新建
	 */
	Integer save(User user);
	
	/**
	 * 
	 *@Description: 删除用户
	 *@param user
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-11下午6:07:30
	 * 
	 * Modification History:
	 *     Date		      Author		   Version		Description
	 * ---------------------------------------------------------*
	 * 2014-3-11	     LiuChuang		v1.0.0		   新建
	 */
	Integer delete(User user);
	/**
	 * 根据用户Ids批量删除用户
	 * @param userIds 
	 * @return 受影响的行数
	 */
	Integer delete(String userIds);
	
	/**
	 * 
	 *@Description: 删除用户扩展
	 *@param user
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-17下午5:56:47
	 */
	Integer deleteExt(User user);
	
	/**
	 * 
	 *@Description: 更新用户
	 *@param user
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-11下午6:08:18
	 * 
	 * Modification History:
	 *     Date		      Author		   Version		Description
	 * ---------------------------------------------------------*
	 * 2014-3-11	     LiuChuang		v1.0.0		   新建
	 */
	Integer update(User user);
	
	/**
	 * 
	 *@Description: 分页查询用户列表
	 *@param page
	 *@return List<User>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 * @param user 
	 *@date: 2014-3-17上午11:07:44
	 */
	List<User> findList(PaginationAble page, User user);
	
	/**
	 * 
	 *@Description: 获取全部可见的用户
	 *@param user
	 *@return List<User>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-7-11下午3:58:52
	 */
	List<User> findAllList(Map<String,Object>param,User user);
	/**
	 * 分页获取会员列表
	 * @param page
	 * @return
	 */
	List<User> findMemberList(PaginationAble page,User user);
	
	/**
	 * 
	 *@Description: 禁用用户
	 *@param ids
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 * @param state 
	 *@date: 2014-3-17下午5:26:43
	 */
	Integer transform(Integer state,String ...ids);
	
	/**
	 * 
	 *@Description: 根据ID查询用户实体
	 *@param id
	 *@return User
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-17下午5:43:30
	 */
	User findUserById(String id);
	
	/**
	 * 
	 *@Description: 保存用户与用户组关联关系
	 *@param user
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-19下午3:40:40
	 */
	Integer saveUserGroupLink(User user);
	
	/**
	 * 
	 *@Description: 更新用户扩展信息
	 *@param user
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-19下午3:46:55
	 */
	Integer updateExt(User user);
	
	/**
	 * 
	 *@Description: 删除用户与用户组关联关系
	 *@param user
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-3-20上午11:34:49
	 */
	Integer deleteUserGroupLink(User user);
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
	Boolean updateByToken(String id);
	/**
	 * 根据用户名和密码查询用户
	 * @param userName
	 * @param passWord
	 * @return
	 */
	User findUserByNameAndPassWord(String userName, String passWord);

	/**
	 * 根据机构Id获取机构下的用户
	 * @param orgIds 机构和子属机构Id
	 * @return
	 */
	List<User> findUserByAll(String[] orgIds);
	/**
	 * 根据发布消息者的UUID获取发布消息者的昵称 或者是 机构名称
	 * @param publisher 发布消息者Id
	 * @return
	 */
	String getFromByPublisher(String publisher);
	
	/**
	 * 根据机构id和用户姓名获取该机构和其所有子级机构的用户
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List<User> findUserByOrgAndName(Map map);
	/**
	 * 根据用户id数组查询用户
	 * @param ids 用户id数组
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List<ExtUser> findUserByIds(Map map);
	
	/**
	 * 根据机构id获取当前机构下的所有用户
	 * @param orgId 机构id
	 * @return
	 */
	List<ExtUser> findUserByOrgId(PaginationAble paginationAble);
	
	/**
	 * 
	 *@Description: 修改密码
	 *@param userId
	 *@param oldPwd
	 *@param newPwd
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-7-4下午11:39:57
	 */
	Integer changePassword(String userId, String oldPwd, String newPwd);
	/**
	 * 根据机构id获取当前机构下的所有用户不分页
	 * @param orgId 机构id
	 * @return
	 */
	List<User> findUserByOrgIdNoPage(Map<String, Object> map);
	/**
	 * 根据机构ids获取当前机构下的所有用户
	 * @param orgId 机构id
	 * @return
	 */
	List<ExtUser> findUserByOrgIds(Map<String, Object> map);
	
	/**
	 * 
	 *@Description: 加载权限数据
	 *@param param
	 *@param user
	 *@return Map<String,Object>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-7-11上午10:09:08
	 */
	Map<String,Object> loadAuth(Map<String,Object> param,User user);
	/**
	 * 根据分类或者分类下的专家用户
	 * @param professional
	 * @return
	 */
	List<User> getUsersByProfessional(String professional);
	/**
	 * 获取专家类型列表数据 T列表中的key
	 * @param key
	 * @return
	 */
	String getProfessionals(String key);
	/**
	 * 分页获取咨询人员列表
	 * @param page
	 * @return
	 */
	List<User> findProList(PaginationAble page,User user);
	/**
	 * 分页获取会计列表
	 * @param page
	 * @return
	 */
	List<User> findAccountantList(PaginationAble page,User user);
	
	/**
	 * 
	 *@Description: 根据ID查询用户
	 *@param ids
	 *@return List<User>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年3月6日下午2:07:04
	 */
	List<User> findUserByIds2(String[] ids);
	
	/**
	 * 获取全部会员用户Ids，用于拉取电子书刊设置电子书刊的默认权限
	 * @return
	 */
	String findAllMembers();
	
	/**
	 * 
	 *@Description: 根据第三方唯一值查询用户
	 *@param openId 用户唯一值
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年7月2日上午11:24:33
	 */
	public User findUserByOpenId(String openId);
	
	/**
	 * 
	 *@Description: 根据医院ID查询绑定了此医院的APP用户
	 *@param hospitalId
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年7月20日下午4:36:35
	 */
	Integer selectCount(String hospitalId);
	
	/**
	 * 
	 *@Description: 根据权限获取任何类型的用户
	 *@param page
	 *@param user
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年8月31日下午5:50:22
	 */
	public List<User> findUsersByPage(PaginationAble page,User user);
	
	/**
	 * 
	 *@Description: 清除用户信息不需要放入缓存中的数据
	 *@param user
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年9月15日下午3:40:50
	 */
	public User cleanUser(User user);
}
