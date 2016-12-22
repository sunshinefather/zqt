package com.zyt.web.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import com.zyt.web.publics.module.sysmanager.bean.Resource;
import com.zyt.web.publics.module.sysmanager.bean.Role;
import com.zyt.web.publics.module.sysmanager.dao.ResourceDao;
import com.zyt.web.publics.module.sysmanager.dao.RoleDao;

/**
 * 
 * 最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。 此类在初始化时，应该取到所有资源及其对应角色的定义。
 * 
 */
public class SecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	@Autowired
	private ResourceDao resourceDao;

	@Autowired
	private RoleDao roleDao;
	
	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	public SecurityMetadataSource() {
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		if (resourceMap == null || resourceMap.size() == 0) {
			loadAuthorityResource();
		}
		HttpServletRequest httpServletRequest = ((FilterInvocation) object).getHttpRequest();
		for (String resource : resourceMap.keySet()) {
			if (StringUtils.isNotBlank(resource) && !"#".equals(resource)) {
				if (antPathMatcher.match(resource.startsWith("/")?resource:"/"+resource,httpServletRequest.getServletPath())) {
					return resourceMap.get(resource);
				}
			}
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> objClass) {
		return true;
	}

	private void loadAuthorityResource() {

		List<Role> roles = roleDao.findAllRoles();// 加载所有的角色
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		if (roles != null) {
			for (Role role : roles) {
				ConfigAttribute configAttribute = new SecurityConfig(role.getRoleCode());
				List<Resource> resources = resourceDao.findResourceByRoleId(role.getId());
				if (resources == null) {
					continue;
				}
				for (Resource resource : resources) {
					// 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中
					if (resourceMap.containsKey(resource.getResourceUrl())) {
						Collection<ConfigAttribute> value = resourceMap.get(resource.getResourceUrl());
						value.add(configAttribute);
						resourceMap.put(resource.getResourceUrl(), value);
					} else {
						Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
						configAttributes.add(configAttribute);
						resourceMap.put(resource.getResourceUrl(),configAttributes);
					}
				}
			}
		}
	}
}
