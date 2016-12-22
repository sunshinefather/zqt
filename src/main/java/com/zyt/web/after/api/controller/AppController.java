package com.zyt.web.after.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyt.web.after.agreement.service.IAgreementService;
import com.zyt.web.after.cms.service.IContentService;
import com.zyt.web.after.development.service.IDevelopmentService;
import com.zyt.web.after.hospital.service.HospitalService;
import com.zyt.web.after.product.service.IProductService;
import com.zyt.web.publics.base.JsonEntity;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.agreement.bean.Agreement;
import com.zyt.web.publics.module.cms.bean.CmsAtt;
import com.zyt.web.publics.module.cms.bean.Content;
import com.zyt.web.publics.module.development.bean.Development;
import com.zyt.web.publics.module.hospital.bean.Hospital;
import com.zyt.web.publics.module.sysmanager.bean.User;
import com.zyt.web.publics.utils.date.DateUtil;

@Controller
@RequestMapping(value="/api")
public class AppController extends ApiController{
	
    @Resource
	private IContentService contentService;
    
    @Resource
    private IProductService productService; 
    
    @Resource
	private IDevelopmentService developmentService;
    
	@Autowired
	private HospitalService hospitalService;
	
	@Autowired
	private IAgreementService agreementService;
	
	@RequestMapping("/yq/gk")
	@ResponseBody
	public JsonEntity gk(){
		JsonEntity js =new JsonEntity();
		js.setData(contentService.findObjectById("1"));
		return js;
	}
	
	@RequestMapping("/yq/fzlc")
	@ResponseBody
	public JsonEntity fzlc(){
		JsonEntity js =new JsonEntity();
		js.setData(contentService.findObjectById("2"));
		return js;
	}
	@RequestMapping("/yq/gh")
	@ResponseBody
	public JsonEntity gh(){
		JsonEntity js =new JsonEntity();
		js.setData(contentService.findObjectById("3"));
		return js;
	}
	@RequestMapping("/yq/cyfz")
	@ResponseBody
	public JsonEntity cyfz(){
		JsonEntity js =new JsonEntity();
		js.setData(contentService.findObjectById("4"));
		return js;
	}
	
	@RequestMapping("/yq/zhengce")
	@ResponseBody
	public JsonEntity zhengce(PaginationAble page){
		JsonEntity js =new JsonEntity();
		page.getWhereParameters().putAll(getParams());
		page.getWhereParameters().put("categoryId","3");
		List<Content> contentList= contentService.findList(page);
		List<Map<String,String>> contentMap= new ArrayList<Map<String,String>>();
		if(contentList!=null && !contentList.isEmpty()){
			for(Content cont:contentList){
				Map<String,String> map =new HashMap<String,String>();
				map.put("id", cont.getId());
				map.put("title", cont.getTitle());
				map.put("content", cont.getContent());
				map.put("datetime", cont.getStr1());
				contentMap.add(map);
			}
		}
		page.setResults(contentMap);
		js.setData(page);
		return js;
	}
	
	@RequestMapping("/yq/zhengce/{id}")
	@ResponseBody
	public JsonEntity zhengceDetial(@PathVariable("id")String id){
		JsonEntity js =new JsonEntity();
		Content cont= contentService.findObjectById(id);
		Map<String,Object> map= new HashMap<String,Object>();
		if(cont!=null ){
			map.put("id", cont.getId());
			map.put("title", cont.getTitle());
			map.put("datetime", cont.getStr1());
			map.put("content", cont.getContent());
			List<Map<String,String>> list=new ArrayList<Map<String,String>>();
			List<CmsAtt> atts= cont.getAtts();
            if(atts!=null && !atts.isEmpty()){
            	for(CmsAtt att:atts){
        			Map<String,String> _map= new HashMap<String,String>();
					_map.put("attName",att.getAttName());
					_map.put("attPath",att.getAttUrl());
					list.add(_map);
            	}
            }
			map.put("attachment",list);
		}
		js.setData(map);
		return js;
	}
	
	@RequestMapping("/yq/chuangye/{id}")
	@ResponseBody
	public JsonEntity chuangyeDetial(@PathVariable("id")String id){
		JsonEntity js =new JsonEntity();
		Content cont= contentService.findObjectById(id);
		Map<String,Object> map= new HashMap<String,Object>();
		if(cont!=null ){
			map.put("id", cont.getId());
			map.put("title", cont.getTitle());
			map.put("datetime", cont.getStr1());
			map.put("content", cont.getContent());
			List<Map<String,String>> list=new ArrayList<Map<String,String>>();
			List<CmsAtt> atts= cont.getAtts();
            if(atts!=null && !atts.isEmpty()){
            	for(CmsAtt att:atts){
        			Map<String,String> _map= new HashMap<String,String>();
					_map.put("attName",att.getAttName());
					_map.put("attPath",att.getAttUrl());
					list.add(_map);
            	}
            }
			map.put("attachment",list);
		}
		js.setData(map);
		return js;
	}
	@RequestMapping("/yq/chuangye")
	@ResponseBody
	public JsonEntity chuangye(PaginationAble page){
		JsonEntity js =new JsonEntity();
		page.getWhereParameters().putAll(getParams());
		page.getWhereParameters().put("categoryId","5");
		List<Content> contentList= contentService.findList(page);
		List<Map<String,String>> contentMap= new ArrayList<Map<String,String>>();
		if(contentList!=null && !contentList.isEmpty()){
			for(Content cont:contentList){
				Map<String,String> map =new HashMap<String,String>();
				map.put("id", cont.getId());
				map.put("title", cont.getTitle());
				map.put("content", cont.getContent());
				map.put("datetime", cont.getStr1());
				contentMap.add(map);
			}
		}
		page.setResults(contentMap);
		js.setData(page);
		return js;
	}
	
	@RequestMapping("/yq/gonggao/{id}")
	@ResponseBody
	public JsonEntity gonggaoDetial(@PathVariable("id")String id){
		JsonEntity js =new JsonEntity();
		Content cont= contentService.findObjectById(id);
		Map<String,Object> map= new HashMap<String,Object>();
		if(cont!=null ){
			map.put("id", cont.getId());
			map.put("title", cont.getTitle());
			map.put("datetime", cont.getStr1());
			map.put("content", cont.getContent());
			List<Map<String,String>> list=new ArrayList<Map<String,String>>();
			List<CmsAtt> atts= cont.getAtts();
            if(atts!=null && !atts.isEmpty()){
            	for(CmsAtt att:atts){
        			Map<String,String> _map= new HashMap<String,String>();
					_map.put("attName",att.getAttName());
					_map.put("attPath",att.getAttUrl());
					list.add(_map);
            	}
            }
			map.put("attachment",list);
		}
		js.setData(map);
		return js;
	}
	@RequestMapping("/yq/gonggao")
	@ResponseBody
	public JsonEntity gonggao(PaginationAble page){
		JsonEntity js =new JsonEntity();
		page.getWhereParameters().putAll(getParams());
		page.getWhereParameters().put("categoryId","4");
		List<Content> contentList= contentService.findList(page);
		List<Map<String,Object>> contentMap= new ArrayList<Map<String,Object>>();
		if(contentList!=null && !contentList.isEmpty()){
			for(Content cont:contentList){
				Map<String,Object> map =new HashMap<String,Object>();
				map.put("id", cont.getId());
				map.put("title", cont.getTitle());
				map.put("content", cont.getContent());
				map.put("datetime", cont.getStr1());
				List<Map<String,String>> list=new ArrayList<Map<String,String>>();
				List<CmsAtt> atts= cont.getAtts();
	            if(atts!=null && !atts.isEmpty()){
	            	for(CmsAtt att:atts){
	        			Map<String,String> _map= new HashMap<String,String>();
						_map.put("attName",att.getAttName());
						_map.put("attPath",att.getAttUrl());
						list.add(_map);
	            	}
	            }
				map.put("attachment",list);
				contentMap.add(map);
			}
		}
		page.setResults(contentMap);
		js.setData(page);
		return js;
	}
	
	@RequestMapping("/product/list")
	@ResponseBody
	public JsonEntity productList(PaginationAble page,String token,String companyId){
		JsonEntity js =new JsonEntity();
		
		String userWidthCompanyId="";
        if(StringUtils.isNotEmpty(token) && StringUtils.isEmpty(companyId)){
           User user = getUser(token);
           if(user!=null){
        	   userWidthCompanyId=user.getExtUser().getOrgId(); 
           }
        }
        
        if(StringUtils.isEmpty(userWidthCompanyId) && StringUtils.isEmpty(companyId)){
        	js.setStatus(JsonEntity.paramerror);
        	js.setMsg("请选择企业");
        	return js;
        }
        page.getWhereParameters().put("hospitalId",StringUtils.isNotEmpty(companyId)?companyId:userWidthCompanyId);
        page.setResults(productService.findList(page));
        js.setData(page);
		return js;
	}
	
	@RequestMapping("/product/{id}")
	@ResponseBody
	public JsonEntity productById(@PathVariable("id")String id){
		JsonEntity js =new JsonEntity();
		js.setData(productService.findObjectById(id));
		return js;
	}
	
	@RequestMapping("/qy/fz")
	@ResponseBody
	public JsonEntity qyfz(String token,String companyId,String statisticsTime){
		JsonEntity js =new JsonEntity();
        if(StringUtils.isEmpty(companyId)){
           User user = getUser(token);
           if(user!=null){
        	   companyId=user.getExtUser().getOrgId(); 
           }
        }
        if(StringUtils.isEmpty(companyId)){
        	js.setStatus(JsonEntity.paramerror);
        	js.setMsg("参数错误");
        	return js;
        }
        
        if(StringUtils.isNotEmpty(statisticsTime)){
        	if(DateUtil.parseToDate(statisticsTime, "yyyy-MM")==null){
        		js.setStatus(JsonEntity.fmterror);
            	js.setMsg("参数错误");
            	return js;
        	}
        }
		Development dp =new Development();
		dp.setHospitalId(companyId);
		dp.setStatisticsTime(statisticsTime);
		List<Development> dplist=developmentService.queryList(dp);
		Hospital hp= hospitalService.getById(companyId);
		
		if(dplist!=null && !dplist.isEmpty() && hp!=null){
			Development _dp=developmentService.queryList(dp).get(0);
			_dp.setHospitalName(hp.getHospitalName());
			_dp.setStr1(hp.getPeoples());
			_dp.setStr2(hp.getTechnicalPatent());
			js.setData(_dp);	
		}
		return js;
	}
	@RequestMapping("/qy/agreement")
	@ResponseBody
	public JsonEntity qyAgreement(String token,String companyId){
		JsonEntity js =new JsonEntity();
        if(StringUtils.isEmpty(companyId)){
            User user = getUser(token);
            if(user!=null){
         	   companyId=user.getExtUser().getOrgId(); 
            }
         }
         if(StringUtils.isEmpty(companyId)){
         	js.setStatus(JsonEntity.paramerror);
         	js.setMsg("参数错误");
         	return js;
         }
        Agreement t= new Agreement();
        t.setHospitalId(companyId);
        
        List<Agreement> aglist=agreementService.queryList(t);
        Hospital hp= hospitalService.getById(companyId);
        if(aglist!=null && !aglist.isEmpty() && hp!=null){
        	Agreement _ag=aglist.get(0);
        	_ag.setHospitalName(hp.getHospitalName());
        	js.setData(_ag);	
        }
        return js;
	}
	
}