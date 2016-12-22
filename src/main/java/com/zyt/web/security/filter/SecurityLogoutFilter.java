package com.zyt.web.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

/***
 * 
 * @ClassName: PalmLinkLogoutFilter
 * @Description: TODO
 * @author: Hadoop
 * @date:2013-12-10 下午12:41:40
 */
public class SecurityLogoutFilter extends LogoutFilter {

	public SecurityLogoutFilter(String logoutSuccessUrl,
			LogoutHandler[] handlers) {
		super(logoutSuccessUrl, handlers);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		System.out.println("------------------logout filter----------------------");
		super.doFilter(request, response, filterChain);
	}

	@Override
	protected boolean requiresLogout(HttpServletRequest request,
			HttpServletResponse response) {
		return super.requiresLogout(request, response);
	}

}
