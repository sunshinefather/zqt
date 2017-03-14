package com.zyt.web.after.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zyt.web.after.api.service.FeedBackApiService;
import com.zyt.web.publics.base.JsonEntity;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.feedback.bean.FeedBack;
import com.zyt.web.publics.module.feedback.bean.FeedBackReplay;
import com.zyt.web.publics.module.sysmanager.bean.User;

@Controller
@RequestMapping(value = "api/feedBack")
public class FeedBackApiController extends ApiController{
	@Autowired
	private FeedBackApiService feedBackApiService;

	/**
	 * 添加意见反馈
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public JsonEntity insertFeedBack(@RequestParam(required = true)String token, FeedBack feedBack) {
		User user = super.getUser(token);
		return feedBackApiService.insertFeedBack(feedBack, user);
	}
	
	/**
	 * 
	 *@Description: 查询意见反馈列表
	 *@param token
	 *@param include 此参数有值时，表示同时获取意见反馈的列表
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2016年4月20日下午1:59:27
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public @ResponseBody JsonEntity list(@RequestParam(required = true)String token,PaginationAble page) {
		User user = super.getUser(token);
		page.setWhereParameters(getParams());
		return feedBackApiService.list(user,page);
	}
	
	/**
	 * 
	 *@Description: 设置意见反馈为已读
	 *@param feedBackId
	 *@param token
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2016年4月20日下午2:15:59
	 */
	@RequestMapping(value = "/updateReadState",method = RequestMethod.POST)
	public @ResponseBody JsonEntity updateReadState(String feedBackId,@RequestParam(required = true)String token){
		User user = super.getUser(token);
		return feedBackApiService.updateReadState(feedBackId,user);
	}
	
	/**
	 * 
	 *@Description: 回复意见反馈
	 *@param token
	 *@param feedBackReplay
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2016年4月20日上午11:40:07
	 */
	@RequestMapping(value = "/replay",method = RequestMethod.POST)
	@ResponseBody
	public JsonEntity replayFeedBack(@RequestParam(required = true)String token, FeedBackReplay feedBackReplay) {
		User user = super.getUser(token);
		return feedBackApiService.replayFeedBack(feedBackReplay, user);
	}
	
	/**
	 * 
	 *@Description: 查询意见反馈回复列表
	 *@param token
	 *@param feedBackId
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2016年4月20日上午11:46:17
	 */
	@RequestMapping(value = "/replayList",method = RequestMethod.GET)
	public @ResponseBody JsonEntity replayFeedBackList(@RequestParam(required = true)String token,
			@RequestParam(required = true)String feedBackId){
		User user = super.getUser(token);
		return feedBackApiService.replayFeedBackList(feedBackId,user);
	}
}
