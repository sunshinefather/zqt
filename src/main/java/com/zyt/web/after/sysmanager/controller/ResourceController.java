package com.zyt.web.after.sysmanager.controller;

import httl.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zyt.web.after.sysmanager.service.IResourceService;
import com.zyt.web.after.sysmanager.service.IUserService;
import com.zyt.web.publics.base.BaseController;
import com.zyt.web.publics.module.sysmanager.bean.Resource;
import com.zyt.web.publics.module.sysmanager.bean.User;
import com.zyt.web.publics.utils.JSONUtils;
import com.zyt.web.publics.utils.UUIDUtils;

/**
 * @author 系统资源控制器
 */
@Controller
@RequestMapping("after/resource")
public class ResourceController extends BaseController {

	@Autowired
	private IResourceService resourceService;

	@Autowired
	private IUserService userService;

	@RequestMapping("/index")
	public String show() throws UnsupportedEncodingException {
		return "after/sysmanger/resource/list";
	}

	/**
	 * @描述 获取系统资源信息
	 * @author maliang
	 * @time 2014-3-12 上午11:12:23
	 * @version
	 * @return
	 */
	@RequestMapping("/list")
	public void list(HttpServletRequest request, HttpServletResponse respose) {
		// 拦截保证此处的用户不为空
		User user = userService.findUserById(super.getUser().getId());
		if (user != null && user.getUserName().equals("admin")) {
			JSONUtils.Success(resourceService.getResources(null,null), respose);
		} else
			JSONUtils.Success(resourceService.findResourceByRoleIds(user == null ? null : user.getRoles(),null,null), respose);
	};
	
	/**
	 * 
	 *@Description: 根据用户获取用户的资源列表
	 *@param userId 用户ID
	 *@param resourceType 资源类型
	 *@param project 项目标识
	 *@param model
	 *@return String
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年3月27日上午9:52:38
	 */
	@RequestMapping(value = "/jsons/{userId}", method = RequestMethod.GET)
	public String showJsonByRoleId(@PathVariable String userId,
			@RequestParam(required = false) String resourceType,
			@RequestParam(required = false) String project, Model model) {
		User user = userService.findUserById(userId);
		if (user != null && user.getUserName().equals("admin")) {
			model.addAttribute("resources", resourceService.getResources(resourceType,project));
		}else{
			model.addAttribute("resources", resourceService.findResourceByRoleIds(user == null ? null : user.getRoles(), resourceType,project));
		}
		return "jsonView";
	}

	/**
	 * @描述 删除节点，如果存在子节点，将一并删除
	 * @author maliang
	 * @time 2014-3-19 上午10:06:22
	 * @version v1.0
	 * @param nodeId
	 * @return
	 */
	@RequestMapping("/delete/{resourceId}")
	public void deleteResource(@PathVariable String resourceId,
			HttpServletRequest request, HttpServletResponse respose) {
		boolean sa;
		StringBuilder str = new StringBuilder();
		Map<String, Object> map = resourceService
				.deleteResourceById(resourceId);
		Object ob = map.get("result");
		if (ob != null) {
			sa = (Boolean) ob;
			if (!sa) {
				str.append("删除失败");
				JSONUtils.Failure(str.toString(), respose);
			} else {
				str.append("删除成功");
				JSONUtils.Success(str.toString(), respose);
			}
		} else {
			int ct = 0;
			for (Object msg : map.values()) {
				if (ct++ != 0) {
					str.append(",");
				}
				str.append(msg.toString());
			}
			JSONUtils.Failure(str.toString(), respose);
		}
	}

	/**
	 * @描述:TODO 添加系统资源
	 * @auto:maliang
	 * @time:2014-3-19
	 * @param resource
	 * @param parenNodeId
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addNode(Resource resource, HttpServletRequest request,
			HttpServletResponse respose) {
		boolean sa;
		StringBuilder str = new StringBuilder();
		Map<String, Object> map;
		if (!StringUtils.isEmpty(resource.getId())) {
			map = resourceService.updateResource(resource);
		} else {
			resource.setId(UUIDUtils.getUUID());
			map = resourceService.insertResource(resource);
		}
		Object ob = map.get("result");
		if (ob != null) {
			sa = (Boolean) ob;
			if (!sa) {
				str.append("保存失败");
				JSONUtils.Failure(str.toString(), respose);
			} else {
				JSONUtils.Success(resource, respose);
			}
		} else {
			int ct = 0;
			for (Object msg : map.values()) {
				if (ct++ != 0) {
					str.append(",");
				}
				str.append(msg.toString());
			}
			JSONUtils.Failure(str.toString(), respose);
		}
	}
}