package com.zyt.web.after.agencyoverview.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyt.web.after.agencyoverview.service.IAgencyOverviewSerice;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.agencyOverview.bean.AgencyOverview;
import com.zyt.web.publics.module.agencyOverview.dao.IAgencyOverviewDao;
import com.zyt.web.publics.module.hospital.bean.Hospital;

@Service
public class AgencyOverviewSericeImpl implements IAgencyOverviewSerice{

	@Autowired
	IAgencyOverviewDao overviewDao;
	@Override
	public Integer add(AgencyOverview agencyOverview) {
		return overviewDao.add(agencyOverview);
	}

	@Override
	public Integer deleteByOrgIds(String orgId) {
		return overviewDao.deleteByOrgIds(orgId);
	}

	@Override
	public List<Hospital> getPageAgencyOverview(
			PaginationAble paginationAble) {
		return overviewDao.getPageAgencyOverview(paginationAble,  new RowBounds(paginationAble.getCurrentResult(), paginationAble.getPageSize()));
	}

	@Override
	public List<AgencyOverview> getAgencyOverviewByOrgId(String orgId) {
		return overviewDao.getAgencyOverviewByOrgId(orgId);
	}

	@Override
	public Integer save(List<AgencyOverview> agencyOverviews) {
		Integer i = 0;
		if(agencyOverviews!=null && agencyOverviews.size()>0){
			overviewDao.deleteByOrgIds(agencyOverviews.get(0).getOrgId());
			for (AgencyOverview agencyOverview : agencyOverviews) {
				overviewDao.add(agencyOverview);
				i++;
			}
		}
		return i;
	}

	@Override
	public AgencyOverview getAgencyOverviewById(String id) {
		return overviewDao.getAgencyOverviewById(id);
	}

}
