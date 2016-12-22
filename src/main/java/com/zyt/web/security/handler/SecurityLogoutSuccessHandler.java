package com.zyt.web.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.zyt.web.publics.utils.SystemConstantUtils;

/**
 * 
 * @ClassName: PalmLinkLogoutSuccessHandler
 * @Description: TODO
 * @author: Hadoop
 * @date:2013-12-9 下午10:49:09
 */
public class SecurityLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler
		implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		request.removeAttribute(SystemConstantUtils.SystemConstant.SESSIONUSER);
		System.out
				.println("------------------logout success----------------------");
		response.sendRedirect(request.getContextPath());
	}

}
