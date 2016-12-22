package com.zyt.web.publics.utils;

import java.util.UUID;


/**
 * 
 * @author Hadoop
 *
 */
public class UUIDUtils {
	   public static synchronized String getUUID() {
		   return   String.valueOf(UUID.randomUUID()).replaceAll("-","");
		  }
}
