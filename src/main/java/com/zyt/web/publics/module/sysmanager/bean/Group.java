package com.zyt.web.publics.module.sysmanager.bean;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.ibatis.type.Alias;

import com.zyt.web.publics.module.hospital.bean.Hospital;

/**
 * 
 * @author LiuChuang
 * @description 用户组
 * @version 1.0
 * @date 2014-3-11
 */
@Alias("Group")
public class Group implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 编码
	 */
	@NotNull(message="编码不能为空")
	private String groupCode;
	
	/**
	 * 组名
	 */
	@NotNull(message="组名不能为空")
	private String groupName;
	
	/**
	 * 是否启用
	 */
	private Boolean enabled;
	
	/**
	 * 角色集合
	 */
	private List<Role> roles;
	
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

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String grroupName) {
		this.groupName = grroupName;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Hospital getOrganization() {
		return organization;
	}

	public void setOrganization(Hospital organization) {
		this.organization = organization;
	}
	
}
