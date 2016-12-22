package com.zyt.web.publics.module.attachment.bean;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.type.Alias;

/**
 * 附件
 */
@Alias("Attachment")
public class Attachment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String attachmentId;

	/**
	 * 文件物理保存地址
	 */
	private String saveAddr = "";

	/**
	 * 文件后缀
	 */
	private String fileSuffix;

	/**
	 * 文件名
	 */
	private String fileName;

	/**
	 * web访问地址
	 */
	private String webAddr;

	/**
	 * 附件类型 image 图片 document 文档 video 视频 audio 声频 other 其它 
	 */
	private String attachmentType;
	
	/**
	 * 小图地址
	 */
	private String miniImageUrl = "";
	
	/**
	 * @return the miniImageUrl
	 */
	public String getMiniImageUrl() {
		if (StringUtils.isNotEmpty(webAddr)) 
			miniImageUrl = StringUtils.substringBeforeLast(webAddr, ".") + "_mini." + fileSuffix;
		return miniImageUrl;
	}
	/**
	 * @param miniImageUrl the miniImageUrl to set
	 */
	public void setMiniImageUrl(String miniImageUrl) {
		this.miniImageUrl = miniImageUrl;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getSaveAddr() {
		return saveAddr;
	}

	public void setSaveAddr(String saveAddr) {
		this.saveAddr = saveAddr;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getWebAddr() {
		return webAddr;
	}

	public void setWebAddr(String webAddr) {
		this.webAddr = webAddr;
	}

	public String getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}

}
