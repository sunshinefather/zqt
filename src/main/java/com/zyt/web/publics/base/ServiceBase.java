package com.zyt.web.publics.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

/**
 * @author maliang
 */
public class ServiceBase {

	public Map<String, Object> results = null;

	{
		results = new HashMap<String, Object>();
	}

	public synchronized Map<String, Object> setResultMap(int saveResultInt) {
		boolean b = saveResultInt > 0;
		results.put("result", b ? true : false);
		results.put("message", b ? "操作成功" : "操作失败");
		return results;
	}

	public synchronized Map<String, Object> setResultMap(boolean b) {
		results.put("result", b);
		results.put("message", b ? "操作成功" : "操作失败");
		return results;
	}

	public String[] removeBlackStr(String[] oldStrs) {
		int i = 0;
		while (ArrayUtils.contains(oldStrs, "")) {
			oldStrs = (String[]) ArrayUtils.remove(oldStrs, i);
			i++;
		}
		return oldStrs;
	}

}
