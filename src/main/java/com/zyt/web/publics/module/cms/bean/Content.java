package com.zyt.web.publics.module.cms.bean;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.zyt.web.publics.module.attachment.bean.Attachment;

/**
 * @Description:内容管理bean
 * @ClassName:  Content 
 * @author: sunshine  
 */
@Alias("Content")
public class Content implements Serializable {
	private static final long serialVersionUID = 1L;
	/**主键*/
	private String id;
	private String title;
	private String summary;
	private String logo;
	private String content;
	private String publisher;
	private String crateTime;
	private String categoryId;
	private String hospitalId;
	private String publishState;
	private String str1;
	private String str2;
	private Attachment attachment;
	private List<CmsAtt> atts;
	public void setId( String id ) {
		this.id = id;
	}
	public String getId(){
		return id;
	}
	public void setTitle( String title ) {
		this.title = title;
	}
	public String getTitle(){
		return title;
	}
	public void setSummary( String summary ) {
		this.summary = summary;
	}
	public String getSummary(){
		return summary;
	}
	public void setLogo( String logo ) {
		this.logo = logo;
	}
	public String getLogo(){
		return logo;
	}
	public void setContent( String content ) {
		this.content = content;
	}
	public String getContent(){
		return content;
	}
	public void setPublisher( String publisher ) {
		this.publisher = publisher;
	}
	public String getPublisher(){
		return publisher;
	}
	public void setCrateTime( String crateTime ) {
		this.crateTime = crateTime;
	}
	public String getCrateTime(){
		return crateTime;
	}
	public void setCategoryId( String categoryId ) {
		this.categoryId = categoryId;
	}
	public String getCategoryId(){
		return categoryId;
	}
	public void setHospitalId( String hospitalId ) {
		this.hospitalId = hospitalId;
	}
	public String getHospitalId(){
		return hospitalId;
	}
	public void setPublishState( String publishState ) {
		this.publishState = publishState;
	}
	public String getPublishState(){
		return publishState;
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
	public Attachment getAttachment() {
		return attachment;
	}
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
	
	public List<CmsAtt> getAtts() {
		return atts;
	}
	
	public void setAtts(List<CmsAtt> atts) {
		this.atts = atts;
	}
	
}
