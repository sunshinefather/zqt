package com.zyt.web.after.position.controller;

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

import com.zyt.web.after.position.service.PositionService;
import com.zyt.web.publics.base.BaseController;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.position.bean.Position;

/**
 * 
 * @author LiuChuang
 * @description 岗位控制器
 * @version 1.0
 * @date 2015年4月22日
 */
@Controller
@RequestMapping(value = "after/position")
public class PositionController extends BaseController {
	@Autowired
	private PositionService positionService;
	
	/**
	 * 
	 *@Description: 查询岗位列表
	 *@param model
	 *@param page
	 *@return String
	 *@throws Exception
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月22日上午11:10:57
	 */
	@RequestMapping(value="position-list")
	public String list(Model model, PaginationAble page) throws Exception {
		page.setWhereParameters(getParams(model));
		List<Position> positions = positionService.findList(page,getUser());
		model.addAttribute("list", positions);
		model.addAttribute("page", page);
		return "after/position/position-list";
	}
	
	/**
	 * 
	 *@Description: 增加岗位
	 *@param model
	 *@return String
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月22日上午11:11:17
	 */
	@RequestMapping(value="position-add",method=RequestMethod.GET)
	public String add(Model model) {
		
		return "after/position/position-edit";
	}
	
	/**
	 * 
	 *@Description: 更新岗位
	 *@param model
	 *@return String
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月22日上午11:13:40
	 */
	@RequestMapping(value="position-edit/{id}",method=RequestMethod.GET)
	public String edit(Model model,@PathVariable String id) {
		model.addAttribute("obj",positionService.getById(id));
		return "after/position/position-edit";
	}
	
	/**
	 * 
	 *@Description: 保存岗位
	 *@param model
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月22日上午11:15:23
	 */
	@RequestMapping(value="position-save",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> save(Model model,@Valid Position position ,BindingResult bindingResult) {
		Map<String, Object> valis = super.getErrors(bindingResult);
		if (valis != null && valis.size() > 0 && !valis.containsValue(true)) {//验证不通过
			return valis;
		}
		int row = positionService.save(position);
		valis.put("row",row);
		return valis;
	}
	
	/**
	 * 
	 *@Description: 更新
	 *@param model
	 *@param position
	 *@return Integer
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2015年4月22日上午11:19:06
	 */
	@RequestMapping(value="position-update",method=RequestMethod.POST)
	@ResponseBody
	public  Map<String,Object> update(Model model,@Valid Position position ,BindingResult bindingResult) {
		Map<String, Object> valis = super.getErrors(bindingResult);
		if (valis != null && valis.size() > 0 && !valis.containsValue(true)) {//验证不通过
			return valis;
		}
		int row = positionService.update(position);
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
	@RequestMapping(value="position-deleteBatch/{ids}",method=RequestMethod.GET)
	@ResponseBody
	public Integer deleteBatch(@PathVariable String ids){
		
		return positionService.delete(ids.split(","));
	}
}
