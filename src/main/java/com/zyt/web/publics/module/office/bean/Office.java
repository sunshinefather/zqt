package com.zyt.web.publics.module.office.bean;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.NotEmpty;

import com.zyt.web.publics.module.hospital.bean.Hospital;

/**
 * 
 * @author LiuChuang
 * @description 职务实体
 * @version 1.0
 * @date 2015年4月20日
 */
@Alias("Office")
public class Office implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private String officeId;
	
	/**
	 * 职务名称
	 */
	@NotEmpty(message="不能为空")
	private String officeName;
	
	/**
	 * 组织机构ID（单位）
	 */
	private String organizationId;
	
	/**
	 * 创建时间
	 */
	private Date applyTime;
	
	private Hospital organization;

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Hospital getOrganization() {
		return organization;
	}

	public void setOrganization(Hospital organization) {
		this.organization = organization;
	}
}
