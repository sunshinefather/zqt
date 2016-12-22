package com.zyt.web.security.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.zyt.web.publics.module.sysmanager.bean.Role;
import com.zyt.web.publics.module.sysmanager.bean.User;
import com.zyt.web.publics.module.sysmanager.dao.RoleDao;
import com.zyt.web.publics.module.sysmanager.dao.UserDao;
import com.zyt.web.security.exception.SecurityAuthenticationException;

public class SecurityUserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserDao securityUserDao;
	@Autowired
	private RoleDao securityRoleDao;

	@Override
	public UserDetails loadUserByUsername(String userName)	throws UsernameNotFoundException {
		
		Collection<GrantedAuthority> grantedAuthoritys = new ArrayList<GrantedAuthority>();
		User user = securityUserDao.findUserByName(userName,false);
		if (user == null) {
			throw new UsernameNotFoundException(String.format(
					"用户名： [%s] 没找到", userName));
		}
		
		List<Role> roles = securityRoleDao.findRolesByUser(user);
		if (roles == null) {
			throw new SecurityAuthenticationException("未分配角色");
		}
		
		for (Role role : roles) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleCode());
			grantedAuthoritys.add(grantedAuthority);
		}
		
		user.setAuthorities(grantedAuthoritys);
		user.setCredentialsNonExpired(user.isEnabled());
		return user;
	}

}
