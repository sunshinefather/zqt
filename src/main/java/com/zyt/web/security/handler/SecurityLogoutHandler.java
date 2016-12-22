package com.zyt.web.security.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import com.zyt.web.publics.utils.SystemConstantUtils;

/**
 * 
 * @ClassName: PalmLinkLogoutHandler
 * @Description: TODO
 * @author: Hadoop
 * @date:2013-12-10 下午1:31:17
 */
public class SecurityLogoutHandler implements LogoutHandler {

	@Override
	public void logout(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) {
		request.getSession().removeAttribute(SystemConstantUtils.SystemConstant.SESSIONUSER);
		request.getSession().invalidate();
	}

}
