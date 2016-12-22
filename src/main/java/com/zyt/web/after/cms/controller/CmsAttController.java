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

import com.zyt.web.after.cms.service.ICmsAttService;
import com.zyt.web.publics.base.BaseController;
import com.zyt.web.publics.base.JsonEntity;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.cms.bean.CmsAtt;
import com.zyt.web.publics.utils.reflection.ReflectionUtils;

/**
 * @ClassName: CmsAttController
 * @Description:cms附件控制层
 * @author: sunshine
 */
@Controller
@RequestMapping("/after/cmsAtt")
public class CmsAttController extends BaseController {

    @Resource
	private ICmsAttService cmsAttService;
	
    /** 
     *cms附件分页列表数据
	 * @Title: list
	 * @Description: 分页获取cms附件列表数据
	 */
	@RequestMapping("/list")
	public String list(Model model, PaginationAble page){
		page.setWhereParameters(getParams());
		model.addAttribute("page", page);
		model.addAttribute("list", cmsAttService.findList(page));
		return "after/cmsAtt/list";
	}
	
   /** 
     *cms附件列表数据
	 * @Title: list
	 * @Description: 获取cms附件列表数据
	 */
	@RequestMapping("/loadlist")
	public String loadList(Model model,CmsAtt cmsAtt){
		model.addAttribute("list", cmsAttService.queryList(cmsAtt));
		return "after/cmsAtt/loadlist";
	}
	
	/**
	 * cms附件表单
	 * @Title: add
	 * @Description:
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		return "after/cmsAtt/edit";
	}

	/**
	 * 更具ID获取cms附件信息
	 * @Title: edit
	 * @Description: TODO
	 */
	@RequestMapping("/edit/{id}")
	public String edit(Model model, @PathVariable(value="id") String id) {
		model.addAttribute("obj", cmsAttService.findObjectById(id));
		return "after/cmsAtt/edit";
	}

	/**
	 * 保存cms附件
	 * @Title: saveOrUpdate
	 * @Description: TODO
	 */
	@RequestMapping("/save")
	@ResponseBody
	public JsonEntity saveOrUpdate(CmsAtt cmsAtt) {
		JsonEntity js = new JsonEntity();
		if (!ValidateEntity(cmsAtt, js)) {
			return js;
		}
		if (StringUtils.isEmpty(cmsAtt.getId())) {
			js.setCode(cmsAttService.insert(cmsAtt));
		} else {
			js.setCode(cmsAttService.update(cmsAtt));
		}
		return js;
	}

	/**
	 * 删除cms附件
	 * @Title: deleteCmsAtt
	 * @Description: TODO
	 */
	@RequestMapping("/del")
	@ResponseBody
	public JsonEntity delete(String id) {
		if (StringUtils.isEmpty(id)) {
			throw new RuntimeException("参数不能为空");
		}
		return new JsonEntity(cmsAttService.delete(id.split("[`]")));
	}
	
	/**
	 * 通用ajax校验
	 * @Description: TODO
	 * @Title: validate
	 * @author: sunshine 
	 */
	@RequestMapping("/validate")
	@ResponseBody
    public JsonEntity validate(CmsAtt cmsAtt,String fieldId,String fieldValue, String old_field,HttpServletRequest request,HttpServletResponse response){
		JsonEntity je= new JsonEntity();
		je.setData(fieldId);
	if (StringUtils.isEmpty(old_field) || !old_field.equals(fieldValue)){
		ReflectionUtils.setFieldValue(cmsAtt, fieldId, fieldValue);
		int ct=cmsAttService.validate(cmsAtt);
		if(ct!=0){
			je.setStatus(JsonEntity.fail);
		}
    }
		return je;
	}
}
