package com.zyt.web.after.config.service;

import java.util.List;
import java.util.Map;

import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.config.bean.SysLog;

/**
 * 系统日志
 * 
 * @author maliang
 */
public interface SysLogsService {

	/**
	 * @描述 TODO 插入日志记录
	 * @author maliang
	 * @time 2014-3-13 上午11:47:06
	 * @version
	 * @param log
	 * @return
	 */
	public Map<String, Object> insertLog(SysLog log);

	/**
	 * @描述 TODO 分页获取日志信息
	 * @author maliang
	 * @time 2014-3-17 上午10:01:07
	 * @version
	 * @param paginationAble
	 * @param log
	 * @return
	 */
	public List<SysLog> getLogsPage(PaginationAble page,
			Map<String, Object> params);

	/**
	 * @描述 TODO 根据ID删除日志信息
	 * @author maliang
	 * @time 2014-3-17 上午10:01:54
	 * @version
	 * @param logId
	 * @return
	 */
	@Deprecated
	public boolean deleteLogById(String logId);

	/**
	 * @描述 TODO 根据ID的数组批量删除日志信息
	 * @author maliang
	 * @time 2014-3-19 上午9:28:01
	 * @version v1.0
	 * @param logIds
	 * @return
	 */
	public Map<String, Object> deleteLogsByIds(String[] logIds);

}
