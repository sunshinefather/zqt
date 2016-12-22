package com.zyt.web.after.development.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

import com.zyt.web.after.development.service.IDevelopmentService;
import com.zyt.web.after.hospital.service.HospitalService;
import com.zyt.web.publics.base.BaseController;
import com.zyt.web.publics.base.JsonEntity;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.development.bean.Development;
import com.zyt.web.publics.module.development.bean.DevelopmentExport;
import com.zyt.web.publics.module.hospital.bean.Hospital;
import com.zyt.web.publics.utils.UUIDUtils;
import com.zyt.web.publics.utils.date.DateUtil;
import com.zyt.web.publics.utils.fileparser.ExportExcel;
import com.zyt.web.publics.utils.reflection.ReflectionUtils;

/**
 * @ClassName: DevelopmentController
 * @Description:企业发展管理控制层
 * @author: sunshine
 */
@Controller
@RequestMapping("/after/development")
public class DevelopmentController extends BaseController {
	private Map<String, String> templateColumns=new LinkedHashMap<String, String>();
	//配置导出模板
	{
		  templateColumns.put("企业名称", "hospitalName");
		  templateColumns.put("产值","chanzhi");
		  templateColumns.put("上年产值","lastYearDevelopment.chanzhi");
		  templateColumns.put("同比","differchanzhi");
		  templateColumns.put("销售收入","xiaoshou");
		  templateColumns.put("上年销售收入","lastYearDevelopment.xiaoshou");
		  templateColumns.put(" 同比","differxiaoshou");
		  templateColumns.put("利润","lirun");
		  templateColumns.put("上年利润","lastYearDevelopment.lirun");
		  templateColumns.put("  同比","differlirun");
		  templateColumns.put("税收","shuishou");
		  templateColumns.put("上年税收","lastYearDevelopment.shuishou");
		  templateColumns.put("   同比","differshuishou");
		  templateColumns.put("用水","yongshui");
		  templateColumns.put("上年用水","lastYearDevelopment.yongshui");
		  templateColumns.put("    同比","differyongshui");
		  templateColumns.put("用电","yongdian");
		  templateColumns.put("上年用电","lastYearDevelopment.yongdian");
		  templateColumns.put("同比 ","differyongdian");
		  templateColumns.put("用气","yongqi");
		  templateColumns.put("上年用气","lastYearDevelopment.yongqi");
		  templateColumns.put("同比  ","differyongqi");
		  templateColumns.put("固定资产投资","gudingzichantouzi");
		  templateColumns.put("上年固定资产投资","lastYearDevelopment.gudingzichantouzi");
		  templateColumns.put("同比   ","differgudingzichantouzi");
		  templateColumns.put("出口创汇","chukouchuanghui");
		  templateColumns.put("上年出口创汇","lastYearDevelopment.chukouchuanghui");
		  templateColumns.put("同比    ","differchukouchuanghui");
		  templateColumns.put("人数","str1");
	}

    @Resource
	private IDevelopmentService developmentService;
    
	@Autowired
	private HospitalService hospitalService;
	
    /** 
     *企业发展管理分页列表数据
	 * @Title: list
	 * @Description: 分页获取企业发展管理列表数据
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
		model.addAttribute("t1",DateUtil.parseToString(new Date(), "yyyy")+"-01");
		model.addAttribute("t2",DateUtil.parseToString(new Date(), "yyyy-MM"));
		model.addAttribute("list", developmentService.findList(page));
		return "after/development/list";
	}
	
   /** 
     *企业发展管理列表数据
	 * @Title: list
	 * @Description: 获取企业发展管理列表数据
	 */
	@RequestMapping("/loadlist")
	public String loadList(Model model,Development development){
		model.addAttribute("list", developmentService.queryList(development));
		return "after/development/loadlist";
	}
	
	/**
	 * 企业发展管理表单
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
		return "after/development/edit";
	}

	/**
	 * 更具ID获取企业发展管理信息
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
		model.addAttribute("obj", developmentService.findObjectById(id));
		return "after/development/edit";
	}

	/**
	 * 保存企业发展管理
	 * @Title: saveOrUpdate
	 * @Description: TODO
	 */
	@RequestMapping("/save")
	@ResponseBody
	public JsonEntity saveOrUpdate(Development development) {
		JsonEntity js = new JsonEntity();
		if (!ValidateEntity(development, js)) {
			return js;
		}
		if (StringUtils.isEmpty(development.getId())) {
			development.setId(UUIDUtils.getUUID());
			js.setCode(developmentService.insert(development));
		} else {
			js.setCode(developmentService.update(development));
		}
		return js;
	}

	/**
	 * 删除企业发展管理
	 * @Title: deleteDevelopment
	 * @Description: TODO
	 */
	@RequestMapping("/del")
	@ResponseBody
	public JsonEntity delete(String id) {
		if (StringUtils.isEmpty(id)) {
			throw new RuntimeException("参数不能为空");
		}
		return new JsonEntity(developmentService.delete(id.split("[`]")));
	}
	
	/**
	 * 通用ajax校验
	 * @Description: TODO
	 * @Title: validate
	 * @author: sunshine 
	 */
	@RequestMapping("/validate")
	@ResponseBody
    public JsonEntity validate(Development development,String fieldId,String fieldValue, String old_field,HttpServletRequest request,HttpServletResponse response){
		JsonEntity je= new JsonEntity();
		je.setData(fieldId);
	if (StringUtils.isEmpty(old_field) || !old_field.equals(fieldValue)){
		ReflectionUtils.setFieldValue(development, fieldId, fieldValue);
		int ct=developmentService.validate(development);
		if(ct!=0){
			je.setStatus(JsonEntity.fail);
		}
    }
		return je;
	}
	/**
	 * 导出报表
	 * @Title: exportExcel
	 * @Description: TODO  
	 * @param: @param request
	 * @param: @param response
	 * @param: @param year      
	 * @return: void
	 * @author: sunshine  
	 * @throws
	 */
	@RequestMapping("/export")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,String t1,String t2,String hospitalId){
		if("0".equals(getUser().getType())){
			 if(StringUtils.isBlank(t1)){
				 t1=DateUtil.parseToString(new Date(), "yyyy")+"-01";
			 }
			 if(StringUtils.isBlank(t2)){
				 t2=DateUtil.parseToString(new Date(), "yyyy-MM");
			 }
			 Map<String,String> parm_map = new HashMap<String,String>();
			 parm_map.put("t1", t1);
			 parm_map.put("t2", t2);
			 parm_map.put("hospitalId",hospitalId);
			 
			List<Development> list =  developmentService.getYearExport(parm_map);
			Map<String,String> parm_map2 = new HashMap<String,String>();
			Date  date=DateUtil.parseToDate(t1, "yyyy-MM");
			Date  date1=DateUtil.parseToDate(t2, "yyyy-MM");
			String t3=(Integer.parseInt(DateUtil.parseToString(date, "yyyy"))-1)+"-"+DateUtil.parseToString(date, "MM");
			String t4=(Integer.parseInt(DateUtil.parseToString(date1, "yyyy"))-1)+"-"+DateUtil.parseToString(date1, "MM");
			parm_map2.put("t1", t3);
			parm_map2.put("t2", t4);
			parm_map2.put("hospitalId",hospitalId);
			List<Development> lastYaerList =  developmentService.getYearExport(parm_map2);
			
			if(list!=null && list.size()>0){
				Map<String,DevelopmentExport> map =new LinkedHashMap<String,DevelopmentExport>();
				for(Development dp:list){
					map.put(dp.getHospitalId(),new DevelopmentExport(dp,dp.getHospitalId()));
				}
				if(lastYaerList!=null && lastYaerList.size()>0){
					for(Development dp:lastYaerList){
						if(map.containsKey(dp.getHospitalId())){
							map.get(dp.getHospitalId()).setLastYearDevelopment(dp);
						}else{
						  DevelopmentExport crudep=new DevelopmentExport(new Development(),dp.getHospitalId());
						  crudep.setLastYearDevelopment(dp);
						  map.put(dp.getHospitalId(),crudep);
						}
					}
				}
				List<DevelopmentExport> listrt= new ArrayList<DevelopmentExport>();
				for(Entry<String,DevelopmentExport> entry:map.entrySet()){
					DevelopmentExport de = entry.getValue();
					Hospital hostpital =  hospitalService.getById(de.getHospitalId());
					if(hostpital!=null){
						de.setStr1(hostpital.getPeoples());//人数
						de.setHospitalName(hostpital.getHospitalName());
					}
					listrt.add(de);
				}
				ExportExcel ee= new ExportExcel(t1+"至"+t2+"企业信息统计报表", listrt, templateColumns, response);
				ee.exportExcel();
			}else{
				PrintWriter rt=null;
				try {
					rt= response.getWriter();
					rt.write("<h1>没有数据</h1>");
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					if(rt!=null){
						rt.flush();
						rt.close();
					}
				}
			}
		}else{
			PrintWriter rt=null;
			try {
				rt= response.getWriter();
				rt.write("非法操作,你的IP和账户已被记录!");
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(rt!=null){
					rt.flush();
					rt.close();
				}
			}
		}
	}
}
