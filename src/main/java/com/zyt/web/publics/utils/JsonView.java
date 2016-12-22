package com.zyt.web.publics.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.AbstractView;

import com.alibaba.fastjson.JSON;

/**
 * json 视图
 * 
 * @author maliang
 * 
 */
public class JsonView extends AbstractView {
	private Logger logger = LoggerFactory.getLogger(JsonView.class);
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/plain");
		String jsoncallback = request.getParameter("jsoncallback");
		if(StringUtils.isNotBlank(jsoncallback)){
			logger.info(String.format("跨域访问，回调：%s", jsoncallback));
			
			response.setHeader("Access-Control-Allow-Origin","*");
			response.getWriter().write(jsoncallback+"("+JSON.toJSONString(model) + ")");
		}else{
			response.getWriter().write(JSON.toJSONString(model));
		}
	}

}
