package com.zyt.web.publics.utils.conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author LiuChuang
 * @description 加载src/main/resources目录下面的properties资源文件，同时只会加载一次，注意目前不支持子文件夹。
 * @version 1.0
 * @date 2014-7-11
 */
public class Configure {
	
	private static final Logger logger = LoggerFactory.getLogger(Configure.class);
	
	private static Hashtable<String, Properties> env = new Hashtable<String, Properties>();
	
	private static class Hold{
		private final static Configure INSTANCE = new Configure(); 
	}
	
	public static Configure getInstance(){
		return Hold.INSTANCE;
	}
	
	/**
	 * 
	 *@Description: 获取资源文件中的某个key对应的值
	 *@param pro 资源文件名，不包含后缀properties
	 *@param key 资源文件键值
	 *@return Object 键对应的值
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014-7-14上午9:37:51
	 */
	public Object getValue(String pro,String key){
		if (env.get(pro) == null) {
		      InputStream is = null;
		      try {
			        is = this.getClass().getResourceAsStream("/" + pro + ".properties");
			        Properties p = new Properties();
			        p.load(is);
			        is.close();
			        env.put(pro, p);
		      }catch (IOException localIOException) {
		    	  logger.error(localIOException.getMessage());
		      }
	    }
	    return env.get(pro).getProperty(key);
	}
	
	/**
	 * 私有化构造方法，创建对象不对外
	 */
   private Configure(){
		
	}
   
   public static void main(String[] args) {
	   System.out.println(Configure.getInstance().getValue("site", "site.cookie.path"));
   }
   
}
