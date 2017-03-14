package com.zyt.web.after.api.service;

import com.zyt.web.publics.base.JsonEntity;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.feedback.bean.FeedBack;
import com.zyt.web.publics.module.feedback.bean.FeedBackReplay;
import com.zyt.web.publics.module.sysmanager.bean.User;

public interface FeedBackApiService {
	public JsonEntity insertFeedBack(FeedBack feedBack, User user);
	
	/**
	 * 
	 *@Description: 回复意见反馈
	 *@param feedBackReplay
	 *@param user
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2016年4月20日上午11:40:40
	 */
	public JsonEntity replayFeedBack(FeedBackReplay feedBackReplay, User user);
	
	/**
	 * 
	 *@Description: 意见反馈回复列表
	 *@param feedBackId
	 *@param user
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2016年4月20日上午11:46:42
	 */
	public JsonEntity replayFeedBackList(String feedBackId, User user);
	
	/**
	 * 
	 *@Description: 查询意见反馈列表
	 *@param user
	 *@param page 
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2016年4月20日下午1:59:50
	 */
	public JsonEntity list(User user, PaginationAble page);
	
	/**
	 * 
	 *@Description: 更新阅读状态
	 *@param feedBackId
	 *@param user
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2016年4月20日下午2:17:03
	 */
	public JsonEntity updateReadState(String feedBackId, User user);
}
