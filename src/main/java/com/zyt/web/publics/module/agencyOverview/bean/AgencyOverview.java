package com.zyt.web.publics.module.agencyOverview.bean;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.zyt.web.publics.module.hospital.bean.Hospital;

@Alias("AgencyOverview")
public class AgencyOverview implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private String id;
	/**
	 * 主题
	 */
	private  String title;
	/**
	 * 顺序
	 */
	private  Integer sequence;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 机构id
	 */
	private String orgId;
	/**
	 * 时间
	 */
	private Date date;
	/**
	 * 机构对象
	 */
	private Hospital organization;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Hospital getOrganization() {
		return organization;
	}
	public void setOrganization(Hospital organization) {
		this.organization = organization;
	}
}
