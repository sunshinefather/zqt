package com.zyt.web.security.filter;

import java.io.IOException;
import java.util.ConcurrentModificationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import com.zyt.web.publics.utils.SystemConstantUtils;
import com.zyt.web.security.exception.SecurityAuthenticationException;


public class SecurityAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private String sessionValidateCode = "validateCode";
	private String validateCodeParameter = "validateCode";
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response) throws AuthenticationException {
		if (!request.getMethod().toUpperCase().equals("POST".toUpperCase())) {  
           throw new AuthenticationServiceException("登陆验证不支持: [" + request.getMethod()+"]方式");  
        }
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		String peer = request.getParameter(SystemConstantUtils.SystemConstant.PEER_KEY);
		request.getSession().setAttribute(SystemConstantUtils.SystemConstant.PEER_KEY, peer);
		if (StringUtils.isEmpty(username)) {
			throw new AuthenticationServiceException("请输入用户名");
		}
		if (StringUtils.isEmpty(password)) {
			throw new AuthenticationServiceException("请输入密码");
		}
		if (SystemConstantUtils.SystemConstant.PEER_SYS.equals(peer)) {//后台登陆
			checkValidateCode(request);
		}
		
		Authentication authentication = null;
		try {
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username,password);
			setDetails(request, authRequest);
			authentication=this.getAuthenticationManager().authenticate(authRequest);
		} catch (UsernameNotFoundException unfe) {
			throw new AuthenticationServiceException("用户不存在");
		} catch (BadCredentialsException bce) {
			throw new AuthenticationServiceException("密码错误");
		} catch (LockedException le) {
			throw new AuthenticationServiceException("用户被锁定");
		} catch (DisabledException de) {
			throw new AuthenticationServiceException("用户被禁用");
		} catch (ConcurrentModificationException cme) {
			throw new AuthenticationServiceException("账户已在其它地方登录");
		} catch (AccountExpiredException aee) {
			throw new AuthenticationServiceException("系统未分配角色");
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		request.getSession().setAttribute(SystemConstantUtils.SystemConstant.SESSIONUSER, authentication.getPrincipal());
		request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
		return authentication;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);
		request.getSession().removeAttribute(SystemConstantUtils.SystemConstant.KAPTCHA_CODE);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object obj = authentication.getPrincipal();
            if (obj instanceof UserDetails) {
                logger.info("系统会话事件: [用户: " + ((UserDetails) obj).getUsername() + " 登录成功]");
            }
        }
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException {
		super.unsuccessfulAuthentication(request, response, failed);
		logger.info("登录失败: [" + failed.getMessage() + "]");
	}

	private void checkValidateCode(HttpServletRequest request) {
		String sessionValidateCode = obtainSessionValidateCode(request.getSession());
		String vailidateCodeParameter = obtainValidateCodeParameter(request);
		if (StringUtils.isEmpty(vailidateCodeParameter) || !sessionValidateCode.equals(vailidateCodeParameter)) {
			throw new SecurityAuthenticationException("验证码错误");
		}
	}
	
	private String obtainSessionValidateCode(HttpSession session){
		System.out.println(session.getId());
		String validateCode =String.valueOf(session.getAttribute(sessionValidateCode));
        validateCode = validateCode == null ? "" : validateCode;
        return validateCode;
	}
	
	private String obtainValidateCodeParameter(HttpServletRequest request){
		String validateCode = request.getParameter(validateCodeParameter);
		validateCode = validateCode == null ? "" : validateCode;
		return validateCode;
	}
	
	public String getSessionValidateCode() {
		return sessionValidateCode;
	}

	public void setSessionValidateCode(String sessionValidateCode) {
		this.sessionValidateCode = sessionValidateCode;
	}

	public String getValidateCodeParameter() {
		return validateCodeParameter;
	}

	public void setValidateCodeParameter(String validateCodeParameter) {
		this.validateCodeParameter = validateCodeParameter;
	}
}