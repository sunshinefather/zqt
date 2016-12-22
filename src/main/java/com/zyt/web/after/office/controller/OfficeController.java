package com.zyt.web.after.office.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyt.web.after.office.service.OfficeSerrvice;
import com.zyt.web.publics.base.BaseController;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.office.bean.Office;

/**
 * 
 * @author LiuChuang
 * @description 职务控制器
 * @version 1.0
 * @date 2015年4月22日
 */
@Controller
@RequestMapping(value = "after/office")
public class OfficeController extends BaseController{
	@Autowired
	private OfficeSerrvice officeService;
	
	/**
	 * 
	 *@Description: 查询职务列表
	 *@param model
	 *@param page
	 *@return String
	 *@throws Exception
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月22日上午11:10:57
	 */
	@RequestMapping(value="office-list")
	public String list(Model model, PaginationAble page) throws Exception {
		page.setWhereParameters(getParams(model));
		List<Office> offices = officeService.findList(page,getUser());
		model.addAttribute("list", offices);
		model.addAttribute("page", page);
		return "after/office/office-list";
	}
	
	/**
	 * 
	 *@Description: 增加职务
	 *@param model
	 *@return String
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月22日上午11:11:17
	 */
	@RequestMapping(value="office-add",method=RequestMethod.GET)
	public String add(Model model) {
		
		return "after/office/office-edit";
	}
	
	/**
	 * 
	 *@Description: 更新职务
	 *@param model
	 *@return String
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月22日上午11:13:40
	 */
	@RequestMapping(value="office-edit/{id}",method=RequestMethod.GET)
	public String edit(Model model,@PathVariable String id) {
		model.addAttribute("obj",officeService.getById(id));
		return "after/office/office-edit";
	}
	
	/**
	 * 
	 *@Description: 保存职务
	 *@param model
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月22日上午11:15:23
	 */
	@RequestMapping(value="office-save",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> save(Model model,@Valid Office office ,BindingResult bindingResult) {
		Map<String, Object> valis = super.getErrors(bindingResult);
		if (valis != null && valis.size() > 0 && !valis.containsValue(true)) {//验证不通过
			return valis;
		}
		int row = officeService.save(office);
		valis.put("row",row);
		return valis;
	}
	
	/**
	 * 
	 *@Description: 更新
	 *@param model
	 *@param office
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月22日上午11:19:06
	 */
	@RequestMapping(value="office-update",method=RequestMethod.POST)
	@ResponseBody
	public  Map<String,Object> update(Model model,@Valid Office office ,BindingResult bindingResult) {
		Map<String, Object> valis = super.getErrors(bindingResult);
		if (valis != null && valis.size() > 0 && !valis.containsValue(true)) {//验证不通过
			return valis;
		}
		int row = officeService.update(office);
		valis.put("row",row);
		return valis;
	}
	
	/**
	 * 
	 *@Description: 批量删除
	 *@param ids
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月22日上午11:18:54
	 */
	@RequestMapping(value="office-deleteBatch/{ids}",method=RequestMethod.GET)
	@ResponseBody
	public Integer deleteBatch(@PathVariable String ids){
		
		return officeService.delete(ids.split(","));
	}
}
