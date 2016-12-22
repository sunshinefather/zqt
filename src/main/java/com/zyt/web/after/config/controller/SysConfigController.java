package com.zyt.web.after.config.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zyt.web.after.config.service.SysConfigService;
import com.zyt.web.publics.annotations.AfterMethod;
import com.zyt.web.publics.annotations.LogType;
import com.zyt.web.publics.base.BaseController;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.config.bean.SysConfig;
import com.zyt.web.publics.utils.SystemConstantUtils;

/**
 * @author maliang
 */
@Controller
@RequestMapping(value = "after/config")
public class SysConfigController extends BaseController {

	@Autowired
	private SysConfigService configService;

	/**
	 * @描述 TODO 系统配置页
	 * @author maliang
	 * @time 2014-3-11 上午9:59:02
	 * @version
	 * @return <p>
	 *         添加分页获取
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String show(Model model,
			@RequestParam(required = false, defaultValue = "1") String pageNo) {
		PaginationAble page = super.setPaginationAbleParams(pageNo,
				SystemConstantUtils.PAGINATION_PAGE_SIZE);
		// 当前页的config数据
		model.addAttribute("configs",
				configService.getConfigsPage(page, super.getParams()));
		// 当前页对象
		model.addAttribute("page", page);
		return "after/config/list";
	}

	/**
	 * @描述 TODO 添加系统配置页面
	 * @author maliang
	 * @time 2014-3-12 下午5:13:13
	 * @version
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String toAdd() {
		return "after/config/add";
	}

	/**
	 * @描述 TODO 提交添加系统配置
	 * @author maliang
	 * @time 2014-3-12 下午5:14:31
	 * @version
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(SysConfig config, Model model) {
		Map<String, Object> valis = configService.insertConfig(config);

		if (valis != null && valis.size() > 0 && !valis.containsValue("succ")) {
			model.addAttribute("valis", valis);
			model.addAttribute("config", config);
			return "after/config/add";
		}
		// 跳转到列表页
		return "redirect:/after/config/list";
	}

	/**
	 * @描述 TODO 到编辑页面,根据ID获取编辑项
	 * @author maliang
	 * @time 2014-3-12 下午5:24:31
	 * @version
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String toEdit(@PathVariable String id, Model model) {
		model.addAttribute("config", configService.getConfigById(id));
		return "after/config/add";
	}

	/**
	 * @描述 TODO 提交编辑信息
	 * @author maliang
	 * @time 2014-3-12 下午5:25:23
	 * @version
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String edit(SysConfig config,Model model) {
		boolean  result =  configService.updateConfigById(config);
		model.addAttribute("config", config);
		if(result){
			return "redirect:list";
		}else{
			return "after/config/add";
		}
	}

	/**
	 * @描述 TODO 删除系统属性信息
	 * @author maliang
	 * @time 2014-3-12 下午5:29:21
	 * @version
	 * @return
	 */
	@AfterMethod(value = "删除系统属性信息", type = LogType.SYSTEM)
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam(value = "configKeys[]", required = true) String[] configKeys) {

		Map<String, Object> result = configService.deleteConfigsByIds(configKeys);

		return JSON.toJSONString(result);
	}

}
