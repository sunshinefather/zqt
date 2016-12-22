package com.zyt.web.security.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class HttpSessionEventPublisher implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		//TODO 创建Session
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		//TODO 销毁Session
		System.out.println("---------------Time out--------------");
	}

}
