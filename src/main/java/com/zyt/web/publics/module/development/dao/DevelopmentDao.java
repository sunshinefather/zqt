package com.zyt.web.publics.module.development.dao;

import java.util.List;
import java.util.Map;

import com.zyt.web.publics.base.BaseDAO;
import com.zyt.web.publics.module.development.bean.Development;

/**
 * 企业发展管理Dao
 * @ClassName:  DevelopmentDao   
 * @Description:TODO   
 * @author: sunshine 
 */
public interface DevelopmentDao extends BaseDAO<Development> {
	public List<Development> getYearExport(Map<String,String> map);
}
