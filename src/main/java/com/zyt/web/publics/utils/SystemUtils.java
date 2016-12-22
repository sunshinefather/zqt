package com.zyt.web.publics.utils;

import java.util.UUID;

/**
 * @author 系统工具
 */
public class SystemUtils {
	private SystemUtils() {
	};

	/**
	 * @描述 UUID
	 * @auto maliang
	 * @time 2014-3-4 下午5:38:15
	 * @return
	 */
	public static String getUUIDCode() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
