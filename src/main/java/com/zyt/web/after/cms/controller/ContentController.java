package com.zyt.web.after.cms.controller;

import java.util.Date;

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
import com.zyt.web.after.cms.service.IContentService;
import com.zyt.web.publics.base.BaseController;
import com.zyt.web.publics.base.JsonEntity;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.cms.bean.Content;
import com.zyt.web.publics.utils.JsoupHtmlHandle;
import com.zyt.web.publics.utils.UUIDUtils;
import com.zyt.web.publics.utils.date.DateUtil;
import com.zyt.web.publics.utils.reflection.ReflectionUtils;

/**
 * @ClassName: ContentController
 * @Description:内容管理控制层
 * @author: sunshine
 */
@Controller
@RequestMapping("/after/content")
public class ContentController extends BaseController {

    @Resource
	private IContentService contentService;
    @Resource
    private IContModelService contModelService;
	
    /** 
     * 园区概述
	 * @Title: list
	 * @Description: 分页获取内容管理列表数据
	 */
	@RequestMapping("/yuanqu")
	public String yuanqu(Model model){
		model.addAttribute("obj", contentService.findObjectById("1"));
		return "after/content/yuanqu";
	}
	/**
	 * 发展
	 * @Title: fazhan
	 * @Description: TODO  
	 * @param: @param model
	 * @param: @return      
	 * @return: String
	 * @author: sunshine  
	 * @throws
	 */
	@RequestMapping("/fazhan")
	public String fazhan(Model model){
		model.addAttribute("obj", contentService.findObjectById("2"));
		return "after/content/fazhan";
	}
	/**
	 * 规划
	 * @Title: guihua
	 * @Description: TODO  
	 * @param: @param model
	 * @param: @return      
	 * @return: String
	 * @author: sunshine  
	 * @throws
	 */
	@RequestMapping("/guihua")
	public String guihua(Model model){
		model.addAttribute("obj", contentService.findObjectById("3"));
		return "after/content/guihua";
	}
	/**
	 * 产业
	 * @Title: chanye
	 * @Description: TODO  
	 * @param: @param model
	 * @param: @return      
	 * @return: String
	 * @author: sunshine  
	 * @throws
	 */
	@RequestMapping("/chanye")
	public String chanye(Model model){
		model.addAttribute("obj", contentService.findObjectById("4"));
		return "after/content/chanye";
	}
	/**
	 * 领导班子
	 * @Title: banzi
	 * @Description: TODO  
	 * @param: @param model
	 * @param: @param page
	 * @param: @return      
	 * @return: String
	 * @author: sunshine  
	 * @throws
	 */
	@RequestMapping("/banzi")
	public String banzi(Model model, PaginationAble page){
		page.getWhereParameters().putAll(getParams());
		page.getWhereParameters().put("categoryId","1");
		model.addAttribute("page", page);
		model.addAttribute("list", contentService.findList(page));
		return "after/content/lijielingdao";
	}
	/**
	 * 园区大计事
	 * @Title: dajishi
	 * @Description: TODO  
	 * @param: @param model
	 * @param: @param page
	 * @param: @return      
	 * @return: String
	 * @author: sunshine  
	 * @throws
	 */
	@RequestMapping("/dajishi")
	public String dajishi(Model model, PaginationAble page){
		page.getWhereParameters().putAll(getParams());
		page.getWhereParameters().put("categoryId","2");
		model.addAttribute("page", page);
		model.addAttribute("list", contentService.findList(page));
		return "after/content/yuanqudashi";
	}
	/**
	 * 政策管理
	 * @Title: zhengce
	 * @Description: TODO  
	 * @param: @param model
	 * @param: @param page
	 * @param: @return      
	 * @return: String
	 * @author: sunshine  
	 * @throws
	 */
	@RequestMapping("/zhengce")
	public String zhengce(Model model, PaginationAble page){
		page.getWhereParameters().putAll(getParams());
		page.getWhereParameters().put("categoryId","3");
		model.addAttribute("page", page);
		model.addAttribute("list", contentService.findList(page));
		return "after/content/zhengceguanli";
	}
	/**
	 * 公告
	 * @Title: gonggao
	 * @Description: TODO  
	 * @param: @param model
	 * @param: @param page
	 * @param: @return      
	 * @return: String
	 * @author: sunshine  
	 * @throws
	 */
	@RequestMapping("/gonggao")
	public String gonggao(Model model, PaginationAble page){
		page.getWhereParameters().putAll(getParams());
		page.getWhereParameters().put("categoryId","4");
		model.addAttribute("page", page);
		model.addAttribute("list", contentService.findList(page));
		return "after/content/gonggao";
	}
	
	@RequestMapping("/chuangye")
	public String chuangye(Model model, PaginationAble page){
		page.getWhereParameters().putAll(getParams());
		page.getWhereParameters().put("categoryId","5");
		model.addAttribute("page", page);
		model.addAttribute("list", contentService.findList(page));
		return "after/content/daxuechuangye";
	}
	
   /** 
     *内容管理列表数据
	 * @Title: list
	 * @Description: 获取内容管理列表数据
	 */
	@RequestMapping("/loadlist")
	public String loadList(Model model,Content content){
		model.addAttribute("list", contentService.queryList(content));
		return "after/content/loadlist";
	}
	
	/**
	 * 内容管理表单
	 * @Title: add
	 * @Description:
	 */
	@RequestMapping("/add/{page}")
	public String add(Model model,@PathVariable("page")String page) {
		return "after/content/edit_"+page;
	}

	/**
	 * 更具ID获取内容管理信息
	 * @Title: edit
	 * @Description: TODO
	 */
	@RequestMapping("/edit/{id}/{page}")
	public String edit(Model model, @PathVariable(value="id") String id,@PathVariable("page")String page) {
		model.addAttribute("obj", contentService.findObjectById(id));
		return "after/content/edit_"+page;
	}

	/**
	 * 保存内容管理
	 * @Title: saveOrUpdate
	 * @Description: TODO
	 */
	@RequestMapping("/save")
	@ResponseBody
	public JsonEntity saveOrUpdate(Content content,HttpServletRequest request) {
		JsonEntity js = new JsonEntity();
		if (!ValidateEntity(content, js)) {
			return js;
		}
		if (StringUtils.isEmpty(content.getId())) {
			content.setId(UUIDUtils.getUUID());
	        content.setHospitalId(getUser().getId());
			content.setCrateTime(DateUtil.parseToString(new Date(),"yyyy-MM-dd HH:mm"));
			content.setPublisher(getUser().getUserName());
			content.setContent(JsoupHtmlHandle.addAttr(content.getContent(),"img[src*="+request.getContextPath()+"]", "width", "100%"));
			js.setCode(contentService.insert(content));
		} else {
			if(contentService.findObjectById(content.getId())!=null){
				js.setCode(contentService.update(content));	
			}else{
		        content.setHospitalId(getUser().getId());
				content.setCrateTime(DateUtil.parseToString(new Date(),"yyyy-MM-dd HH:mm"));
				content.setPublisher(getUser().getUserName());
				js.setCode(contentService.insert(content));
			}
		}
		return js;
	}

	/**
	 * 删除内容管理
	 * @Title: deleteContent
	 * @Description: TODO
	 */
	@RequestMapping("/del")
	@ResponseBody
	public JsonEntity delete(String id) {
		if (StringUtils.isEmpty(id)) {
			throw new RuntimeException("参数不能为空");
		}
		return new JsonEntity(contentService.delete(id.split("[`]")));
	}
	
	/**
	 * 通用ajax校验
	 * @Description: TODO
	 * @Title: validate
	 * @author: sunshine 
	 */
	@RequestMapping("/validate")
	@ResponseBody
    public JsonEntity validate(Content content,String fieldId,String fieldValue, String old_field,HttpServletRequest request,HttpServletResponse response){
		JsonEntity je= new JsonEntity();
		je.setData(fieldId);
	if (StringUtils.isEmpty(old_field) || !old_field.equals(fieldValue)){
		ReflectionUtils.setFieldValue(content, fieldId, fieldValue);
		int ct=contentService.validate(content);
		if(ct!=0){
			je.setStatus(JsonEntity.fail);
		}
    }
		return je;
	}
}
