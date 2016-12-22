package com.zyt.web.publics.tags;

import httl.util.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.alibaba.fastjson.JSONObject;
import com.zyt.web.after.config.service.SysConfigService;
import com.zyt.web.after.sysmanager.service.IUserService;
import com.zyt.web.publics.module.config.bean.SysConfig;

/**
 * 组件标签
 * 
 * @ClassName: PageSelect
 * @Description:
 * @author: sunshine
 * @date: 2014年3月27日 下午1:18:41
 */
public class PageSelect extends RequestContextAwareTag {

	private static final long serialVersionUID = 1L;
//	private static final String wraprow = "\b\n";
//	private static final String format = "\t";
	private SysConfigService sysConfigService;
	
	private IUserService userService;

	// 请求key
	private String key;

	// 默认值,用于编辑
	private String defaultValue;

	// html 标签元素 id属性
	private String id;

	// html 标签元素name属性
	private String name;

	// html 标签元素 class属性
	private String css;

	// html 标签元素 style属性
	private String style;

	private String value;

	/**
	 * 生成html
	 * 
	 * @Title: createHtmlDom
	 * @Description:
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	private String createHtmlDom() {
		StringBuilder sb = new StringBuilder("<select ");
		if (StringUtils.isNotBlank(this.name)) 
			sb.append("name=\"" + this.name + "\"");
		if (StringUtils.isNotBlank(this.id)) 
			sb.append("id=\"" + this.id + "\"");
		if (StringUtils.isNotBlank(this.css)) 
			sb.append("class=\"" + this.css + "\"");
		if (StringUtils.isNotBlank(this.style))
			sb.append("style=\"" + this.style + "\"");
		sb.append(" >");
		sb.append(createOption());
		sb.append("</select>");
		return sb.toString();
	}

	/**
	 * 生成option选项
	 * 
	 * @Title: getOption
	 * @Description:
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	private String createOption() {
		SysConfig sc = sysConfigService.getConfigById(key);
		StringBuffer sb = new StringBuffer();
		if (sc != null) {
			JSONObject js = JSONObject.parseObject(sc.getConfigValue());
			if (js != null) {
				//选择专家类型时需要处理专家类型的可选项
				if ("professional".equals(key)) {
					String pros = userService.getProfessionals(defaultValue);
					forEach(sb, pros, js);
				} else 
					forEach(sb, null, js);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * @param sb
	 * @param pros
	 * @param js
	 */
	private void forEach(StringBuffer sb, String pros, JSONObject js) {
		if (StringUtils.isNotBlank(pros)) {
			for (String key : js.keySet()) {
				if (pros.contains(key))
					continue;
				sb.append("<option value=\"");
				sb.append(key + "\"");
				if (StringUtils.isNotBlank(defaultValue) && defaultValue.equals(key))
					sb.append("selected=\"selected\"");
				sb.append(" >");
				sb.append(js.get(key));
				sb.append("</option>");
			}
		} else {
			for (String key : js.keySet()) {
				sb.append("<option value=\"");
				sb.append(key + "\"");
				if (StringUtils.isNotBlank(defaultValue) && defaultValue.equals(key)) 
					sb.append("selected=\"selected\"");
				sb.append(" >");
				sb.append(js.get(key));
				sb.append("</option>");
			}
		}
		
	}

	private Object getValueByKey() {
		SysConfig sc = sysConfigService.getConfigById(key);
		if (sc != null) {
			JSONObject js = JSONObject.parseObject(sc.getConfigValue());
			return js.get(value);
		}
		return null;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public SysConfigService getSysConfigService() {
		return sysConfigService;
	}

	public void setSysConfigService(SysConfigService sysConfigService) {
		this.sysConfigService = sysConfigService;
	}
	
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	protected int doStartTagInternal() throws Exception {
		JspWriter out = this.pageContext.getOut();
		try {
			init();
			if (this.value == null)
				out.write(createHtmlDom());
			else {
				Object obj = getValueByKey();
				out.write(obj != null ? (String) obj : "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new JspException("标签解析错误");
		}
		return EVAL_PAGE;
	}

	private void init() {
		//从webapplicationcontext中获取Bean
		WebApplicationContext wc = getRequestContext()
				.getWebApplicationContext();
		SysConfigService scs = (SysConfigService) wc
				.getBean("sysConfigServiceImpl");
		IUserService us = (IUserService) 
				wc.getBean("userServiceImpl");
		setUserService(us);
		setSysConfigService(scs);
	}


	
	
}