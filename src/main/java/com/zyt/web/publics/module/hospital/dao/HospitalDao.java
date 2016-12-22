package com.zyt.web.publics.module.hospital.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.hospital.bean.Hospital;
/**
 * 
 * @author Kevin
 * @description 医院DAO层
 * @version 1.0
 * @date 2015年6月2日
 */
public interface HospitalDao {
	/**
	 * 
	 *@Description: 查询所有医院
	 *@param params
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年6月2日下午3:30:42
	 */
	List<Hospital>  getHospitals(Map<String, Object> params);
	
	/**
	 * 
	 *@Description: 查询所有的医院
	 *@param paginationAble
	 *@param row
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年6月18日下午2:09:00
	 */
	List<Hospital> findAll(PaginationAble paginationAble,RowBounds row);
	
	/**
	 * 
	 *@Description: 增加医院
	 *@param hospital
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年6月2日下午3:32:10
	 */
	Integer insertHospital(Hospital hospital);
	/**
	 * 
	 *@Description: 更新医院
	 *@param hospital
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年6月2日下午3:32:23
	 */
	Integer updateHospital(Hospital hospital);
	/**
	 * 
	 *@Description: 根据ID删除医院
	 *@param id
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年6月2日下午3:33:10
	 */
	Integer deleteById(String id);
	/**
	 * 
	 *@Description: 根据ID批量删除医院
	 *@param ids
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年6月2日下午3:36:03
	 */
	Integer deleteByIds(String ids[]);
	/**
	 * 
	 *@Description: 根据ID查询医院
	 *@param id
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年6月2日下午3:36:33
	 */
	Hospital getById(String id);
	/**
	 * 
	 *@Description: 分页获取医院列表数据
	 *@param paginationAble
	 *@param rowBounds
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年6月2日下午3:36:52
	 */
	List<Hospital> getByPage(PaginationAble paginationAble, RowBounds rowBounds);
	/**
	 * 
	 *@Description: 根据相同的父级Id获取该父级下所有子医院信息
	 *@param parentId
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年6月2日下午3:37:08
	 */
	List<Hospital> getByParentId(String parentId);
	/**
	 * 
	 *@Description: 根据用户Id获取用户所在的医院和下级子属医院
	 *@param userId
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年6月2日下午3:38:01
	 */
	List<Hospital> getByUserId(String userId);
	
	/**
	 * 
	 *@Description: 根据区域id获取到改区域下的所有医院
	 *@param regionId
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年6月2日下午3:39:09
	 */
	List<Hospital> getByRegoinId(String regionId);
	
	/**
	 * 
	 *@Description: 获取全部医院
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年6月2日下午3:39:45
	 */
	List<Hospital> getAll();
	
	/**
	 * 
	 *@Description:  根据父级Id获取当前机构，以及所有下级机构并按照parentsId进行排序
	 *@param parentId
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年6月2日下午3:39:53
	 */
	List<Hospital> getByParentsId(String parentId);
	
	/**
	 * 
	 *@Description: 根据医院以及下级医院并分页
	 *@param paginationAble
	 *@param rowBounds
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年6月2日下午3:40:42
	 */
	List<Hospital> getHospitalPageByParentsId(PaginationAble paginationAble, RowBounds rowBounds);
	
	/**
	 * 
	 *@Description: 根据医院id获取第一级子级医院
	 *@param id
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年6月2日下午3:41:00
	 */
	List<Hospital> getChildById(String id);
	
	/**
	 * 
	 *@Description: 根据区域ID集合查询符合条件的医院
	 *@param regionIds
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年6月2日下午3:41:16
	 */
	List<Hospital> getHospitalByRegionIds(@Param("regionIds")String[] regionIds);
	
	/**
	 * 
	 *@Description: 根据医院ID查询医院列表
	 *@param ids
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年6月2日下午3:41:28
	 */
	List<Hospital> getByIds(@Param("ids")String[] ids);
	/**
	 * 
	 *@Description: 获取第一级机构
	 *@return List<Hospital>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月24日下午1:52:10
	 */
	List<Hospital> getFirstLayer();
	
	/**
	 * 
	 *@Description: 根据用户权限获取数据
	 *@param paginationAble
	 *@param rowBounds
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年10月14日上午10:43:31
	 */
	List<Hospital> findListByPage(PaginationAble paginationAble, RowBounds rowBounds);
	
	/**
	 * 
	 *@Description: 根据编码查询
	 *@param hospitalCode
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 * @param hospitalId 
	 *@date: 2015年10月14日下午1:33:52
	 */
	Hospital findHospitalByCode(@Param("hospitalCode")String hospitalCode, @Param("hospitalId")String hospitalId);
}
