package com.zyt.web.publics.module.config.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.config.bean.SysConfig;

public interface SysConfigDao {

	public List<SysConfig> getConfigs();

	public List<SysConfig> getConfigsOfPage(PaginationAble page,
			RowBounds bounds);

	public SysConfig getConfigById(String configId);

	public int deleteConfigById(String configId);

	public int deleteConfigsByIds(String[] configIds);

	public int updateConfigById(SysConfig config);

	public int insertConfig(SysConfig config);

}
