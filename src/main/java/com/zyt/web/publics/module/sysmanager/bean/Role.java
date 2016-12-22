package com.zyt.web.publics.module.sysmanager.bean;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.ibatis.type.Alias;

import com.zyt.web.publics.module.hospital.bean.Hospital;

/**
 * 
 * @author LiuChuang
 * @description 角色实体
 * @version 1.0
 * @date 2014-3-11
 */
@Alias("Role")
public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 角色名
	 */
	@NotNull(message="角色名不能为空")
	private String roleName;
	
	/**
	 * 角色编码
	 */
	@NotNull(message="角色编码不能为空")
	private String roleCode;
	
	/**
	 * 是否启用
	 */
	private Boolean enabled;
	
	/**
	 * 行政划区域
	 */
	private Region region;
	
	/**
	 * 用户组
	 */
	private List<Group> groups;
	
	/**
	 * 资源
	 */
	private List<Resource> resources;
	
	/**
	 * 用户所属机构对象
	 */
	private Hospital organization;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public Hospital getOrganization() {
		return organization;
	}

	public void setOrganization(Hospital organization) {
		this.organization = organization;
	}
	
}
