package com.zyt.web.publics.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;

import com.zyt.web.after.config.service.SysLogsService;
import com.zyt.web.publics.annotations.AfterException;
import com.zyt.web.publics.annotations.AfterMethod;
import com.zyt.web.publics.annotations.BeforeMethod;
import com.zyt.web.publics.annotations.LogType;
import com.zyt.web.publics.module.config.bean.SysLog;
import com.zyt.web.publics.module.sysmanager.bean.User;

/**
 * @描述: 用于拦截指定方法
 * @author maliang
 */
@Aspect
public class LoggingAspect {

	@Autowired
	private SysLogsService sysLogsService;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private TaskExecutor taskExecutor;

	/**
	 * @描述:TODO 拦截指定方法之前
	 *          <p>
	 *          目前只针对访问之后跟异常进行日志记录
	 * @auto:maliang
	 * @time:2014-3-12
	 */
	@After(value = "@annotation(com.zyt.web.publics.annotations.AfterMethod)")
	public void afterMethod(JoinPoint joinPoint) {
		Method method = getMethod(joinPoint);
		AfterMethod afterMethod = method.getAnnotation(AfterMethod.class);
		try {
			if (afterMethod != null && request != null) {
				// 该方法不会复用thread
				// SimpleAsyncTaskExecutor asyncTaskExecutor = new
				// SimpleAsyncTaskExecutor();
				final SysLog log = this.getSysLogObect(request, joinPoint,
						afterMethod.value(), afterMethod.type(), "正常");
				taskExecutor.execute(new Runnable() {
					@Override
					public void run() {
						sysLogsService.insertLog(log);
					}
				});
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @描述:TODO 方法之前拦截
	 * @auto:maliang
	 * @time:2014-3-12
	 */
	@Before(value = "@annotation(com.zyt.web.publics.annotations.BeforeMethod)")
	public void beforeMethod(JoinPoint joinPoint) {

		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Method method = getMethod(joinPoint);
		BeforeMethod beforeMethod = method.getAnnotation(BeforeMethod.class);
		if (beforeMethod != null) {
			System.err.println(beforeMethod.value());
		}
	}

	/**
	 * @描述 TODO 访问后异常
	 * @author maliang
	 * @time 2014-3-13 上午11:13:14
	 * @version
	 * @param joinPoint
	 */
	@Async
	@AfterThrowing(value = "@annotation(com.zyt.web.publics.annotations.AfterException)")
	public void afterThrowingMethod(JoinPoint joinPoint) {
		Method method = getMethod(joinPoint);
		AfterException afterException = method
				.getAnnotation(AfterException.class);
		if (afterException != null) {
			System.err.println(afterException.value());
		}
	}

	/**
	 * @描述 TODO 获取当前执行的方法
	 * @author maliang
	 * @time 2014-3-13 上午10:19:12
	 * @version
	 * @return <p>
	 *         如果采用环绕通知则可以不需要使用下面的方式
	 */
	public Method getMethod(JoinPoint joinPoint) {
		String methodLongName = joinPoint.getSignature().toLongString();
		Method[] methods = joinPoint.getTarget().getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			if (method.toString().equals(methodLongName)) {
				return method;
			}
		}
		return null;
	}

	/**
	 * @描述 TODO 获取该方法上面的指定注解
	 * @author maliang
	 * @time 2014-3-13 上午10:30:21
	 * @version
	 * @param t
	 * @return
	 */
	public <T extends Annotation> T getAnnotation(Method method, Class<T> t) {
		if (method == null || t == null) {
			return null;
		} else {
			return method.getAnnotation(t);
		}
	}

	/**
	 * @描述 TODO 日志实体
	 * @author maliang
	 * @time 2014-3-13 下午4:13:45
	 * @version
	 * @param request
	 * @param joinPoint
	 * @param content
	 * @param type
	 * @return
	 */
	public SysLog getSysLogObect(HttpServletRequest request,
			JoinPoint joinPoint, String content, LogType logType, String type) {
		//获取IP
		String ip = getIpAddr(request);
		//获取系统当前登录用户
		User user = (User) User.getInstance();
		//获取用户Id
		String userId = user.getId();
		//获取用户名称				
		String userName = user.getUserName();
		SysLog sl = new SysLog();
		sl.setLogContent(content);
		sl.setLogTime(new Date());
		sl.setLoginIp(ip);
		// 日志类型
		sl.setLogType(logType.toString());
		sl.setUserId(userId);
		sl.setUserName(userName);
		sl.setHandleMethod(joinPoint.getSignature().toLongString());

		return sl;
	}

	/**
	 * @描述 TODO 获取IP
	 * @author maliang
	 * @time 2014-3-13 下午4:08:41
	 * @version
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
