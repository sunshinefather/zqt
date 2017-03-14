package com.zyt.web.after.feedback.service;

import java.util.List;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.feedback.bean.FeedBack;
import com.zyt.web.publics.module.feedback.bean.FeedBackReplay;

/**
 * 
 * @description 意见反馈服务层
 * @version 1.0
 */
public interface FeedBackService {
	
	
	/**
	 * 
	 *@Description: 分页查询意见反馈
	 *@param paginationAble
	 *@return List<FeedBack>
	 *@version: v1.0.0
	 */
	List<FeedBack> findList(PaginationAble paginationAble);
	
	/**
	 * 
	 *@Description: 根据ID查询意见反馈
	 *@param id
	 *@return FeedBack
	 *@version: v1.0.0
	 */
	FeedBack findById(String id);
	
	
	/**
	 * 添加
	 * @param FeedBack
	 * @return 
	 */
	Integer insertFeedBack(FeedBack feedBack);
	/**
	 * 
	 *@Description: 批量删除意见
	 *@param ids
	 *@return Integer
	 *@version: v1.0.0
	 */
	Integer batchDelete(String[] ids);
	
	/**
	 * 
	 *@Description: 回复意见反馈
	 *@param feedBackReplay
	 *@return
	 *@version: v1.0.0
	 */
	public Integer feedbackReplay(FeedBackReplay feedBackReplay);
	
	/**
	 * 
	 *@Description: 意见反馈回复列表
	 *@param feedBackId
	 *@return
	 *@version: v1.0.0
	 */
	public List<FeedBackReplay> feedbackReplayList(String feedBackId);
	
	/**
	 * 
	 *@Description: 更新意见反馈
	 *@param feedBack
	 *@return
	 *@version: v1.0.0
	 */
	public Integer update(FeedBack feedBack);
}
