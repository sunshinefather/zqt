package com.zyt.web.publics.module.feedback.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;
import com.zyt.web.publics.module.attachment.bean.Attachment;
import com.zyt.web.publics.module.sysmanager.bean.User;

/**
 * 
 * @author Kevin
 * @description 意见反馈回复实体
 * @version 1.0
 * @date 2016年04月19日
 */
@Alias("FeedBackReplay")
public class FeedBackReplay implements Serializable {
	private static final long serialVersionUID = 1L;
	/**主键*/
	private String feedBackReplayId;
	/**回复内容*/
	private String content;
	/**回复者*/
	private String replayer;
	/**意见反馈ID*/
	private String feedBackId;
	/**回复时间*/
	private Date replayDate;
	/**
	 * 图片ID 多个使用逗号隔开
	 */
	private String imageId;
	/**
	 * 图片实体列表
	 */
	private List<Attachment> imageAttachments;
	
	//回复用户
	private User user;
	public void setFeedBackReplayId( String feedBackReplayId ) {
		this.feedBackReplayId = feedBackReplayId;
	}
	public String getFeedBackReplayId(){
		return feedBackReplayId;
	}
	public void setContent( String content ) {
		this.content = content;
	}
	public String getContent(){
		return content;
	}
	public void setReplayer( String replayer ) {
		this.replayer = replayer;
	}
	public String getReplayer(){
		return replayer;
	}
	public Date getReplayDate() {
		return replayDate;
	}
	public void setReplayDate(Date replayDate) {
		this.replayDate = replayDate;
	}
	public String getFeedBackId() {
		return feedBackId;
	}
	public void setFeedBackId(String feedBackId) {
		this.feedBackId = feedBackId;
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