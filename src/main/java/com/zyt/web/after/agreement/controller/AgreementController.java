package com.zyt.web.after.agreement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyt.web.after.agreement.service.IAgreementService;
import com.zyt.web.after.hospital.service.HospitalService;
import com.zyt.web.publics.base.BaseController;
import com.zyt.web.publics.base.JsonEntity;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.agreement.bean.Agreement;
import com.zyt.web.publics.module.hospital.bean.Hospital;
import com.zyt.web.publics.utils.UUIDUtils;
import com.zyt.web.publics.utils.reflection.ReflectionUtils;

/**
 * @ClassName: AgreementController
 * @Description:协议约定控制层
 * @author: sunshine
 */
@Controller
@RequestMapping("/after/agreement")
public class AgreementController extends BaseController {

    @Resource
	private IAgreementService agreementService;
    
	@Autowired
	private HospitalService hospitalService;
	
    /** 
     *协议约定分页列表数据
	 * @Title: list
	 * @Description: 分页获取协议约定列表数据
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
		model.addAttribute("list", agreementService.findList(page));
		return "after/agreement/list";
	}
	
   /** 
     *协议约定列表数据
	 * @Title: list
	 * @Description: 获取协议约定列表数据
	 */
	@RequestMapping("/loadlist")
	public String loadList(Model model,Agreement agreement){
		model.addAttribute("list", agreementService.queryList(agreement));
		return "after/agreement/loadlist";
	}
	
	/**
	 * 协议约定表单
	 * @Title: add
	 * @Description:
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isRoot", false);
		params.put("type",getUser().getType());
		params.put("userId",getUser().getId());
		model.addAttribute("orgList", hospitalService.findList(params));
		return "after/agreement/edit";
	}

	/**
	 * 更具ID获取协议约定信息
	 * @Title: edit
	 * @Description: TODO
	 */
	@RequestMapping("/edit/{id}")
	public String edit(Model model, @PathVariable(value="id") String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isRoot", false);
		params.put("type",getUser().getType());
		params.put("userId",getUser().getId());
		model.addAttribute("orgList", hospitalService.findList(params));
		model.addAttribute("obj", agreementService.findObjectById(id));
		return "after/agreement/edit";
	}

	/**
	 * 保存协议约定
	 * @Title: saveOrUpdate
	 * @Description: TODO
	 */
	@RequestMapping("/save")
	@ResponseBody
	public JsonEntity saveOrUpdate(Agreement agreement) {
		JsonEntity js = new JsonEntity();
		if (!ValidateEntity(agreement, js)) {
			return js;
		}
		if (StringUtils.isEmpty(agreement.getId())) {
			agreement.setId(UUIDUtils.getUUID());
			js.setCode(agreementService.insert(agreement));
		} else {
			js.setCode(agreementService.update(agreement));
		}
		return js;
	}

	/**
	 * 删除协议约定
	 * @Title: deleteAgreement
	 * @Description: TODO
	 */
	@RequestMapping("/del")
	@ResponseBody
	public JsonEntity delete(String id) {
		if (StringUtils.isEmpty(id)) {
			throw new RuntimeException("参数不能为空");
		}
		return new JsonEntity(agreementService.delete(id.split("[`]")));
	}
	
	/**
	 * 通用ajax校验
	 * @Description: TODO
	 * @Title: validate
	 * @author: sunshine 
	 */
	@RequestMapping("/validate")
	@ResponseBody
    public JsonEntity validate(Agreement agreement,String fieldId,String fieldValue, String old_field,HttpServletRequest request,HttpServletResponse response){
		JsonEntity je= new JsonEntity();
		je.setData(fieldId);
	if (StringUtils.isEmpty(old_field) || !old_field.equals(fieldValue)){
		ReflectionUtils.setFieldValue(agreement, fieldId, fieldValue);
		int ct=agreementService.validate(agreement);
		if(ct!=0){
			je.setStatus(JsonEntity.fail);
		}
    }
		return je;
	}
}
