package com.zyt.web.publics.module.sysmanager.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zyt.web.publics.module.sysmanager.bean.Region;
/**
 * 区域数据dao
 * @ClassName:  RegionDao   
 * @Description: 
 * @author: sunshine  
 * @date:   2014年3月12日 上午11:00:55
 */
public interface RegionDao {
	/**
	 * 获取所有区域数据
	 * @return
	 */
	List<Region> getRegions();
    /**
     * 根据id获取区域数据
     * @param id
     * @return
     */
	Region getRegionById(@Param("id")Serializable id);
    /**
     * 单条插入区域数据
     * @param region
     * @return
     */
	int insertRegion(Region region);
    /**
     * 更新区域数据
     * @param region
     * @return
     */
	int updateRegion(Region region);
    /**
     * 根据id删除区域数据
     * @param id
     * @return
     */
	int deleteRegion(@Param("id")Serializable id);
	/**
	 * 更具用户id获取区域以及子区域
	 * @Title: getRegionByUserId   
	 * @Description: 
	 * @param: @param userId
	 * @param: @return      
	 * @return: List<Region>      
	 * @throws
	 */
    List<Region> getRegionByUserId(@Param("userId")Serializable userId);
    /**
     * 根据id获取该区域和该区域下所有区域
     * @param id 区域id
     * @return  List<Region>
     */
    List<Region> getRegionByParents(String id);
    
    /**
     * 
     *@Description: 根据父级ID查询子级第一级行政区划
     *@param parentId
     *@return
     *@version: v1.0.0
     *@author: Kevin
     *@date: 2015年7月28日上午11:46:42
     */
	List<Region> getRegionByParentId(@Param("parentId")String parentId,@Param("regionName")String regionName);
	
	/**
	 * 
	 *@Description: 根据行政区划查询
	 *@param regionName
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年7月29日下午2:14:40
	 */
	Region getRegionByName(@Param("regionName")String regionName);
}
