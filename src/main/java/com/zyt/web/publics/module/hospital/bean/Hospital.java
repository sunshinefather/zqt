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
	private String hospitalType;//类型
	private String isHighSalary;//是否高薪企业
	private String highSalaryStart;//高薪企业获得时间
	private String highSalaryEnd;//高薪企业结束时间
	private String registeredCapital;//注册资本
	private String bln;//营业执照号
	private String blnImg;//营业执照证件
	private String peoples;//企业人数
	private String technicalPatent;//技术专利
	private String shuiwuDJ;//税务登记证号码
	private String shuiwuDJImg;//税务登记证图
	private String orgNum;//组织机构代码
	private String orgImg;//组织机构图
	private String officeAdd;//办公地址
	private String industryCategory;//行业分类
	private String manageCategorry;//管理分类
	
	private String guotut1;//国土证时间1
	private String guotumj1;//国土证面积1
	private String guotuNum1;//国土证1土体证号
	
	private String guotut2;//国土证时间1
	private String guotumj2;//国土证面积1
	private String guotuNum2;//国土证1土体证号
	
	private String guotut3;//国土证时间1
	private String guotumj3;//国土证面积1
	private String guotuNum3;//国土证1土体证号
	
	private String guotut4;//国土证时间1
	private String guotumj4;//国土证面积1
	private String guotuNum4;//国土证1土体证号
	
	private String guotut5;//国土证时间1
	private String guotumj5;//国土证面积1
	private String guotuNum5;//国土证1土体证号
	
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

	/**  
	 * @Title:  getBlnImg <BR>    
	 * @return: String <BR>  
	 */
	public String getBlnImg() {
		return blnImg;
	}

	/**  
	 * @Title:  setBlnImg <BR>   
	 * @return: String <BR> 
	 */
	public void setBlnImg(String blnImg) {
		this.blnImg = blnImg;
	}

	/**  
	 * @Title:  getShuiwuDJ <BR>    
	 * @return: String <BR>  
	 */
	public String getShuiwuDJ() {
		return shuiwuDJ;
	}

	/**  
	 * @Title:  setShuiwuDJ <BR>   
	 * @return: String <BR> 
	 */
	public void setShuiwuDJ(String shuiwuDJ) {
		this.shuiwuDJ = shuiwuDJ;
	}

	/**  
	 * @Title:  getShuiwuDJImg <BR>    
	 * @return: String <BR>  
	 */
	public String getShuiwuDJImg() {
		return shuiwuDJImg;
	}

	/**  
	 * @Title:  setShuiwuDJImg <BR>   
	 * @return: String <BR> 
	 */
	public void setShuiwuDJImg(String shuiwuDJImg) {
		this.shuiwuDJImg = shuiwuDJImg;
	}

	/**  
	 * @Title:  getOrgNum <BR>    
	 * @return: String <BR>  
	 */
	public String getOrgNum() {
		return orgNum;
	}

	/**  
	 * @Title:  setOrgNum <BR>   
	 * @return: String <BR> 
	 */
	public void setOrgNum(String orgNum) {
		this.orgNum = orgNum;
	}

	/**  
	 * @Title:  getOrgImg <BR>    
	 * @return: String <BR>  
	 */
	public String getOrgImg() {
		return orgImg;
	}

	/**  
	 * @Title:  setOrgImg <BR>   
	 * @return: String <BR> 
	 */
	public void setOrgImg(String orgImg) {
		this.orgImg = orgImg;
	}

	/**  
	 * @Title:  getOfficeAdd <BR>    
	 * @return: String <BR>  
	 */
	public String getOfficeAdd() {
		return officeAdd;
	}

	/**  
	 * @Title:  setOfficeAdd <BR>   
	 * @return: String <BR> 
	 */
	public void setOfficeAdd(String officeAdd) {
		this.officeAdd = officeAdd;
	}

	/**  
	 * @Title:  getIndustryCategory <BR>    
	 * @return: String <BR>  
	 */
	public String getIndustryCategory() {
		return industryCategory;
	}

	/**  
	 * @Title:  setIndustryCategory <BR>   
	 * @return: String <BR> 
	 */
	public void setIndustryCategory(String industryCategory) {
		this.industryCategory = industryCategory;
	}

	/**  
	 * @Title:  getManageCategorry <BR>    
	 * @return: String <BR>  
	 */
	public String getManageCategorry() {
		return manageCategorry;
	}

	/**  
	 * @Title:  setManageCategorry <BR>   
	 * @return: String <BR> 
	 */
	public void setManageCategorry(String manageCategorry) {
		this.manageCategorry = manageCategorry;
	}

	/**  
	 * @Title:  getGuotut1 <BR>    
	 * @return: String <BR>  
	 */
	public String getGuotut1() {
		return guotut1;
	}

	/**  
	 * @Title:  setGuotut1 <BR>   
	 * @return: String <BR> 
	 */
	public void setGuotut1(String guotut1) {
		this.guotut1 = guotut1;
	}

	/**  
	 * @Title:  getGuotumj1 <BR>    
	 * @return: String <BR>  
	 */
	public String getGuotumj1() {
		return guotumj1;
	}

	/**  
	 * @Title:  setGuotumj1 <BR>   
	 * @return: String <BR> 
	 */
	public void setGuotumj1(String guotumj1) {
		this.guotumj1 = guotumj1;
	}

	/**  
	 * @Title:  getGuotuNum1 <BR>    
	 * @return: String <BR>  
	 */
	public String getGuotuNum1() {
		return guotuNum1;
	}

	/**  
	 * @Title:  setGuotuNum1 <BR>   
	 * @return: String <BR> 
	 */
	public void setGuotuNum1(String guotuNum1) {
		this.guotuNum1 = guotuNum1;
	}

	/**  
	 * @Title:  getGuotut2 <BR>    
	 * @return: String <BR>  
	 */
	public String getGuotut2() {
		return guotut2;
	}

	/**  
	 * @Title:  setGuotut2 <BR>   
	 * @return: String <BR> 
	 */
	public void setGuotut2(String guotut2) {
		this.guotut2 = guotut2;
	}

	/**  
	 * @Title:  getGuotumj2 <BR>    
	 * @return: String <BR>  
	 */
	public String getGuotumj2() {
		return guotumj2;
	}

	/**  
	 * @Title:  setGuotumj2 <BR>   
	 * @return: String <BR> 
	 */
	public void setGuotumj2(String guotumj2) {
		this.guotumj2 = guotumj2;
	}

	/**  
	 * @Title:  getGuotuNum2 <BR>    
	 * @return: String <BR>  
	 */
	public String getGuotuNum2() {
		return guotuNum2;
	}

	/**  
	 * @Title:  setGuotuNum2 <BR>   
	 * @return: String <BR> 
	 */
	public void setGuotuNum2(String guotuNum2) {
		this.guotuNum2 = guotuNum2;
	}

	/**  
	 * @Title:  getGuotut3 <BR>    
	 * @return: String <BR>  
	 */
	public String getGuotut3() {
		return guotut3;
	}

	/**  
	 * @Title:  setGuotut3 <BR>   
	 * @return: String <BR> 
	 */
	public void setGuotut3(String guotut3) {
		this.guotut3 = guotut3;
	}

	/**  
	 * @Title:  getGuotumj3 <BR>    
	 * @return: String <BR>  
	 */
	public String getGuotumj3() {
		return guotumj3;
	}

	/**  
	 * @Title:  setGuotumj3 <BR>   
	 * @return: String <BR> 
	 */
	public void setGuotumj3(String guotumj3) {
		this.guotumj3 = guotumj3;
	}

	/**  
	 * @Title:  getGuotuNum3 <BR>    
	 * @return: String <BR>  
	 */
	public String getGuotuNum3() {
		return guotuNum3;
	}

	/**  
	 * @Title:  setGuotuNum3 <BR>   
	 * @return: String <BR> 
	 */
	public void setGuotuNum3(String guotuNum3) {
		this.guotuNum3 = guotuNum3;
	}

	/**  
	 * @Title:  getGuotut4 <BR>    
	 * @return: String <BR>  
	 */
	public String getGuotut4() {
		return guotut4;
	}

	/**  
	 * @Title:  setGuotut4 <BR>   
	 * @return: String <BR> 
	 */
	public void setGuotut4(String guotut4) {
		this.guotut4 = guotut4;
	}

	/**  
	 * @Title:  getGuotumj4 <BR>    
	 * @return: String <BR>  
	 */
	public String getGuotumj4() {
		return guotumj4;
	}

	/**  
	 * @Title:  setGuotumj4 <BR>   
	 * @return: String <BR> 
	 */
	public void setGuotumj4(String guotumj4) {
		this.guotumj4 = guotumj4;
	}

	/**  
	 * @Title:  getGuotuNum4 <BR>    
	 * @return: String <BR>  
	 */
	public String getGuotuNum4() {
		return guotuNum4;
	}

	/**  
	 * @Title:  setGuotuNum4 <BR>   
	 * @return: String <BR> 
	 */
	public void setGuotuNum4(String guotuNum4) {
		this.guotuNum4 = guotuNum4;
	}

	public String getGuotut5() {
		return guotut5;
	}

	public void setGuotut5(String guotut5) {
		this.guotut5 = guotut5;
	}

	public String getGuotumj5() {
		return guotumj5;
	}

	public void setGuotumj5(String guotumj5) {
		this.guotumj5 = guotumj5;
	}

	public String getGuotuNum5() {
		return guotuNum5;
	}

	public void setGuotuNum5(String guotuNum5) {
		this.guotuNum5 = guotuNum5;
	}
	
}