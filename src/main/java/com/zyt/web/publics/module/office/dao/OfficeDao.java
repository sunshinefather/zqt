package com.zyt.web.publics.module.office.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.office.bean.Office;

/**
 * 
 * @author LiuChuang
 * @description 职务DAO层
 * @version 1.0
 * @date 2015年4月20日
 */
public interface OfficeDao {
	/**
	 * 
	 *@Description: 保存
	 *@param office
	 *@return int
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月20日下午5:09:51
	 */
	public int save(Office office);
	/**
	 * 
	 *@Description: 批量删除
	 *@param ids
	 *@return int
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月20日下午5:13:22
	 */
	public int delete(@Param("ids")String [] ids);
	
	/**
	 * 
	 *@Description: 更新岗位
	 *@param office
	 *@return int
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月20日下午5:13:57
	 */
	public int update(Office office);
	/**
	 * 
	 *@Description: 分页查询岗位信息
	 *@param paginationAble
	 *@param row
	 *@return List<Office>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月20日下午5:15:03
	 */
	public List<Office> findList(PaginationAble paginationAble,RowBounds row);
	
	/**
	 * 
	 *@Description: 根据ID查询职务
	 *@param id
	 *@return Office
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月22日下午4:24:19
	 */
	public Office getById(String id);
	
	/**
	 * 
	 *@Description: 根据组织机构查询职务
	 *@param orgIds
	 *@return List<Office>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月23日下午6:49:20
	 */
	public List<Office> getByOrgIds(@Param("orgIds")String[] orgIds);
	/**
	 * 
	 *@Description: 根据用户权限获取职务
	 *@param params
	 *@return List<Office>
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月24日上午11:53:31
	 */
	public List<Office> getByUser(Map<String, Object> params);
}
