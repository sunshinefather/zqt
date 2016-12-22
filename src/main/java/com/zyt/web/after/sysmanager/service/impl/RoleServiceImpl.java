package com.zyt.web.after.sysmanager.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.zyt.web.after.sysmanager.service.IResourceService;
import com.zyt.web.after.sysmanager.service.IRoleService;
import com.zyt.web.after.sysmanager.service.IUserService;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.sysmanager.bean.Resource;
import com.zyt.web.publics.module.sysmanager.bean.Role;
import com.zyt.web.publics.module.sysmanager.bean.User;
import com.zyt.web.publics.module.sysmanager.dao.RoleDao;
import com.zyt.web.publics.utils.UUIDUtils;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private IResourceService resourceService;
	
	@Autowired
	private IUserService userService;
	
	@Override
	public List<Role> findList(PaginationAble paginationAble,User user){
		userService.loadAuth(paginationAble.getWhereParameters(), user);
		return roleDao.findList(paginationAble,new RowBounds(paginationAble.getCurrentResult(), paginationAble.getPageSize()));
	}

	@Override
	public Integer save(Role role) {
		role.setId(UUIDUtils.getUUID());
		role.setRoleCode(role.getId());
		role.setEnabled(true);
		this.saveRoleGroupLink(role);
		this.saveRoleResourceLink(role);
		return roleDao.save(role);
	}

	@Override
	public Integer delete(Role role) {
		this.deleteRoleGroupLink(role);
		return roleDao.delete(role);
	}

	@Override
	public Integer update(Role role) {
		this.deleteRoleGroupLink(role);
		this.saveRoleGroupLink(role);
		this.deleteRoleResourceLink(role);
		this.saveRoleResourceLink(role);
		return roleDao.update(role);
	}

	@Override
	public List<Role> findAllRoles() {
		return roleDao.findAllRoles();
	}

	@Override
	public Role findRoleById(String roleId) {
		return roleDao.findRoleById(roleId);
	}

	@Override
	public Integer transform(Integer state, String... ids) {
		return roleDao.transform(state,ids);
	}

	@Override
	public Integer saveRoleGroupLink(Role role) {
		if(role.getGroups() != null && role.getGroups().size() > 0)
			return roleDao.saveRoleGroupLink(role);
		return 0;
	}
	
	@Override
	public Integer saveRoleResourceLink(Role role) {
		if(role.getResources() != null && role.getResources().size() > 0)
			return roleDao.saveRoleResourceLink(role);
		return 0;
	}

	@Override
	public Integer deleteRoleGroupLink(Role role) {
		if(role.getGroups() != null && role.getGroups().size() > 0)
			return roleDao.deleteRoleGroupLink(role);
		return 0;
	}
	
	public Integer deleteRoleResourceLink(Role role){
		if(role.getResources() != null && role.getResources().size() > 0)
			return roleDao.deleteRoleResourceLink(role);
		return 0;
	}

	@Override
	public Integer deleteBatch(String[] ids) {
		if(ids != null && ids.length > 0)
			return roleDao.deleteBatch(ids);
		return 0;
	}

	@Override
	public List<Resource> getResourcesByRole(Role role) {
		return resourceService.findResourceByRoleId(role.getId());
	}

	@Override
	public List<Role> findRolesByUser(User user) {
		Assert.notNull(user.getId(),"用户ID不能为空！");
		return roleDao.findRolesByUser(user);
	}

	@Override
	public List<Role> search(Map<String,Object>param, User user) {
		return roleDao.search(userService.loadAuth(param, user));
	}
}
