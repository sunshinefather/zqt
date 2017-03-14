package com.zyt.web.after.feedback.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zyt.web.after.feedback.service.FeedBackReplayService;
import com.zyt.web.after.feedback.service.FeedBackService;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.attachment.bean.Attachment;
import com.zyt.web.publics.module.attachment.dao.IAttachmentDao;
import com.zyt.web.publics.module.feedback.bean.FeedBack;
import com.zyt.web.publics.module.feedback.bean.FeedBackReplay;
import com.zyt.web.publics.module.feedback.dao.FeedBackDao;
import com.zyt.web.publics.module.sysmanager.dao.UserDao;
import com.zyt.web.publics.utils.UUIDUtils;

@Service
public class FeedBackServiceImpl  implements FeedBackService{
	@Resource
	Environment env;
	@Autowired
	private FeedBackDao feedBackDao;
	@Autowired
	private FeedBackReplayService feedBackReplayService;
	
	@Autowired
	private IAttachmentDao attachmentDao;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public List<FeedBack> findList(PaginationAble paginationAble) {
		 List<FeedBack> feedBacks = feedBackDao.findList(paginationAble, new RowBounds(paginationAble.getCurrentResult(), paginationAble.getPageSize()));
		 if (feedBacks != null && !feedBacks.isEmpty()) {
				 for (FeedBack feedBack : feedBacks) {
					feedBack.setUser(userDao.findUserById(feedBack.getMotherId()));
					if(StringUtils.isNotBlank(feedBack.getImageId())){//循环查询图片实体
						List<Attachment> attachments = new ArrayList<Attachment>();
						String [] imageIds = feedBack.getImageId().split(",");
						for (String imageId : imageIds) {
							Attachment attachment = attachmentDao.getById(imageId);
							if(attachment != null){
								attachments.add(attachment);
							}
						}
						feedBack.setImageAttachments(attachments);
					}
				 }
		 }
		return feedBacks;
	}

	@Override
	public FeedBack findById(String id) {
		FeedBack feedBack = feedBackDao.findById(id);
		if (feedBack != null) {
			if(StringUtils.isNotBlank(feedBack.getImageId())){//循环查询图片实体
				List<Attachment> attachments = new ArrayList<Attachment>();
				String [] imageIds = feedBack.getImageId().split(",");
				for (String imageId : imageIds) {
					Attachment attachment = attachmentDao.getById(imageId);
					if(attachment != null){
						attachments.add(attachment);
					}
				}
				feedBack.setImageAttachments(attachments);
			}
				feedBack.setUser(userDao.findUserById(feedBack.getMotherId()));
		}
		return feedBack;
	}
	
	@Override
	public Integer insertFeedBack(FeedBack feedBack) {
		feedBack.setCreateDate(new Date());
		feedBack.setFeedBackId(UUIDUtils.getUUID());
		
		return feedBackDao.insertFeedBack(feedBack);
	}
	
	@Override
	public Integer batchDelete(String[] ids) {
		return feedBackDao.batchDelete(ids);
	}

	@Override
	@Transactional
	public Integer feedbackReplay(FeedBackReplay feedBackReplay) {
		FeedBack feedBack = this.findById(feedBackReplay.getFeedBackId());
		if(feedBack != null){
			feedBack.setFeedBackId(feedBackReplay.getFeedBackId());
			feedBack.setReadState(false);
			feedBack.setWebReadState(true);
			feedBackDao.update(feedBack);
		}
		return feedBackReplayService.insert(feedBackReplay);
	}

	@Override
	public List<FeedBackReplay> feedbackReplayList(String feedBackId) {
		FeedBackReplay feedBackReplay = new FeedBackReplay();
		feedBackReplay.setFeedBackId(feedBackId);
		List<FeedBackReplay> feedBackReplays = feedBackReplayService.queryList(feedBackReplay);
		if(feedBackReplays != null){
				for (FeedBackReplay f : feedBackReplays) {
					if(StringUtils.isNotBlank(f.getReplayer())){
						f.setUser(userDao.findUserById(f.getReplayer()));
					}
				}
		}
		return feedBackReplays;
	}

	@Override
	public Integer update(FeedBack feedBack) {
		return feedBackDao.update(feedBack);
	}

}