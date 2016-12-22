package com.zyt.web.publics.module.position.bean;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.NotEmpty;

import com.zyt.web.publics.module.hospital.bean.Hospital;

/**
 * 
 * @author LiuChuang
 * @description 岗位实体
 * @version 1.0
 * @date 2015年4月20日
 */
@Alias("Position")
public class Position implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private String positionId;
	
	/**
	 * 岗位名称
	 */
	@NotEmpty(message="不能为空")
	private String positionName;
	
	/**
	 * 组织机构ID（单位）
	 */
	private String organizationId;
	
	/**
	 * 创建时间
	 */
	private Date applyTime;
	
	private Hospital organization;

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Hospital getOrganization() {
		return organization;
	}

	public void setOrganization(Hospital organization) {
		this.organization = organization;
	}
	
}
