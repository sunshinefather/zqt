package com.zyt.web.publics.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class SpringHandlerExceptionResolver implements
		HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		if(ex instanceof SysBusinessException){
			//TODO 
		}else{
			return new ModelAndView("error-500");
		}
		return null;
	}

}
