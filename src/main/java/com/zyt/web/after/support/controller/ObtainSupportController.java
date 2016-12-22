package com.zyt.web.after.support.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zyt.web.after.config.service.SysConfigService;
import com.zyt.web.after.hospital.service.HospitalService;
import com.zyt.web.after.support.service.IObtainSupportService;
import com.zyt.web.publics.base.BaseController;
import com.zyt.web.publics.base.JsonEntity;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.config.bean.SysConfig;
import com.zyt.web.publics.module.hospital.bean.Hospital;
import com.zyt.web.publics.module.support.bean.ObtainSupport;
import com.zyt.web.publics.utils.UUIDUtils;
import com.zyt.web.publics.utils.reflection.ReflectionUtils;

/**
 * @ClassName: ObtainSupportController
 * @Description:企业获得支持控制层
 * @author: sunshine
 */
@Controller
@RequestMapping("/after/obtainSupport")
public class ObtainSupportController extends BaseController {

    @Resource
	private IObtainSupportService obtainSupportService;
    
    @Resource
	private HospitalService hospitalService;
	
	@Resource
	private SysConfigService sysConfigService;
	
    /** 
     *企业获得支持分页列表数据
	 * @Title: list
	 * @Description: 分页获取企业获得支持列表数据
	 */
	@RequestMapping("/list")
	public String list(Model model, PaginationAble page){
		page.setWhereParameters(getParams());
		model.addAttribute("page", page);
		if(!"0".equals(getUser().getType())){
			page.getWhereParameters().put("hospitalId",getUser().getExtUser().getOrgId());
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isRoot", false);
		params.put("type",getUser().getType());
		params.put("userId",getUser().getId());
		List<Hospital> listcc = hospitalService.findList(params);
		Map<String,String> map=new HashMap<String,String>();
		for(Hospital na:listcc){
			map.put(na.getHospitalId(), na.getHospitalName());
		}
		model.addAttribute("map",map);
		model.addAttribute("list", obtainSupportService.findList(page));
		return "after/obtainSupport/list";
	}
	
   /** 
     *企业获得支持列表数据
	 * @Title: list
	 * @Description: 获取企业获得支持列表数据
	 */
	@RequestMapping("/loadlist")
	public String loadList(Model model,ObtainSupport obtainSupport){
		model.addAttribute("list", obtainSupportService.queryList(obtainSupport));
		return "after/obtainSupport/loadlist";
	}
	
	/**
	 * 企业获得支持表单
	 * @Title: add
	 * @Description:
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isRoot", false);
		params.put("type",getUser().getType());
		params.put("userId",getUser().getId());
		SysConfig syscong= sysConfigService.getConfigById("supportCategory");
		String val= syscong.getConfigValue();
		if(val!=null){
			model.addAttribute("categorys",val.split("[,]"));
		}
		model.addAttribute("orgList", hospitalService.findList(params));
		return "after/obtainSupport/edit";
	}

	/**
	 * 更具ID获取企业获得支持信息
	 * @Title: edit
	 * @Description: TODO
	 */
	@RequestMapping("/edit/{id}")
	public String edit(Model model, @PathVariable(value="id") String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isRoot", false);
		params.put("type",getUser().getType());
		params.put("userId",getUser().getId());
		SysConfig syscong= sysConfigService.getConfigById("supportCategory");
		String val= syscong.getConfigValue();
		if(val!=null){
			model.addAttribute("categorys",val.split("[,]"));
		}
		model.addAttribute("orgList", hospitalService.findList(params));
		model.addAttribute("obj", obtainSupportService.findObjectById(id));
		return "after/obtainSupport/edit";
	}

	/**
	 * 保存企业获得支持
	 * @Title: saveOrUpdate
	 * @Description: TODO
	 */
	@RequestMapping("/save")
	@ResponseBody
	public JsonEntity saveOrUpdate(ObtainSupport obtainSupport) {
		JsonEntity js = new JsonEntity();
		if (!ValidateEntity(obtainSupport, js)) {
			return js;
		}
		if (StringUtils.isEmpty(obtainSupport.getId())) {
			obtainSupport.setId(UUIDUtils.getUUID());
			js.setCode(obtainSupportService.insert(obtainSupport));
		} else {
			js.setCode(obtainSupportService.update(obtainSupport));
		}
		return js;
	}

	/**
	 * 删除企业获得支持
	 * @Title: deleteObtainSupport
	 * @Description: TODO
	 */
	@RequestMapping("/del")
	@ResponseBody
	public JsonEntity delete(String id) {
		if (StringUtils.isEmpty(id)) {
			throw new RuntimeException("参数不能为空");
		}
		return new JsonEntity(obtainSupportService.delete(id.split("[`]")));
	}
	
	/**
	 * 通用ajax校验
	 * @Description: TODO
	 * @Title: validate
	 * @author: sunshine 
	 */
	@RequestMapping("/validate")
	@ResponseBody
    public JsonEntity validate(ObtainSupport obtainSupport,String fieldId,String fieldValue, String old_field,HttpServletRequest request,HttpServletResponse response){
		JsonEntity je= new JsonEntity();
		je.setData(fieldId);
	if (StringUtils.isEmpty(old_field) || !old_field.equals(fieldValue)){
		ReflectionUtils.setFieldValue(obtainSupport, fieldId, fieldValue);
		int ct=obtainSupportService.validate(obtainSupport);
		if(ct!=0){
			je.setStatus(JsonEntity.fail);
		}
    }
		return je;
	}
}
