package com.zyt.web.publics.cluster;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 分布式session过滤器
 * @ClassName:  DistributedSessionFilter   
 * @Description:TODO   
 * @author: sunshine  
 * @date:   2016年11月10日 下午4:34:29
 */
public class DistributedSessionFilter implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(DistributedSessionFilter.class);
			
	private FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
        DistributedHttpServletRequest distributedReques =new DistributedHttpServletRequest(httpRequest,httpResponse,this.filterConfig.getServletContext());
        chain.doFilter(distributedReques, httpResponse);
	}

	@Override
	public void destroy() {
		
	}
}