package com.zyt.web.security.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import com.zyt.web.publics.module.sysmanager.bean.User;
import com.zyt.web.publics.utils.SystemConstantUtils;

/**
 * 
 * @author LiuChuang
 * @description 登录成功处理器
 * @version 1.0
 * @date 2015年2月12日
 */
public class LoginAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		User user = (User)authentication.getPrincipal();
		request.getSession().setAttribute(SystemConstantUtils.SystemConstant.SESSIONUSER, user);
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
 
    
}
