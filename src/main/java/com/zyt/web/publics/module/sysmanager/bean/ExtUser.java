package com.zyt.web.publics.module.sysmanager.bean;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.NotBlank;

import com.zyt.web.publics.module.hospital.bean.Hospital;
import com.zyt.web.publics.module.image.bean.Image;
import com.zyt.web.publics.module.position.bean.Position;

@Alias("ExtUser")
public class ExtUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 全名
	 */
	private String fullName;
	
	/**
	 * 昵称
	 */
	private String nickName;
	
	/**
	 * 性别
	 */
	private String gender;
	
	/**
	 * 邮件
	 */
	private String email;
	
	/**
	 * 办公电话
	 */
	private String mobile;
	
	/**
	 * 是否对其他会员隐藏办公电话
	 */
	private String hideMobile;
	
	/**
	 * 社交账号
	 */
	private String socialAccount;
	
	/**
	 * 最后登录时间
	 */
	private Date lastActiveTime;
	
	/**
	 * 注册时间
	 */
	private Date registerTime;
	
	/**
	 * 支付账号
	 */
	private String alipayAccount;
	
	/**
	 * 头像
	 */
	private String avatar;
	
	/**
	 * 头像对应的biz_image表中记录
	 */
	private Image avatarImage;
	
	/**
	 * 个人简介
	 */
	private String biography;
	
	/**
	 * 用户状态 （在线与离线）
	 */
	private Boolean state;
	
	/**
	 * 职位
	 */
	private String position;
	/**
	 * 身份证
	 */
	@NotBlank(message = "身份证不能为空")
	private String idCard;
	/**
	 * 用户所属机构
	 */
	private String orgId;
	/**
	 * 用户所属机构对象
	 */
	private Hospital organization;
	/**
	 * 岗位描述
	 */
	private String descriptions;
	/**
	 * 专业
	 */
	private String professional;
	
	/**
	 * 座机电话
	 */
	private String tel;
	
	/**
	 * 座机电话2
	 */
	private String tel2;
	
	/**
	 * 传真
	 */
	private String fax;
	
	/**
	 * 传真2
	 */
	private String fax2;
	
	/**
	 * 电话2
	 */
	private String mobile2;
	
	/**
	 * 排序
	 */
	private Integer sort = 0;
	
	/**
	 * 岗位对象
	 */
	private Position positionBean;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSocialAccount() {
		return socialAccount;
	}

	public void setSocialAccount(String socialAccount) {
		this.socialAccount = socialAccount;
	}

	public Date getLastActiveTime() {
		return lastActiveTime;
	}

	public void setLastActiveTime(Date lastActiveTime) {
		this.lastActiveTime = lastActiveTime;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getAlipayAccount() {
		return alipayAccount;
	}

	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Hospital getOrganization() {
		return organization;
	}

	public void setOrganization(Hospital organization) {
		this.organization = organization;
	}
	
	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public String getHideMobile() {
		return hideMobile;
	}

	public void setHideMobile(String hideMobile) {
		this.hideMobile = hideMobile;
	}

	public Image getAvatarImage() {
		return avatarImage;
	}

	public void setAvatarImage(Image avatarImage) {
		this.avatarImage = avatarImage;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getFax2() {
		return fax2;
	}

	public void setFax2(String fax2) {
		this.fax2 = fax2;
	}

	public String getMobile2() {
		return mobile2;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
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

	public Position getPositionBean() {
		return positionBean;
	}

	public void setPositionBean(Position positionBean) {
		this.positionBean = positionBean;
	}
	
}
