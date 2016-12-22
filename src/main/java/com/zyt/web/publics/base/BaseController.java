package com.zyt.web.publics.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.sysmanager.bean.User;
import com.zyt.web.publics.utils.HibernateValidatorFactoryUtils;
import com.zyt.web.publics.utils.StringUtil;
import com.zyt.web.publics.utils.SystemConstantUtils;
public class BaseController {

    private final Logger log = LoggerFactory.getLogger(BaseController.class);

    /**
     * @描述 异常拦截
     * @auto maliang
     * @time 2014-3-3 下午6:14:09
     * @param re
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler(value = { Exception.class })
    public ModelAndView handleException(RuntimeException re, HttpServletRequest request, HttpServletResponse response) {
        String XMLHttpRequest = request.getHeader("x-requested-with");
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("result", false);
        String errMsg = re.getMessage();
        model.put("message", errMsg.indexOf("Duplicate") > 0 ?"数据已存在，不能重复添加！":errMsg.indexOf("database") > 0 ? "sql语句错误" : re.getMessage());
        return new ModelAndView(XMLHttpRequest != null && !XMLHttpRequest.equals("") ? "jsonView" : "error", model);
    }

    public boolean pushValueToSession(HttpServletRequest request, HttpServletResponse response, String key, Object object) {
        boolean b = false;
        try {
        	request.getSession().setAttribute(key,object);
            b = true;
        } catch (Exception e) {
            log.error("装载数据错误!");
            throw new RuntimeException("装载数据错误!");
        }
        return b;
    }

    public boolean deleteValueFromSession(HttpServletRequest request, HttpServletResponse response, String key) {
        boolean b = false;
        try {
        	request.getSession().removeAttribute(key);
            b = true;
        } catch (Exception e) {
            log.error("删除数据错误!");
            throw new RuntimeException("删除数据错误!");
        }
        return b;
    }

    /**
     * @描述  page 对象参数设定
     *     <p>
     *     请求参数在service实现设定
     * @auto maliang
     * @time 2014-3-6 上午10:34:22
     * @param pageNo
     *            当前页码
     * @param pageSize
     *            每页长度
     * @return PaginationAble
     */
    public PaginationAble setPaginationAbleParams(String pageNo, int pageSize) {
        PaginationAble page = PaginationAble.getPaginationAble();
        // 请求参数是string 类型
        int pageNum = 1;
        try {
            pageNum = Integer.valueOf(pageNo);
        } catch (Exception e) {
            pageNum = 1;
        }
        page.setPageNo(pageNum);
        page.setPageSize(pageSize);
        return page;
    }


    /**
     * @描述 返回参数集合
     * @author maliang
     * @time 2014-3-17 上午10:38:34
     * @version v1.0
     * @return
     */
    protected Map<String, Object> getParams() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String[]> params = request.getParameterMap();
        if (params != null && params.size() > 0) {
            // 实例化一个顺序map 避免参数顺序错乱
            Map<String, Object> nativeMap = new LinkedHashMap<String, Object>();
            for (String paramKey : params.keySet()) {
            	String v = request.getParameter(paramKey);
            	if(request.getMethod().equalsIgnoreCase("get")){  
                    try {
                    	v = StringUtil.toUTF8(v);
					} catch (Exception e) {
						v =  request.getParameter(paramKey);
					}
                }
                nativeMap.put(paramKey, v);
            }
            return nativeMap.size() == 0 ? null : nativeMap;
        }
        return new HashMap<String, Object>();
    }

    protected Map<String, Object> getParams(Model model) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String[]> params = request.getParameterMap();
        if (params != null && params.size() > 0) {
            Map<String, Object> nativeMap = new LinkedHashMap<String, Object>();
            for (String key : params.keySet()) {
            	String v = request.getParameter(key);
            	if(request.getMethod().equalsIgnoreCase("get")){
                    try {
                    	v = StringUtil.toUTF8(v);
					} catch (Exception e) {
						v = request.getParameter(key);
					}
                }
                nativeMap.put(key, v);
                if (model != null)
                    model.addAttribute(key, v);
            }
            return nativeMap.size() == 0 ? null : nativeMap;
        }
        return new HashMap<String, Object>();
    }

    /**
     * 
     * @Description: 获取session中存储的用户
     * @return User
     * @version: v1.0.0
     * @author: LiuChuang
     * @date: 2014-3-25下午3:03:11
     */
    public User getUser() {
    	User user=null;
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().setAttribute(SystemConstantUtils.SystemConstant.SESSIONUSER, user);
        return user;
    }


    public Map<String, Object> getErrors(BindingResult... bindingResults) {
        Map<String, Object> errors = new LinkedHashMap<String, Object>();
        if (bindingResults != null && bindingResults.length > 0) {
            List<Object> errorObjects = new ArrayList<Object>();
            for (BindingResult bindingResult : bindingResults) {
                if (bindingResult.hasErrors()) {
                    errorObjects.add(bindingResult.getTarget());
                    if (!errors.containsKey("result")) {
                        errors.put("result", false);
                        errors.put("message", "操作失败,验证未通过");
                    }
                    List<FieldError> fieldErrors = bindingResult.getFieldErrors();
                    for (FieldError fieldError : fieldErrors) {
                        errors.put(fieldError.getField(), fieldError.getDefaultMessage());
                    }
                }
            }
            if (errorObjects.size() > 0) {
                errors.put("object", errorObjects);
            }
        }
        if (!errors.containsValue(false)) {
            errors.put("result", true);
            errors.put("message", "验证通过");
        }
        return errors;
    }

    public boolean ValidateEntity(Serializable obj, JsonEntity js) {
        Map<String, Object> map = HibernateValidatorFactoryUtils.verifyObject(obj);
        StringBuffer str;
        if (map != null && map.size() > 0) {
            str = new StringBuffer();
            int ct = 0;
            for (String key : map.keySet()) {
                if (!("result".equals(key) || "message".equals(key))) {
                    if (ct++ != 0) {
                        str.append(",");
                    }
                    str.append(map.get(key));
                }
            }
            js.setStatus(JsonEntity.fail);
            js.setMsg(str.toString());

        }
        return js.getStatus().equals(JsonEntity.success);
    }
    
    /**
     * 验证用户是否登录
     * @param user
     * @return
     */
    public String validateUser(User user) {
    	if (user == null)
    		user = this.getUser();
    			if (user == null)
    				return "/index";
    	return null;
    }

    /**
     * @描述:上传文件
     * @auto:maliang
     * @time:2014-3-31
     * @param multipartFile
     * @return <p>
     *         未具体实现
     */
    @RequestMapping("/uploadfile")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        Map<String, Object> valis = new HashMap<String, Object>();
        if (multipartFile.isEmpty()) {

        } else {
            valis.put("result", false);
            valis.put("message", "选择上传文件");
        }
        return JSON.toJSONString(valis);
    }

}
