package com.zyt.web.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import com.zyt.web.publics.utils.SystemConstantUtils;

public class SecurityFilter extends AbstractSecurityInterceptor implements Filter {

    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        FilterInvocation filterInvocation = new FilterInvocation(request, response, chain);
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        invoke(filterInvocation,req,resp);

    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    public void invoke(FilterInvocation filterInvocation, HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException,AuthenticationException {
    	InterceptorStatusToken token = super.beforeInvocation(filterInvocation);
		if(token==null){
			Object user=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			//如果未登录(匿名用户)跳转到登陆页面，否则放行
			if("anonymousUser".equals(user) || request.getSession().getAttribute(SystemConstantUtils.SystemConstant.SESSIONUSER)==null){
				String url=filterInvocation.getRequest().getContextPath()+"/index";
				filterInvocation.getResponse().sendRedirect(url);
				return;
			}
		}
		try {
			filterInvocation.getChain().doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
    }
    
    public FilterInvocationSecurityMetadataSource getFilterInvocationSecurityMetadataSource() {
        return securityMetadataSource;
    }

    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
        return securityMetadataSource;
    }

    public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }
}
