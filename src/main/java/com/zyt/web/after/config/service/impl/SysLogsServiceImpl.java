package com.zyt.web.after.config.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zyt.web.after.config.service.SysLogsService;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.config.bean.SysLog;
import com.zyt.web.publics.module.config.dao.SysLogDao;
import com.zyt.web.publics.utils.HibernateValidatorFactoryUtils;
import com.zyt.web.publics.utils.SystemConstantUtils;

@Service
public class SysLogsServiceImpl implements SysLogsService {

	@Autowired
	private SysLogDao sysLogsDao;

	@Transactional
	@Override
	public Map<String, Object> insertLog(SysLog log) {
		Map<String, Object> results = HibernateValidatorFactoryUtils
				.verifyObject(log);
		if (results == null || results.size() == 0) {
			results = new HashMap<String, Object>();
			try {
				results.put("result", sysLogsDao.insertLog(log) > 0 ? "succ"
						: "fail");
			} catch (Exception e) {
				throw new RuntimeException("添加系统日志错误,可能存在该日志标识主键.");
			}
		}
		return results;
	}

	@Override
	public List<SysLog> getLogsPage(PaginationAble page,
			Map<String, Object> params) {
		RowBounds bounds = new RowBounds(page.getCurrentResult(),
				SystemConstantUtils.PAGINATION_PAGE_SIZE);
		return sysLogsDao.getLogsPage(page, bounds);
	}

	@Override
	public boolean deleteLogById(String logId) {
		return false;
	}

	@Override
	@Transactional
	public Map<String, Object> deleteLogsByIds(String[] logIds) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int rint = sysLogsDao.deleteLogsByIds(logIds);
			result.put("result", rint > 0 && rint == logIds.length ? "succ"
					: "fail");
			return result;
		} catch (Exception e) {
			throw new RuntimeException("批量删除信息异常.");
		}
	}

}
