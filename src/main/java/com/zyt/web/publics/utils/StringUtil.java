package com.zyt.web.publics.utils;

import httl.util.StringUtils;

public class StringUtil {
	
	/**
	 * 
	 *@Description: 转换编码
	 *@param str
	 *@return
	 *@throws Exception
	 *@version: v1.0.0
	 *@author: Kevin
	 *@date: 2015年9月18日下午4:01:39
	 */
	public  static String toUTF8(String str) throws Exception {
		if (StringUtils.isEmpty(str)) {
			return "";
		}
		String retStr = str;
		byte b[];
		b = str.getBytes("ISO8859_1");

		for (int i = 0; i < b.length; i++) {
			byte b1 = b[i];
			if (b1 == 63)
				break;
			else if (b1 > 0)
				continue;
			else if (b1 < 0) {
				retStr = new String(b, "utf8");
				break;
			}
		}
		return retStr;
	}
}
