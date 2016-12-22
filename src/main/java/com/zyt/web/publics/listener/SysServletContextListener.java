package com.zyt.web.publics.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zyt.web.after.config.service.SysConfigService;
import com.zyt.web.after.sysmanager.service.IResourceService;
import com.zyt.web.publics.module.config.bean.SysConfig;
import com.zyt.web.publics.module.sysmanager.bean.Resource;
import com.zyt.web.publics.utils.EhcacheUtils;
import com.zyt.web.publics.utils.SystemConstantUtils;

/**
 * @ClassName: PalmLinkServletContextListener
 * @Description: TODO
 * @author: Hadoop
 * @date:2013-12-14 下午12:00:29
 */
@SuppressWarnings({ "unused" })
public class SysServletContextListener implements ServletContextListener {

	@Autowired
	private SysConfigService configService;

	@Autowired
	private IResourceService resourceService;

	private void init(ServletContextEvent servletContextEvent) {
		WebApplicationContextUtils
				.getRequiredWebApplicationContext(
						servletContextEvent.getServletContext())
				.getAutowireCapableBeanFactory().autowireBean(this);
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		System.setProperty("webAppRootKey", servletContext.getRealPath("/"));
		System.setProperty("contextPath", servletContext.getContextPath());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
