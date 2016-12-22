package com.zyt.web.publics.utils;

/**
 * 系统常量类
 * 
 * @author maliang
 */
public class SystemConstantUtils {

	/**
	 * 验证码code
	 */
	public static final String CODE = "code";
	
	/**
	 * 用户缓存key
	 */
	public static final String USERCACHE = "userCache";

	/**
	 * 初始页码key
	 */
	public static final int PAGINATION_PAGE_SIZE = 15;

	/**
	 * 系统缓存名称
	 */
	public static final String CACHENAEM = "sysCache";

	/**
	 * 系统资源key
	 */
	public static String RESOURCECACHECODE = "resourceCacheCode";

	/**
	 * 系统配置信息缓存key
	 */
	public static String CONFIGCAHECODE = "configCacheCode";
	/**
	 * 系统标示
	 */
	public static String SIGN = "FrameWork";

	/**
	 * 系统基础参数常量
	 * 
	 * @author Hadoop
	 * 
	 */
	public static class SystemConstant {
		/**
		 * 内存中的Key
		 */
		public static String SESSIONUSER = "SESSIONUSER";
		
		/**
		 * 内存中配置消息服务器所需项目名的Key
		 */
		public static String PROJECT = "PROJECT";

		/**
		 * session中存储的校验码key
		 */
		public static String KAPTCHA_CODE = "KAPCHA_CODE";

		/**
		 * 登录端标记 Key
		 * 
		 */
		public static String PEER_KEY = "PEER";

		/**
		 * WEBSITE 端标记
		 * 
		 */
		public static String PEER_WEBSITE = "1";

		/**
		 * SYS 端标记
		 * 
		 */
		public static String PEER_SYS = "0";
		
		/**
		 * jsessionid标记
		 */
		public static String JSESSIONID = "JSESSIONID";
	}

}
