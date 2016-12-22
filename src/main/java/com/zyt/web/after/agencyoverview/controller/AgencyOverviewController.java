package com.zyt.web.after.agencyoverview.controller;

import httl.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zyt.web.after.agencyoverview.service.IAgencyOverviewSerice;
import com.zyt.web.after.hospital.service.HospitalService;
import com.zyt.web.after.sysmanager.service.IUserService;
import com.zyt.web.publics.base.BaseController;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.agencyOverview.bean.AgencyOverview;
import com.zyt.web.publics.module.hospital.bean.Hospital;
import com.zyt.web.publics.module.sysmanager.bean.User;
import com.zyt.web.publics.utils.SystemConstantUtils;
import com.zyt.web.publics.utils.UUIDUtils;

/**
 * @author ZLM
 * @description 机构概述Controller
 * @version 1.0
 * @date 2014-7-18
 */
@Controller
@RequestMapping(value = "after/agencyoverview")
public class AgencyOverviewController extends BaseController {

	@Autowired
	IUserService userService;
	@Autowired
	HospitalService hospitalService;
	@Autowired 
	IAgencyOverviewSerice agencyOverviewSerice;
	/**
	 * 列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, HttpServletRequest request) {
		String pageNo = request.getParameter("pageNo");
		if (null == pageNo || "".equals(pageNo))
			pageNo = "1";
		PaginationAble paginationAble = new PaginationAble(
				Integer.valueOf(pageNo),
				SystemConstantUtils.PAGINATION_PAGE_SIZE);
		User user =  getUser();
		Map<String, Object> whereParameters = new HashMap<String, Object>();
		String ids[] =null;
		if(user.getType().equals("100")){
			Hospital org = user.getExtUser().getOrganization();
			if (org != null) {
				String regionId = org.getRegionId();
				if (StringUtils.isNotBlank(regionId)) {
					List<Hospital> list = hospitalService.findOrganizationByRegoinId(regionId);
					if (list != null && list.size() > 0) {
						ids = new String[list.size()];
						for (int i = 0; i < ids.length; i++) {
							ids[i] = list.get(i).getHospitalId();
						}
						if(list.size()>0)
							whereParameters.put("orgIds", ids);
					}
				}
			}
		}else if(user.getType().equals("200")){
			ids = hospitalService.getChildOrgsByUserId(user.getId());
			whereParameters.put("orgIds", ids);
		}
		paginationAble.setWhereParameters(whereParameters);
		model.addAttribute("overview", agencyOverviewSerice.getPageAgencyOverview(paginationAble));
		model.addAttribute("page", paginationAble);
		// 当前页对象
		return "after/agencyoverview/list_agencyoverview";
	}
	/**
	 * 跳转到修改页面
	 * @param model
	 * @param id
	 * @param jump 跳转参数
	 * @return
	 */
	@RequestMapping(value = "/update/{id}/{jump}")
	public String update(Model model,@PathVariable String id,@PathVariable String jump) {
		List<AgencyOverview> agencyOverviews = agencyOverviewSerice.getAgencyOverviewByOrgId(id);
		if(agencyOverviews!=null && agencyOverviews.size()>0){
			model.addAttribute("sequence", agencyOverviews.get(agencyOverviews.size()-1).getSequence());
		}else{
			model.addAttribute("sequence", 0);
		}
		model.addAttribute("org", hospitalService.getById(id));
		model.addAttribute("agencyOverview", agencyOverviews);
		model.addAttribute("upOrSe", jump);
		return "after/agencyoverview/modify_agencyoverview";
	}

	/**
	 * 新增
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updatedata")
	public String updatedata(Hospital organization,Model model, HttpServletRequest request) {
		if(organization != null && organization.getAgencyOverviews() !=null && organization.getAgencyOverviews().size() > 0){
			List<AgencyOverview> agencyOverviews = new ArrayList<AgencyOverview>();
			for (AgencyOverview  agencyOverview: organization.getAgencyOverviews()) {
				agencyOverview.setId(UUIDUtils.getUUID());
				agencyOverview.setDate(new Date());
				agencyOverviews.add(agencyOverview);
			}
			agencyOverviewSerice.save(agencyOverviews);
		}
		return list(model, request);
	}
}
