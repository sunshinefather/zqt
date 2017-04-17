package com.zyt.web.publics.module.product.bean;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

/**
 * @Description:企业产品bean
 * @ClassName:  Product 
 * @author: sunshine  
 */
@Alias("Product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	/**主键*/
	private String id;
	/**企业id*/
	private String hospitalId;
	/**获得年*/
	private String name;
	/**介绍*/
	private String introduce;
	/**产品图片*/
	private String logo;
	private String str1;
	private String str2;
	private String hospitalName;//企业名称
	public void setId( String id ) {
		this.id = id;
	}
	public String getId(){
		return id;
	}
	public void setHospitalId( String hospitalId ) {
		this.hospitalId = hospitalId;
	}
	public String getHospitalId(){
		return hospitalId;
	}
	public void setName( String name ) {
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setIntroduce( String introduce ) {
		this.introduce = introduce;
	}
	public String getIntroduce(){
		return introduce;
	}
	public void setLogo( String logo ) {
		this.logo = logo;
	}
	public String getLogo(){
		return logo;
	}
	public void setStr1( String str1 ) {
		this.str1 = str1;
	}
	public String getStr1(){
		return str1;
	}
	public void setStr2( String str2 ) {
		this.str2 = str2;
	}
	public String getStr2(){
		return str2;
	}

	public String getHospitalName() {
		return hospitalName;
	}
 
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

    
}
