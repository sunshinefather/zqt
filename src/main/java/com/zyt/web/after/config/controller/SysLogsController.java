package com.zyt.web.after.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zyt.web.after.config.service.SysLogsService;
import com.zyt.web.publics.base.BaseController;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.utils.SystemConstantUtils;

/**
 * 系统日志开发
 * 
 * @author maliang
 */
@Controller
@RequestMapping(value = "after/log")
public class SysLogsController extends BaseController {

	@Autowired
	private SysLogsService logsService;

	/**
	 * @描述 TODO 获取日志列表
	 * @author maliang
	 * @time 2014-3-17 上午9:51:07
	 * @version
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String show(
			@RequestParam(required = false, defaultValue = "1") String pageNo,
			Model model) {
		// page 对象可在参数列表中创建，也可以通过方法创建
		PaginationAble page = super.setPaginationAbleParams(pageNo,
				SystemConstantUtils.PAGINATION_PAGE_SIZE);
		// logsService.getLogsPage(page, log);
		model.addAttribute("logs",
				logsService.getLogsPage(page, super.getParams()));
		model.addAttribute("page", page);
		return "/after/config/log_list";
	}

	/**
	 * @描述 TODO 批量删除日志信息
	 * @author maliang
	 * @time 2014-3-18 下午6:06:36
	 * @version v1.0
	 * @param logIds
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(
			@RequestParam(value = "logIds[]", required = true) String[] logIds) {
		return JSON.toJSONString(logsService.deleteLogsByIds(logIds));
	}

}
