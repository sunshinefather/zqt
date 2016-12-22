package com.zyt.web.publics.module.config.bean;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Alias("SysLog")
public class SysLog implements Serializable {
	private static final long serialVersionUID = 1L;
	// 系统日志ID 采用数据库自动生成
	private int id;
	// 操作用的ID
	@NotBlank(message = "操作用户ID不能为空")
	private String userId;
	// 操作用户的名字
	@NotBlank(message = "操作用户名不能为空")
	private String userName;
	// 日志类容
	@NotBlank(message = "日志类容不能为空")
	private String logContent;
	// 用户登录IP
	@NotBlank(message = "用户登录IP不能为空")
	private String loginIp;
	// 日志时间
	@NotNull(message = "日志记录时间不能为空")
	@DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss")
	private Date logTime;
	// 日志类型
	@NotBlank(message = "日志类型不能为空")
	private String logType;
	// 操作的方法
	@NotBlank(message = "操作方法不能为空")
	private String handleMethod;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getHandleMethod() {
		return handleMethod;
	}

	public void setHandleMethod(String handleMethod) {
		this.handleMethod = handleMethod;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SysLog other = (SysLog) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
