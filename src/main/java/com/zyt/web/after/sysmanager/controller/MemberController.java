package com.zyt.web.after.sysmanager.controller;

import httl.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zyt.web.after.hospital.service.HospitalService;
import com.zyt.web.after.position.service.PositionService;
import com.zyt.web.after.sysmanager.service.IRegionService;
import com.zyt.web.after.sysmanager.service.IUserService;
import com.zyt.web.publics.base.BaseController;
import com.zyt.web.publics.base.JsonEntity;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.hospital.bean.Hospital;
import com.zyt.web.publics.module.sysmanager.bean.ExtUser;
import com.zyt.web.publics.module.sysmanager.bean.Group;
import com.zyt.web.publics.module.sysmanager.bean.User;
import com.zyt.web.publics.utils.fileparser.FileParser;
import com.zyt.web.publics.utils.fileparser.FileParserFactory;
import com.zyt.web.publics.utils.reflection.ReflectionUtils;

/**
 * 
 * @author YuJ
 * @description 会员管理Controller
 * @version 1.0
 * @date 2014-05-15
 */
@Controller
@RequestMapping("/after/member")
public class MemberController extends BaseController {

	@Autowired
	IUserService userService;
	@Autowired
	HospitalService hospitalService;
	@Autowired
	IRegionService regionService;
	@Autowired
	PositionService positionService;
	
	public static final String XLS = "xls";
	public static final String XLSX = "xlsx";
	private Map<String, String> templateColumns = new LinkedHashMap<String, String>();
	//配置导入导出模板
	{
		templateColumns.put("账户名", "userName");
		templateColumns.put("姓名", "extUser.fullName");
		templateColumns.put("昵称", "extUser.nickName");
		templateColumns.put("职位", "extUser.position");
		templateColumns.put("机构Id", "extUser.orgId");
		templateColumns.put("邮箱", "extUser.email");
		templateColumns.put("性别", "extUser.gender");
		templateColumns.put("电话", "extUser.mobile");
		templateColumns.put("电话2", "extUser.mobile2");
		templateColumns.put("座机", "extUser.tel");
		templateColumns.put("座机2", "extUser.tel2");
		templateColumns.put("传真", "extUser.fax");
		templateColumns.put("传真2", "extUser.fax2");
	}
	
	/**
	 * 会员列表
	 * @param model
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(Model model, PaginationAble page) throws Exception {
		page.setWhereParameters(getParams(model));
		model.addAttribute("list", userService.findMemberList(page,getUser()));
		model.addAttribute("page", page);
		return "after/member/list";
	}
	
	/**
	 * 编辑
	 * @param model
	 * @param memberId 会员Id
	 * @return
	 */
	@RequestMapping("/edit/{memberId}")
	public String edit(Model model, @PathVariable String memberId) {
		User curUser =getUser();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", curUser.getType());
		//判断用户对象
		if ("100".equals(curUser.getType())) {// 区域用户
			String[] regionIds = regionService.getChildRegionsByUserId(curUser
					.getId());
			params.put("regionIds", regionIds);
		} else 
			params.put("userId", curUser.getId());
		
		//获取当前登录用户可选的机构
		List<Hospital> list = hospitalService.findList(params);
		if (StringUtils.isNotBlank(memberId) && !"null".equals(memberId)) {//编辑
			User user = userService.findUserById(memberId);
			//获取用户对象
			model.addAttribute("obj", user);
			//处理默认把选中的机构放在下拉选项第一位
			Hospital organization = null;
			for (Hospital org : list) {
				if (org.getHospitalId().equals(user.getExtUser().getOrgId())) {
					organization = org;
					list.remove(org);
					break;
				}
			}
			if (organization != null)
				list.add(0, organization);
		}
		model.addAttribute("positions",positionService.getByUser(getUser()));
		model.addAttribute("orgList", list);
		return "after/member/edit";
	}

	/**
	 * 新增或编辑用户保存数据
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/modify")
	public @ResponseBody Integer modify(User user) {
		user.setType("300");
		//设置会员用户组
		Group group = new Group();
		group.setId("f5d89ae246064893a8b311b996dc33fe");//设置会员用户组Id
		List<Group> groups = new ArrayList<Group>();
		groups.add(group);
		user.setGroups(groups);
		if (StringUtils.isBlank(user.getId()))
			return userService.save(user);
		else
			return userService.update(user);
	}
	
	/**
	 * 导入excel
	 * @param fileUpload
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/uploadExcel")
	@ResponseBody
	public JsonEntity uploadExcel(
			@RequestParam("file") MultipartFile fileUpload,
			HttpServletRequest request, HttpServletResponse response) {
		JsonEntity je = new JsonEntity();
		User curUser = getUser();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", curUser.getType());
		if ("100".equals(curUser.getType())) {// 区域用户
			String[] regionIds = regionService.getChildRegionsByUserId(curUser
					.getId());
			params.put("regionIds", regionIds);
		} else
			params.put("userId", curUser.getId());
		List<Hospital> list = hospitalService.findList(params);
		//获取当前用户下的第一个机构Id
		String orgId = null;
		if (list != null && !list.isEmpty())
			orgId = list.get(0).getHospitalId();
		// 判断是否有文件
		if (!fileUpload.isEmpty()) {
			try {
				String fileName = fileUpload.getOriginalFilename();// 源文件名
				// 获取文件后缀
				String[] suffixs = fileName.split("\\.");
				String suffix = suffixs[suffixs.length - 1];
				
				if (XLS.equalsIgnoreCase(suffix)
						|| XLSX.equalsIgnoreCase(suffix)) {
					FileParser parser = FileParserFactory.getExcelFileParser(
							fileName, fileUpload.getInputStream(),
							templateColumns);
					if (parser.isEmpty()) {
						je.setStatus(JsonEntity.fail);
						je.setMsg("文件中没有数据");
						return je;
					}
					int i = 1;
					while (parser.hasMore()) {
						Map<String, String> rowData = parser.parserLine();
						// 没有读到数据则跳出循环
						if (rowData == null || rowData.size() == 0) {
							break;
						}
						User user = new User();
						user.setType("300");
						//设置会员用户组
						Group group = new Group();
						group.setId("f5d89ae246064893a8b311b996dc33fe");//设置会员用户组Id
						List<Group> groups = new ArrayList<Group>();
						groups.add(group);
						user.setGroups(groups);
						ExtUser extUser = new ExtUser();
						extUser.setRegisterTime(new Date());
						user.setExtUser(extUser);
						user.setPassword("123456");//初始化密码
						for (String ss : rowData.keySet()) {
							ReflectionUtils.invokeSetter(user, ss, rowData.get(ss));
						}
						//设置机构
						if (user.getExtUser().getOrgId() == null || "".equals(user.getExtUser().getOrgId()))
							user.getExtUser().setOrgId(orgId);
						je.setCode(userService.save(user));
						if (je.getStatus().equals(JsonEntity.fail)) {
							je.setMsg("插入第" + i + "行失败");
							return je;
						}
						i++;
					}
				} else {
					// 上传的文件格式不正确
					je.setStatus(JsonEntity.fail);
					je.setMsg("您上传的文件格式不正确");
					return je;
				}
			} catch (Exception e) {
				e.printStackTrace();
				je.setStatus(JsonEntity.fail);
				je.setMsg("您上传的模板不正确");
				return je;
			}
		} else {
			je.setStatus(JsonEntity.fail);
			je.setMsg("文件为空!");
		}
		return je;
	}
	
}
