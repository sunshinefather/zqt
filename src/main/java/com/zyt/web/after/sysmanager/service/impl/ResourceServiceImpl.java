package com.zyt.web.after.sysmanager.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zyt.web.after.sysmanager.service.IResourceService;
import com.zyt.web.publics.module.sysmanager.bean.Resource;
import com.zyt.web.publics.module.sysmanager.bean.Role;
import com.zyt.web.publics.module.sysmanager.dao.ResourceDao;
import com.zyt.web.publics.utils.CacheModuleConstants;
import com.zyt.web.publics.utils.HibernateValidatorFactoryUtils;
import com.zyt.web.publics.utils.SystemUtils;

@Service
public class ResourceServiceImpl implements IResourceService {

	@Autowired
	private ResourceDao resourceDao;

	@Override
	@Cacheable(value=CacheModuleConstants.SYS_RESOURCE)
	public List<Resource> getResources(String resourceType,String projects) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("resourceType", resourceType);
		if(StringUtils.isNotBlank(projects)){
			params.put("projects", Arrays.asList(projects.split(",")));
		}
		return resourceDao.getResources(params);
	}

	@Transactional
	@Override
	@CacheEvict(value=CacheModuleConstants.SYS_RESOURCE,allEntries=true)
	public Map<String, Object> insertResource(Resource resource) {
		resource.setId(SystemUtils.getUUIDCode());
		Map<String, Object> valis = HibernateValidatorFactoryUtils
				.verifyObject(resource);
		try {
			if (valis == null) {
				int result = resourceDao.insertResource(resource);
				valis = new HashMap<String, Object>();
				valis.put("result", result > 0 ? true : false);
				valis.put("message", result > 0 ? "添加成功" : "添加失败");
				valis.put("id", resource.getId());
			}
			return valis;
		} catch (Exception e) {
			throw new RuntimeException("添加资源信息错误");
		}
	}

	@Transactional
	@Override
	@CacheEvict(value=CacheModuleConstants.SYS_RESOURCE,allEntries=true)
	public Map<String, Object> deleteResourceById(String resourceId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int result = resourceDao.deleteResourceById(resourceId);
			map.put("result", result > 0 ? true : false);
			map.put("message", result > 0 ? "删除成功" : "删除失败");
			return map;
		} catch (Exception e) {
			throw new RuntimeException("删除资源信息错误; " + e.getMessage());
		}
	}

	@Override
	public Resource getResourceById(String resourceId) {
		return resourceDao.getResourceById(resourceId);
	}

	@Override
	public List<Resource> findResourceByRoleIds(List<Role> roles, String resourceType,String projects) {
		if (roles == null) {
			return new ArrayList<Resource>();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roles", roles);
		params.put("resourceType", resourceType);
		if(StringUtils.isNotBlank(projects)){
			params.put("projects", Arrays.asList(projects.split(",")));
		}
		return resourceDao.findResourceByRoleIds(params);
	}

	@Override
	@Cacheable(value=CacheModuleConstants.SYS_RESOURCE)
	public List<Resource> findFirstLevelResourceByRoleId(String roleId) {
		return resourceDao.findFirstLevelResourceByRoleId(roleId);
	}

	@Override
	@Cacheable(value=CacheModuleConstants.SYS_RESOURCE)
	public List<Resource> findNextLevelResourceByResourceId(String resourceId) {
		return resourceDao.findNextLevelResourceByResourceId(resourceId);
	}

	@Override
	public List<Resource> findResourceByRoleId(String roleId) {
		return resourceDao.findResourceByRoleId(roleId);
	}
	@Override
	@CacheEvict(value=CacheModuleConstants.SYS_RESOURCE,allEntries=true)
	public Map<String, Object> updateResource(Resource resource) {
		Map<String, Object> valis = HibernateValidatorFactoryUtils
				.verifyObject(resource);
		try {
			if (valis == null) {
				int result = resourceDao.updateResource(resource);
				valis = new HashMap<String, Object>();
				valis.put("result", result > 0 ? true : false);
			}
			return valis;
		} catch (Exception e) {
			throw new RuntimeException("修改资源信息错误");
		}
	}
}
