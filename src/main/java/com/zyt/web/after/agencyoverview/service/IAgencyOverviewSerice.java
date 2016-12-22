package com.zyt.web.after.agencyoverview.service;

import java.util.List;

import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.agencyOverview.bean.AgencyOverview;
import com.zyt.web.publics.module.hospital.bean.Hospital;

/**
 * @author ZLM
 * @description  机构概述持久层接口
 * @version 1.0
 * @date 2014-7-22
 */
public interface IAgencyOverviewSerice {
	/**
	 * 添加
	 * @param agencyOverview
	 * @return
	 */
	Integer add(AgencyOverview agencyOverview);
	/**
	 * 根据机构id删除机构概述
	 * @param orgId
	 * @return
	 */
	Integer deleteByOrgIds(String orgId);
	/**
	 * 分页获取信息
	 * @param paginationAble
	 * @param rowBounds
	 * @return
	 */
	List<Hospital> getPageAgencyOverview(PaginationAble paginationAble);
	/**
	 * 根据机构id获取机构概述
	 * @param orgId 机构id
	 * @return
	 */
	List<AgencyOverview> getAgencyOverviewByOrgId(String orgId);
	
	/**
	 * 根据机构id获取机构概述
	 * @param orgId 机构id
	 * @return
	 */
	Integer save(List<AgencyOverview> agencyOverviews);
	/**
	 * 根据id获取
	 * @param id
	 * @return AgencyOverview
	 */
	AgencyOverview getAgencyOverviewById(String id);
	
}
