package com.zyt.web.publics.module.feedback.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.feedback.bean.FeedBack;

public interface FeedBackDao {
	
	/**
	 * 
	 *@Description: 分页查询意见反馈列表
	 *@param paginationAble
	 *@param row
	 *@return List<FeedBack>
	 */
	List<FeedBack> findList(PaginationAble paginationAble,RowBounds row);
	
	/**
	 * 
	 *@Description: 根据ID查询反馈意见
	 *@param id
	 *@return FeedBack
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
	 *@Description: 批量删除反馈信息
	 *@param ids
	 *@return Integer
	 */
	Integer batchDelete(@Param("ids")String[] ids);
	
	/**
	 * 
	 *@Description: 更新
	 *@param feedBack
	 *@return
	 */
	public Integer update(FeedBack feedBack);
}
