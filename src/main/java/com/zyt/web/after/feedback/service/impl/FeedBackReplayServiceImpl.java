package com.zyt.web.after.feedback.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyt.web.after.feedback.service.FeedBackReplayService;
import com.zyt.web.publics.base.AbstractServiceImpl;
import com.zyt.web.publics.base.BaseDAO;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.feedback.bean.FeedBackReplay;
import com.zyt.web.publics.module.feedback.dao.FeedBackReplayDao;
import com.zyt.web.publics.module.image.bean.Image;
import com.zyt.web.publics.module.image.dao.ImageDao;
import com.zyt.web.publics.utils.UUIDUtils;


@Service
public class FeedBackReplayServiceImpl extends AbstractServiceImpl<FeedBackReplay> implements FeedBackReplayService {
    @Resource
	private FeedBackReplayDao feedBackReplayDao;
    
    @Autowired
	private ImageDao imageDao;
	
	@Override
	public BaseDAO<FeedBackReplay> dao() {
		return feedBackReplayDao;
	}
	
    @Override
	public void setId(FeedBackReplay t, String id) {
         t.setFeedBackReplayId(id);
	}
	
	@Override
	public Integer insert(FeedBackReplay feedBackReplay) {
		feedBackReplay.setFeedBackReplayId(UUIDUtils.getUUID());
		feedBackReplay.setReplayDate(new Date());
		return super.insert(feedBackReplay);
	}

	@Override
	public Integer update(FeedBackReplay feedBackReplay) {
		return super.update(feedBackReplay);
	}

	@Override
	public FeedBackReplay findObjectById(String id) {
		FeedBackReplay feedBackReplay =  super.findObjectById(id);
		if(StringUtils.isNotBlank(feedBackReplay.getImageId())){//循环查询图片实体
			List<Image> attachments = new ArrayList<Image>();
			String [] imageIds = feedBackReplay.getImageId().split(",");
			for (String imageId : imageIds) {
				Image attachment = imageDao.getImageById(imageId);
				if(attachment != null){
					attachments.add(attachment);
				}
			}
			feedBackReplay.setImageAttachments(attachments);
		}
		return feedBackReplay;
	}

	@Override
	public List<FeedBackReplay> findList(PaginationAble paginationAble) {
		List<FeedBackReplay> feedBackReplayList = super.findList(paginationAble);
		for (FeedBackReplay feedBackReplay : feedBackReplayList) {
			if(StringUtils.isNotBlank(feedBackReplay.getImageId())){//循环查询图片实体
				List<Image> attachments = new ArrayList<Image>();
				String [] imageIds = feedBackReplay.getImageId().split(",");
				for (String imageId : imageIds) {
					Image attachment = imageDao.getImageById(imageId);
					if(attachment != null){
						attachments.add(attachment);
					}
				}
				feedBackReplay.setImageAttachments(attachments);
			}
		}
		return feedBackReplayList;
	}

	@Override
	public List<FeedBackReplay> queryList(FeedBackReplay params) {
		List<FeedBackReplay> feedBackReplayList = super.queryList(params);
		for (FeedBackReplay feedBackReplay : feedBackReplayList) {
			if(StringUtils.isNotBlank(feedBackReplay.getImageId())){//循环查询图片实体
				List<Image> attachments = new ArrayList<Image>();
				String [] imageIds = feedBackReplay.getImageId().split(",");
				for (String imageId : imageIds) {
					Image attachment = imageDao.getImageById(imageId);
					if(attachment != null){
						attachments.add(attachment);
					}
				}
				feedBackReplay.setImageAttachments(attachments);
			}
		}
		return feedBackReplayList;
	}

	@Override
	public Integer delete(String[] ids) {
		return super.delete(ids);
	}

	@Override
	public Integer validate(FeedBackReplay feedBackReplay) {
		return super.validate(feedBackReplay);
	}
}
