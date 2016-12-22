package com.zyt.web.after.config.service.impl;

import httl.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.zyt.web.after.config.service.SysConfigService;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.config.bean.SysConfig;
import com.zyt.web.publics.module.config.dao.SysConfigDao;
import com.zyt.web.publics.utils.CacheModuleConstants;
import com.zyt.web.publics.utils.HibernateValidatorFactoryUtils;
import com.zyt.web.publics.utils.SystemConstantUtils;

@Service
public class SysConfigServiceImpl implements SysConfigService {

	@Autowired
	private SysConfigDao sysConfigDao;

	@Override
	@Cacheable(value=CacheModuleConstants.SYSCONFIG)
	public List<SysConfig> getConfigs() {
		return sysConfigDao.getConfigs();
	}

	@Override
	public SysConfig getConfigById(String configId) {
		return sysConfigDao.getConfigById(configId);
	}

	@Transactional
	@Override
	@CacheEvict(value=CacheModuleConstants.SYSCONFIG,allEntries=true)
	public boolean updateConfigById(SysConfig config) {
		return sysConfigDao.updateConfigById(config) > 0 ? true : false;
	}

	@Transactional
	@Override
	@CacheEvict(value=CacheModuleConstants.SYSCONFIG,allEntries=true)
	public boolean deleteConfigById(String configId) {
		return sysConfigDao.deleteConfigById(configId) > 0 ? true : false;
	}

	@Transactional
	@Override
	@CacheEvict(value=CacheModuleConstants.SYSCONFIG,allEntries=true)
	public Map<String, Object> insertConfig(SysConfig config) {

		try {
			Map<String, Object> valis = HibernateValidatorFactoryUtils
					.verifyObject(config);
			if (valis == null || valis.size() == 0) {
				valis = new HashMap<String, Object>();
				int flog = sysConfigDao.insertConfig(config);
				valis.put("result", flog > 0 ? "succ" : "fail");
			}
			return valis;
		} catch (Exception e) {
			throw new RuntimeException("添加信息错误,对应的key可能存在!");
		}
	}

	@Override
	public List<SysConfig> getConfigsPage(PaginationAble page,
			Map<String, Object> params) {
		RowBounds bounds = new RowBounds(page.getCurrentResult(),
				SystemConstantUtils.PAGINATION_PAGE_SIZE);
		if (params != null && params.size() > 0) {
			page.setWhereParameters(params);
		}
		return sysConfigDao.getConfigsOfPage(page, bounds);
	}

	@Override
	@Transactional
	@CacheEvict(value=CacheModuleConstants.SYSCONFIG,allEntries=true)
	public Map<String, Object> deleteConfigsByIds(String[] configIds) {
		Map<String, Object> result = null;
		try {
			// 批量删除配置属性信息
			if (configIds != null && configIds.getClass().isArray()
					&& configIds.length > 0) {
				result = new HashMap<String, Object>();
				int rint = sysConfigDao.deleteConfigsByIds(configIds);
				// 返回结果
				result.put("result",
						rint > 0 && rint == configIds.length ? "succ" : "fail");
			}
		} catch (Exception e) {
			throw new RuntimeException("批量删除信息错误 ");
		}

		return result;
	}

	@Override
	public String getValue(String configkey, String key) {
		SysConfig sysConfig = sysConfigDao.getConfigById(configkey);
		if (sysConfig != null) {
			String values = sysConfig.getConfigValue();
			if (StringUtils.isNotBlank(values)) {
				JSONObject js = JSONObject.parseObject(values);
				for (String k : js.keySet()) {
					if (k.equals(key))
						return js.getString(k);
				}
			} 
			return "";
		} else
			return "";
	}

}
