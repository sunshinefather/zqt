package com.zyt.web.publics.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionListener implements HttpSessionListener {

	private static final Logger log = LoggerFactory
			.getLogger(SessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		log.info("session 会话创建成功:  " + se.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		log.info("session 会话销毁成功:  " + se.getSession().getId());
	}

}
