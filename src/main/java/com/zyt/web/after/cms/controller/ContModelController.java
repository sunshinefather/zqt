package com.zyt.web.after.cms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyt.web.after.cms.service.IContModelService;
import com.zyt.web.publics.base.BaseController;
import com.zyt.web.publics.base.JsonEntity;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.cms.bean.ContModel;
import com.zyt.web.publics.utils.reflection.ReflectionUtils;

/**
 * @ClassName: ContModelController
 * @Description:内容分类控制层
 * @author: sunshine
 */
@Controller
@RequestMapping("/after/contModel")
public class ContModelController extends BaseController {

    @Resource
	private IContModelService contModelService;
	
    /** 
     *内容分类分页列表数据
	 * @Title: list
	 * @Description: 分页获取内容分类列表数据
	 */
	@RequestMapping("/list")
	public String list(Model model, PaginationAble page){
		page.getWhereParameters().putAll(getParams());
		page.getWhereParameters().put("hospitalId",getUser().getId());
		model.addAttribute("page", page);
		model.addAttribute("list", contModelService.findList(page));
		return "after/contModel/list";
	}
	
   /** 
     *内容分类列表数据
	 * @Title: list
	 * @Description: 获取内容分类列表数据
	 */
	@RequestMapping("/loadlist")
	public String loadList(Model model,ContModel contModel){
		model.addAttribute("list", contModelService.queryList(contModel));
		return "after/contModel/loadlist";
	}
	
	/**
	 * 内容分类表单
	 * @Title: add
	 * @Description:
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		return "after/contModel/edit";
	}

	/**
	 * 更具ID获取内容分类信息
	 * @Title: edit
	 * @Description: TODO
	 */
	@RequestMapping("/edit/{id}")
	public String edit(Model model, @PathVariable(value="id") String id) {
		model.addAttribute("obj", contModelService.findObjectById(id));
		return "after/contModel/edit";
	}

	/**
	 * 保存内容分类
	 * @Title: saveOrUpdate
	 * @Description: TODO
	 */
	@RequestMapping("/save")
	@ResponseBody
	public JsonEntity saveOrUpdate(ContModel contModel) {
		JsonEntity js = new JsonEntity();
		if (!ValidateEntity(contModel, js)) {
			return js;
		}
		if (StringUtils.isEmpty(contModel.getId())) {
			js.setCode(contModelService.insert(contModel));
		} else {
			js.setCode(contModelService.update(contModel));
		}
		return js;
	}

	/**
	 * 删除内容分类
	 * @Title: deleteContModel
	 * @Description: TODO
	 */
	@RequestMapping("/del")
	@ResponseBody
	public JsonEntity delete(String id) {
		if (StringUtils.isEmpty(id)) {
			throw new RuntimeException("参数不能为空");
		}
		return new JsonEntity(contModelService.delete(id.split("[`]")));
	}
	
	/**
	 * 通用ajax校验
	 * @Description: TODO
	 * @Title: validate
	 * @author: sunshine 
	 */
	@RequestMapping("/validate")
	@ResponseBody
    public JsonEntity validate(ContModel contModel,String fieldId,String fieldValue, String old_field,HttpServletRequest request,HttpServletResponse response){
		JsonEntity je= new JsonEntity();
		je.setData(fieldId);
	if (StringUtils.isEmpty(old_field) || !old_field.equals(fieldValue)){
		ReflectionUtils.setFieldValue(contModel, fieldId, fieldValue);
		int ct=contModelService.validate(contModel);
		if(ct!=0){
			je.setStatus(JsonEntity.fail);
		}
    }
		return je;
	}
}
