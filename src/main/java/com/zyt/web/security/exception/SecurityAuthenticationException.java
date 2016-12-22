package com.zyt.web.security.exception;

import org.springframework.security.core.AuthenticationException;

public class SecurityAuthenticationException extends AuthenticationException {

	public SecurityAuthenticationException(String authenticationMsg) {
		super(authenticationMsg);
	}
	public SecurityAuthenticationException(String authenticationMsg,Throwable throwable) {
		super(authenticationMsg,throwable);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1260852758188882696L;

}
