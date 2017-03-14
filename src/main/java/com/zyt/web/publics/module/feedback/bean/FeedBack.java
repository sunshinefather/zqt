package com.zyt.web.publics.module.feedback.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;
import com.zyt.web.publics.module.attachment.bean.Attachment;
import com.zyt.web.publics.module.sysmanager.bean.User;

/**
 * 意见反馈
 */
@Alias("FeedBack")
public class FeedBack implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String feedBackId;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 反馈者
	 */
	private String motherId;

	/**
	 * 反馈时间
	 */
	private Date createDate;
	
	/**
	 * 客户端阅读状态
	 */
	private Boolean readState;
	
	/**
	 * web端阅读状态
	 */
	private Boolean webReadState;
	
	/**
	 * 图片ID 多个使用逗号隔开
	 */
	private String imageId;
	
	private User user;
	
	/**
	 * 图片实体列表
	 */
	private List<Attachment> imageAttachments;

	public String getFeedBackId() {
		return feedBackId;
	}

	public void setFeedBackId(String feedBackId) {
		this.feedBackId = feedBackId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMotherId() {
		return motherId;
	}

	public void setMotherId(String motherId) {
		this.motherId = motherId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Boolean getReadState() {
		return readState;
	}

	public void setReadState(Boolean readState) {
		this.readState = readState;
	}

	public Boolean getWebReadState() {
		return webReadState;
	}

	public void setWebReadState(Boolean webReadState) {
		this.webReadState = webReadState;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public List<Attachment> getImageAttachments() {
		return imageAttachments;
	}

	public void setImageAttachments(List<Attachment> imageAttachments) {
		this.imageAttachments = imageAttachments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
