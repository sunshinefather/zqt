package com.zyt.web.after.product.controller;

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

import com.zyt.web.after.hospital.service.HospitalService;
import com.zyt.web.after.product.service.IProductService;
import com.zyt.web.publics.base.BaseController;
import com.zyt.web.publics.base.JsonEntity;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.hospital.bean.Hospital;
import com.zyt.web.publics.module.product.bean.Product;
import com.zyt.web.publics.utils.UUIDUtils;
import com.zyt.web.publics.utils.reflection.ReflectionUtils;

/**
 * @ClassName: ProductController
 * @Description:企业产品控制层
 * @author: sunshine
 */
@Controller
@RequestMapping("/after/product")
public class ProductController extends BaseController {

    @Resource
	private IProductService productService;
	@Autowired
	private HospitalService hospitalService;
	
    /** 
     *企业产品分页列表数据
	 * @Title: list
	 * @Description: 分页获取企业产品列表数据
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
		model.addAttribute("list", productService.findList(page));
		return "after/product/list";
	}
	
   /** 
     *企业产品列表数据
	 * @Title: list
	 * @Description: 获取企业产品列表数据
	 */
	@RequestMapping("/loadlist")
	public String loadList(Model model,Product product){
		model.addAttribute("list", productService.queryList(product));
		return "after/product/loadlist";
	}
	
	/**
	 * 企业产品表单
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
		return "after/product/edit";
	}

	/**
	 * 更具ID获取企业产品信息
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
		model.addAttribute("obj", productService.findObjectById(id));
		return "after/product/edit";
	}

	/**
	 * 保存企业产品
	 * @Title: saveOrUpdate
	 * @Description: TODO
	 */
	@RequestMapping("/save")
	@ResponseBody
	public JsonEntity saveOrUpdate(Product product) {
		JsonEntity js = new JsonEntity();
		if (!ValidateEntity(product, js)) {
			return js;
		}
		if (StringUtils.isEmpty(product.getId())) {
			product.setId(UUIDUtils.getUUID());
			js.setCode(productService.insert(product));
		} else {
			js.setCode(productService.update(product));
		}
		return js;
	}

	/**
	 * 删除企业产品
	 * @Title: deleteProduct
	 * @Description: TODO
	 */
	@RequestMapping("/del")
	@ResponseBody
	public JsonEntity delete(String id) {
		if (StringUtils.isEmpty(id)) {
			throw new RuntimeException("参数不能为空");
		}
		return new JsonEntity(productService.delete(id.split("[`]")));
	}
	
	/**
	 * 通用ajax校验
	 * @Description: TODO
	 * @Title: validate
	 * @author: sunshine 
	 */
	@RequestMapping("/validate")
	@ResponseBody
    public JsonEntity validate(Product product,String fieldId,String fieldValue, String old_field,HttpServletRequest request,HttpServletResponse response){
		JsonEntity je= new JsonEntity();
		je.setData(fieldId);
	if (StringUtils.isEmpty(old_field) || !old_field.equals(fieldValue)){
		ReflectionUtils.setFieldValue(product, fieldId, fieldValue);
		int ct=productService.validate(product);
		if(ct!=0){
			je.setStatus(JsonEntity.fail);
		}
    }
		return je;
	}
}
