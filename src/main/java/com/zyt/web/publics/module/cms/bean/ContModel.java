package com.zyt.web.publics.module.cms.bean;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

/**
 * @Description:内容分类bean
 * @ClassName:  ContModel 
 * @author: sunshine  
 */
@Alias("ContModel")
public class ContModel implements Serializable {
	private static final long serialVersionUID = 1L;
	/**主键*/
	private String id;
	private String hospitalId;
	private String name;
	private String parentId;
	private String navigateId;
	private String navigateName;
	private String levelSeq;
	private String levelIndex;
	/**层级格式编码,方便搜索*/
	private String formatCode;
	private String str1;
	private String str2;
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
	public void setParentId( String parentId ) {
		this.parentId = parentId;
	}
	public String getParentId(){
		return parentId;
	}
	public void setNavigateId( String navigateId ) {
		this.navigateId = navigateId;
	}
	public String getNavigateId(){
		return navigateId;
	}
	public void setLevelSeq( String levelSeq ) {
		this.levelSeq = levelSeq;
	}
	public String getLevelSeq(){
		return levelSeq;
	}
	public void setLevelIndex( String levelIndex ) {
		this.levelIndex = levelIndex;
	}
	public String getLevelIndex(){
		return levelIndex;
	}
	public void setFormatCode( String formatCode ) {
		this.formatCode = formatCode;
	}
	public String getFormatCode(){
		return formatCode;
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
	public String getNavigateName() {
		return navigateName;
	}
	public void setNavigateName(String navigateName) {
		this.navigateName = navigateName;
	}
    
}
