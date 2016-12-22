package com.zyt.web.publics.module.hospital.bean;

import java.io.Serializable;
import java.util.List;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.NotBlank;
import com.zyt.web.publics.module.agencyOverview.bean.AgencyOverview;
import com.zyt.web.publics.module.image.bean.Image;
import com.zyt.web.publics.module.sysmanager.bean.Region;

/**
 * 
 * @author Kevin
 * @description 医院实体
 * @version 1.0
 * @date 2015年6月2日
 */
@Alias("Hospital")
public class Hospital implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private String hospitalId;
	/**
	 * 机构名称
	 */
	@NotBlank(message = "机构名称不能为空")
	private String hospitalName;
	/**
	 * 直属上级ID
	 */
	private String parentId;
	/**
	 * 所有的上级ID
	 */
	private String parentsId;
	/**
	 * 级别
	 */
	private String grade;
	/**
	 * 所属区域
	 */
	private String regionId;

	/**
	 * 机构地址
	 */
	private String address;
	/**
	 * 机构经度
	 */
	private Double lng = 0.0;
	/**
	 * 机构纬度
	 */
	private Double lat = 0.0;
	
	/**
	 * 距离
	 */
	private String distance;
	
	/**
	 * 创建时间
	 */
	private String createDate;
	
	/**
	 * 简介
	 */
	private String introduction;
	/**
	 * logo小图
	 */
	private String logo;
	
	/**
	 * logo小图对应的biz_image记录
	 */
	private Image logoImage;

	/**
	 * 区域对象
	 */
	private Region region;
	
	/**
	 * 是否有子级机构
	 */
	private Boolean hasChild;
	
	/**
	 * 排序字段
	 */
	private Integer sort = 0;
	
	/**
	 * 联系电话
	 */
	private String contactPhone;
	
	/**
	 * 联系人
	 */
	private String contact;
	
	/**
	 * 固定联系电话
	 */
	private String contactPhone2;
	
	/**
	 * 固定联系人
	 */
	private String contact2;
	
	/**
	 * 机构类型
	 */
	private String hospitalType;
	private String isHighSalary;
	private String highSalaryStart;
	private String highSalaryEnd;
	private String registeredCapital;
	private String bln;
	private String peoples;
	private String technicalPatent;
	private List<AgencyOverview> agencyOverviews;

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentsId() {
		return parentsId;
	}

	public void setParentsId(String parentsId) {
		this.parentsId = parentsId;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Image getLogoImage() {
		return logoImage;
	}

	public void setLogoImage(Image logoImage) {
		this.logoImage = logoImage;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public List<AgencyOverview> getAgencyOverviews() {
		return agencyOverviews;
	}

	public void setAgencyOverviews(List<AgencyOverview> agencyOverviews) {
		this.agencyOverviews = agencyOverviews;
	}

	public Boolean getHasChild() {
		return hasChild;
	}

	public void setHasChild(Boolean hasChild) {
		this.hasChild = hasChild;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getHospitalType() {
		return hospitalType;
	}

	public void setHospitalType(String hospitalType) {
		this.hospitalType = hospitalType;
	}

	public String getContactPhone2() {
		return contactPhone2;
	}

	public void setContactPhone2(String contactPhone2) {
		this.contactPhone2 = contactPhone2;
	}

	public String getContact2() {
		return contact2;
	}

	public void setContact2(String contact2) {
		this.contact2 = contact2;
	}

	public String getIsHighSalary() {
		return isHighSalary;
	}

	public void setIsHighSalary(String isHighSalary) {
		this.isHighSalary = isHighSalary;
	}

	public String getHighSalaryStart() {
		return highSalaryStart;
	}

	public void setHighSalaryStart(String highSalaryStart) {
		this.highSalaryStart = highSalaryStart;
	}

	public String getHighSalaryEnd() {
		return highSalaryEnd;
	}

	public void setHighSalaryEnd(String highSalaryEnd) {
		this.highSalaryEnd = highSalaryEnd;
	}

	public String getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getBln() {
		return bln;
	}

	public void setBln(String bln) {
		this.bln = bln;
	}

	public String getPeoples() {
		return peoples;
	}

	public void setPeoples(String peoples) {
		this.peoples = peoples;
	}

	public String getTechnicalPatent() {
		return technicalPatent;
	}

	public void setTechnicalPatent(String technicalPatent) {
		this.technicalPatent = technicalPatent;
	}
	
}