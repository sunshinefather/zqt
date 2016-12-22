package com.zyt.web.after.hospital.service;
 
import java.util.List;
import java.util.Map;

import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.hospital.bean.Hospital;
import com.zyt.web.publics.module.sysmanager.bean.User;

/**
 * @description 业务层接口
 * @author YuJ
 * @date 2014-04-12
 * @version 1.0
 */
public interface HospitalService {
	/**
	 * 根据用户类型获取获取数据（如果为区域用户，那么需要获取区域以及子属区域下的所有机构（包括子属机构），
	 * 如果为机构用户那么需要获取机构下和子属机构，
	 * 如果为特殊用户那么获取全部数据）
	 * @param params 参数对象<{isRoot:ture},{type:100},{regionIds:[]},{userId:用户Id}>
	 * isRoot：是否跟对象,type：用户类型（0：超管，100：区域用户、200：医院管理员，regionIds：区域数组，userId：用户Id）
	 * @return 机构数据集合
	 */
	 List<Hospital> findList(Map<String, Object> params);
	 
	 /**
	  * 
	  *@Description: 查询所有的医院
	  *@param paginationAble
	  *@return
	  *@version: v1.0.0
	  *@author: Kevin
	  *@date: 2015年6月18日下午2:10:12
	  */
	 List<Hospital> findAll(PaginationAble paginationAble);
	
	/**
	 * 增加医院
	 * @param hospital 医院对象
	 * @return true or false
	 */
	Integer save(Hospital hospital);
	/**
	 * 更改医院
	 * @param hospital 医院对象
	 * @return true or false
	 */
	Integer updateHospital(Hospital hospital);
	/**
	 * 根据Id删除机构
	 * @param id 机构Id
	 * @return true or false
	 */
	Boolean deleteById(String id);
	/**
	 * 根据机构Ids批量删除机构
	 * @param ids 机构数组
	 * @return 受影响的行数
	 */
	Boolean deleteByIds(String ids[]);
	/**
	 * 根据机构Id获取机构对象
	 * @param id 机构Id
	 * @return 机构对象
	 */
	Hospital getById(String id);
	
	/**
	 * 根据相同的父级Id获取该父级下所有子级机构信息
	 * @param parentId
	 * @return
	 */
	List<Hospital> getByParentId(String parentId);
	/**
	 * 根据机构ID获取全部父级Ids
	 * @param orgId
	 * @return
	 */
	String[] getParentsOrgsByOrgId(String orgId);
	/**
	 * 根据用户Id获取用户所在的机构Id和子属机构的Id
	 * @param userId 用户Id
	 * @return 机构Id数组
	 */
	String[] getChildOrgsByUserId(String userId);
	/**
	 * 根据用Id获取用户所在的机构Id和父类机构的Id
	 * @param userId 用户Id
	 * @return 机构Id数组
	 */
	String[] getParentsOrgsByUserId(String userId);
	/**
	 * 根据区域id获取到改区域下的所有机构
	 * @param regionId 区域Id
	 * @return List<Organization>
	 */
	List<Hospital> findOrganizationByRegoinId(String regionId);
	/**
	 * 获取全部机构
	 * @return
	 */
	List<Hospital> getAllOrganization();
	/**
	 * 根据机构Id获取当前机构，以及所有下级机构并按照parentsId进行排序
	 * @return
	 */
	List<Hospital> getOrganizationByParentsId(String parentId);
	
	/**
	 * 机构以及下级机构并分页
	 * @param paginationAble
	 * @param rowBounds
	 * @return
	 */
	List<Hospital> getOrganizationPageByParentsId(PaginationAble paginationAble);
	/**
	 * 根据机构id获取第一级子级机构
	 * @param map
	 * @return
	 */
	List<Hospital> getChildOrganizationById(String id);
	
	/**
	 * 
	 *@Description: 根据区域ID查询出所有符合条件的机构
	 *@param regionIds
	 *@return List<String>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-7-11下午3:01:38
	 */
	List<Hospital> getOrganizationByRegionIds(String [] regionIds);
	
	/**
	 * 
	 *@Description: 根据机构ID集合查询组织机构
	 *@param ids
	 *@return List<Organization>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月21日下午6:05:04
	 */
	List<Hospital> getOrganizationByIds(String [] ids);
	
	/**
	 * 
	 *@Description: 获取第一层机构
	 *@return List<Organization>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月24日下午1:51:19
	 */
	List<Hospital> getFirstLayer();
	
	/**
	 * 
	 *@Description: 分页获取数据
	 *@param page
	 *@param user
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年10月14日上午9:47:31
	 */
	List<Hospital> findListByPage(PaginationAble page, User user);
	
	/**
	 * 
	 *@Description: 验证编码是否唯一
	 *@param hospitalCode 编码
	 *@param hospitalId  记录ID
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年10月14日下午1:28:04
	 */
	boolean validCode(String hospitalCode, String hospitalId);
	
	}
