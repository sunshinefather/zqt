package com.zyt.web.publics.module.support.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;

/**
 * @Description:企业获得支持bean
 * @ClassName:  ObtainSupport 
 * @author: sunshine  
 */
@Alias("ObtainSupport")
public class ObtainSupport implements Serializable {
	private static final long serialVersionUID = 1L;
	/**主键*/
	private String id;
	/**企业id*/
	private String hospitalId;
	/**获得年*/
	private String obtainYear;
	/**产值*/
	private BigDecimal money;
	private String str1;
	private BigDecimal str2;
	private BigDecimal str3;
	private BigDecimal str4;
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
	public void setObtainYear( String obtainYear ) {
		this.obtainYear = obtainYear;
	}
	public String getObtainYear(){
		return obtainYear;
	}
	public void setMoney( BigDecimal money ) {
		this.money = money;
	}
	public BigDecimal getMoney(){
		return money;
	}
	public void setStr1(String str1 ) {
		this.str1 = str1;
	}
	public String getStr1(){
		return str1;
	}
	public void setStr2( BigDecimal str2 ) {
		this.str2 = str2;
	}
	public BigDecimal getStr2(){
		return str2;
	}
	public void setStr3( BigDecimal str3 ) {
		this.str3 = str3;
	}
	public BigDecimal getStr3(){
		return str3;
	}
	public void setStr4( BigDecimal str4 ) {
		this.str4 = str4;
	}
	public BigDecimal getStr4(){
		return str4;
	}

    
}
