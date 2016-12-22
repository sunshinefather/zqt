package com.zyt.web.publics.module.cms.bean;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;

/**
 * @Description:cms附件bean
 * @ClassName:  CmsAtt 
 * @author: sunshine  
 */
@Alias("CmsAtt")
public class CmsAtt implements Serializable {
	private static final long serialVersionUID = 1L;
	/**主键*/
	private String id;
	/**专家互动id*/
	private String contentId;
	/**附件id*/
	private String attId;
	private String attName;
	private String attUrl;
	public void setId( String id ) {
		this.id = id;
	}
	public String getId(){
		return id;
	}
	public void setContentId( String contentId ) {
		this.contentId = contentId;
	}
	public String getContentId(){
		return contentId;
	}
	public void setAttId( String attId ) {
		this.attId = attId;
	}
	public String getAttId(){
		return attId;
	}
	public void setAttName( String attName ) {
		this.attName = attName;
	}
	public String getAttName(){
		return attName;
	}
	public void setAttUrl( String attUrl ) {
		this.attUrl = attUrl;
	}
	public String getAttUrl(){
		return attUrl;
	}

    
}
