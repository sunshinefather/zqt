package com.zyt.web.publics.module.config.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.config.bean.SysLog;

/**
 * @author 系统日志
 */
public interface SysLogDao {

	/**
	 * @描述  添加系统日志
	 * @author maliang
	 * @time 2014-3-13 上午11:35:29
	 * @version
	 * @param log
	 * @return
	 */
	public int insertLog(SysLog log);

	/**
	 * @描述
	 * @author maliang
	 * @time 2014-3-18 下午5:25:43
	 * @version v1.0
	 * @param page
	 * @param bounds
	 * @return
	 */
	public List<SysLog> getLogsPage(PaginationAble page, RowBounds bounds);

	/**
	 * @描述 批量删除日志信息
	 * @author maliang
	 * @time 2014-3-19 上午9:29:00
	 * @version v1.0
	 * @param logIds
	 * @return
	 */
	public int deleteLogsByIds(String[] logIds);

}
