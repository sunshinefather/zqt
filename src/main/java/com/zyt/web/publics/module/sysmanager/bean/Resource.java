package com.zyt.web.publics.module.sysmanager.bean;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

/**
 * 
 * @author LiuChuang
 * @description 资源
 * @version 1.0
 * @date 2015年3月26日
 */
@Alias("Resource")
public class Resource implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "ID不能为空")
	private String id;

	/**
	 * 资源编号
	 */
	@NotBlank(message = "资源编号不能为空")
	private String code;

	/**
	 * 资源名
	 */
	@NotBlank(message = "资源名称不能为空")
	private String resourceName;

	/**
	 * 资源路径
	 */
	@NotBlank(message = "资源路径不能为空")
	private String resourceUrl;

	/**
	 * 资源所属上级
	 */
	private String parent;

	/**
	 * 资源排序
	 */
	private Integer order = 0;
	
	/**
	 * 资源类型 1菜单,2功能
	 */
	private String resourceType;
	
	/**
	 * 项目标识
	 */
	private String project;

	@NumberFormat
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getResourceName() {
		return resourceName == null ? "" : resourceName.trim();
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceUrl() {
		return resourceUrl == null ? "" : resourceUrl.trim();
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public String getParent() {
		return parent == null ? "" : parent.trim();
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result
				+ ((resourceName == null) ? 0 : resourceName.hashCode());
		result = prime * result
				+ ((resourceType == null) ? 0 : resourceType.hashCode());
		result = prime * result
				+ ((resourceUrl == null) ? 0 : resourceUrl.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resource other = (Resource) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (resourceName == null) {
			if (other.resourceName != null)
				return false;
		} else if (!resourceName.equals(other.resourceName))
			return false;
		if (resourceType == null) {
			if (other.resourceType != null)
				return false;
		} else if (!resourceType.equals(other.resourceType))
			return false;
		if (resourceUrl == null) {
			if (other.resourceUrl != null)
				return false;
		} else if (!resourceUrl.equals(other.resourceUrl))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Resource [id=" + id + ", code=" + code + ", resourceName="
				+ resourceName + ", resourceUrl=" + resourceUrl + ", parent="
				+ parent + ", order=" + order + ", resourceType="
				+ resourceType + ", project=" + project + "]";
	}
	
	
}
