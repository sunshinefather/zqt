package com.zyt.web.after.feedback.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zyt.web.after.feedback.service.FeedBackService;
import com.zyt.web.publics.base.BaseController;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.feedback.bean.FeedBackReplay;

/**
 * 
 * @author Kevin
 * @description 意见反馈
 * @version 1.0
 * @date 2015年8月27日
 */
@Controller
@RequestMapping(value="after/feedback")
public class FeedBackController extends BaseController{
	
	@Autowired
	private FeedBackService feedBackService;
	
	/**
	 * 
	 *@Description: 分页获取反馈列表
	 *@param model
	 *@param page
	 *@return String
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-6-27下午3:55:58
	 */
	@RequestMapping(value="feedback_base_list",method = RequestMethod.GET)
	public String list(Model model,PaginationAble page){
	    page.setWhereParameters(getParams(model));
		model.addAttribute("list",feedBackService.findList(page));
		model.addAttribute("page", page);
		return "after/feedback/feedback_base_list";
	}
	
	
	/**
	 * 
	 *@Description: 查询意见反馈详情
	 *@param model
	 *@param id
	 *@return String
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-6-30上午9:24:58
	 */
	@RequestMapping(value="feedback_base_detail/{id}",method=RequestMethod.GET)
	public String modify(Model model,@PathVariable String id){
		model.addAttribute("obj",feedBackService.findById(id));
		return "after/feedback/feedback_base_detail";
	}
	
	/**
	 * 
	 *@Description: 批量删除意见反馈
	 *@param ids
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-6-30下午3:14:00
	 */
	@RequestMapping(value="feedback_base_batchDelete",method=RequestMethod.POST)
	public @ResponseBody Integer batchDelete(String ids){
		Assert.notNull(ids);
		return feedBackService.batchDelete(ids.split(","));
	}
	
	/**
	 * 
	 *@Description: 回复
	 *@param feedBack
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2016年4月18日下午3:51:25
	 */
	@RequestMapping(value="feedback_base_replay",method=RequestMethod.POST)
	public @ResponseBody Integer feedbackReplay(FeedBackReplay feedBackReplay){
		feedBackReplay.setReplayer(getUser().getId());
		return feedBackService.feedbackReplay(feedBackReplay);
	}
	
	/**
	 * 
	 *@Description: 根据意见反馈ID查询回复列表
	 *@param feedBackId 意见反馈ID
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2016年4月20日上午11:24:51
	 */
	@RequestMapping(value="feedback_replay_list",method=RequestMethod.GET)
	public @ResponseBody List<FeedBackReplay> feedbackReplayList(String feedBackId){
		return feedBackService.feedbackReplayList(feedBackId);
	}
	
	
	/**
	 * 
	 *@Description: 跳转界面
	 *@param path
	 *@return
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2016年11月8日下午2:44:22
	 */
	@RequestMapping(value = "forward")
	public  String forward(String path){
		return path;
	}
}
