package com.zyt.web.after.api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyt.web.after.api.service.FeedBackApiService;
import com.zyt.web.after.feedback.service.FeedBackReplayService;
import com.zyt.web.after.feedback.service.FeedBackService;
import com.zyt.web.publics.base.JsonEntity;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.feedback.bean.FeedBack;
import com.zyt.web.publics.module.feedback.bean.FeedBackReplay;
import com.zyt.web.publics.module.image.bean.Image;
import com.zyt.web.publics.module.sysmanager.bean.User;

@Service
public class FeedBackApiServiceImpl implements FeedBackApiService {
	@Autowired
	private FeedBackService feedBackService;
	@Autowired
	private FeedBackReplayService feedBackReplayService;
	
	@Override
	public JsonEntity insertFeedBack(FeedBack feedBack, User user) {
		JsonEntity jsonEntity = new JsonEntity();
		if(user == null){
			jsonEntity.setMsg("登录已失效");
			jsonEntity.setStatus(JsonEntity.fail);
		}else{
			if(feedBack != null){
				feedBack.setReadState(true);
				feedBack.setWebReadState(false);
				feedBack.setMotherId(user.getId());
				feedBackService.insertFeedBack(feedBack);
			}
		}
		return jsonEntity;
	}

	@Override
	public JsonEntity replayFeedBack(FeedBackReplay feedBackReplay, User user) {
		JsonEntity jsonEntity = new JsonEntity();
		if(user == null){
			jsonEntity.setMsg("登录已失效");
			jsonEntity.setStatus(JsonEntity.fail);
		}else{
			FeedBack feedBack = new FeedBack();
			feedBack.setFeedBackId(feedBackReplay.getFeedBackId());
			feedBack.setWebReadState(false);
			feedBackService.update(feedBack);
			
			feedBackReplay.setReplayer(user.getId());
			feedBackReplayService.insert(feedBackReplay);
		}
		return jsonEntity;
	}

	@Override
	public JsonEntity replayFeedBackList(String feedBackId, User user) {
		JsonEntity jsonEntity = new JsonEntity();
		if(user == null){
			jsonEntity.setMsg("登录已失效");
			jsonEntity.setStatus(JsonEntity.fail);
		}else{
			FeedBack feedBack = new FeedBack();
			feedBack.setFeedBackId(feedBackId);
			feedBack.setReadState(true);
			feedBackService.update(feedBack);
			List<FeedBackReplay>  feedBackReplays = feedBackService.feedbackReplayList(feedBackId);
			if(feedBackReplays != null){
				List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
				for (FeedBackReplay feedBackReplay : feedBackReplays) {
					Map<String,Object> data = new HashMap<String,Object>();
					data.put("content",feedBackReplay.getContent());
					data.put("replayDate",feedBackReplay.getReplayDate());
					data.put("replayerName",feedBackReplay.getUser() != null?feedBackReplay.getUser().getExtUser() != null?feedBackReplay.getUser().getExtUser().getFullName():null:null);
					data.put("replayer",feedBackReplay.getReplayer());
					
					List<String> imageList = new ArrayList<String>();
					if(feedBackReplay.getImageAttachments() != null && feedBackReplay.getImageAttachments().size() > 0){
						for (Image attachment: feedBackReplay.getImageAttachments()) {
							imageList.add(attachment.getWebAdd());
						}
					}
					data.put("imageList",imageList);
					
					datas.add(data);
				}
				jsonEntity.setData(datas);
			}
		}
		return jsonEntity;
	}

	@Override
	public JsonEntity list(User user,PaginationAble page) {
		JsonEntity jsonEntity = new JsonEntity();
		if(user == null){
			jsonEntity.setMsg("登录已失效");
			jsonEntity.setStatus(JsonEntity.fail);
		}else{
			page.getWhereParameters().put("motherId",user.getId());
			page.getWhereParameters().put("app",true);
			List<FeedBack>  feedBacks = feedBackService.findList(page);
			List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
			for (FeedBack feedBack : feedBacks) {
				Map<String,Object> data = new HashMap<String,Object>();
				data.put("content",feedBack.getContent());
				data.put("createDate",feedBack.getCreateDate());
				data.put("motherName",feedBack.getUser() != null?feedBack.getUser().getExtUser() != null?feedBack.getUser().getExtUser().getFullName():null:null);
				data.put("motherId",feedBack.getMotherId());
				data.put("feedBackId",feedBack.getFeedBackId());
				data.put("readState",feedBack.getReadState() == null?false:feedBack.getReadState());
				
				List<String> imageList = new ArrayList<String>();
				if(feedBack.getImageAttachments() != null && feedBack.getImageAttachments().size() > 0){
					for (Image attachment: feedBack.getImageAttachments()) {
						imageList.add(attachment.getWebAdd());
					}
				}
				data.put("imageList",imageList);
				
				List<Map<String,Object>> replays = new ArrayList<Map<String,Object>>();
				if(page.getWhereParameters().get("include") != null 
						&& StringUtils.isNotBlank(page.getWhereParameters().get("include").toString())){
					List<FeedBackReplay>  feedBackReplays = feedBackService.feedbackReplayList(feedBack.getFeedBackId());
					if(feedBackReplays != null){
						for (FeedBackReplay feedBackReplay : feedBackReplays) {
							Map<String,Object> replay = new HashMap<String,Object>();
							replay.put("content",feedBackReplay.getContent());
							replay.put("replayDate",feedBackReplay.getReplayDate());
							replay.put("replayerName",feedBackReplay.getUser() != null?feedBackReplay.getUser().getExtUser() != null?feedBackReplay.getUser().getExtUser().getFullName():null:null);
							replay.put("replayer",feedBackReplay.getReplayer());
							
							List<String> replatImageList = new ArrayList<String>();
							if(feedBackReplay.getImageAttachments() != null && feedBackReplay.getImageAttachments().size() > 0){
								for (Image attachment: feedBackReplay.getImageAttachments()) {
									replatImageList.add(attachment.getWebAdd());
								}
							}
							replay.put("imageList",replatImageList);
							
							replays.add(replay);
						}
						jsonEntity.setData(replays);
					}
				}
				data.put("replay",replays);
				datas.add(data);
			}
			jsonEntity.setData(datas);
			jsonEntity.setTotals(page.getTotalResults());
		}
		return jsonEntity;
	}

	@Override
	public JsonEntity updateReadState(String feedBackId, User user) {
		JsonEntity jsonEntity = new JsonEntity();
		if(user == null){
			jsonEntity.setMsg("登录已失效");
			jsonEntity.setStatus(JsonEntity.fail);
		}else{
			FeedBack feedBack = new FeedBack();
			feedBack.setReadState(true);
			feedBack.setFeedBackId(feedBackId);
			feedBackService.update(feedBack);
		}
		return jsonEntity;
	}
	
}
