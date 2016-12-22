package com.zyt.web.publics.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcacheUtils {

	private static CacheManager cacheManager = CacheManager.getInstance();

	private EhcacheUtils() {
	};

	public static CacheManager getCacheManager() {
		if (cacheManager == null) {
			cacheManager = CacheManager.getInstance();
		}
		return cacheManager;
	}

	/**
	 * @描述 添加缓存值
	 * @auto maliang
	 * @time 2014-3-4 上午10:54:49
	 * @param cacheName
	 *            缓存名称
	 * @param pushCacheKey
	 *            缓存key
	 * @param pushCacheValue
	 *            缓存值
	 * @return
	 */
	public static boolean pushValueToCache(String cacheName,
			String pushCacheKey, Object pushCacheValue) {
		try {
			Cache cache = getCacheManager().getCache(cacheName);
			if (cache != null) {
				Element element = new Element(pushCacheKey, pushCacheValue);
				cache.put(element);
			}
			// cacheManager.shutdown();
		} catch (Exception e) {
			throw new RuntimeException("写入缓存错误!");
		}
		return true;
	}

	/**
	 * @描述 获取缓存值
	 * @auto maliang
	 * @time 2014-3-4 上午10:54:27
	 * @param cacheName
	 *            缓存名称
	 * @param cacheKey
	 *            缓存key
	 * @return
	 */
	public static Object pollValueFromCache(String cacheName, String cacheKey) {
		Object object = null;
		try {
			Cache cache = getCacheManager().getCache(cacheName);
			if (cache != null) {
				object = cache.get(cacheKey);
			}
		} catch (Exception e) {
			throw new RuntimeException("获取缓存数据错误!");
		}
		return object;
	}

	/**
	 * @描述 TODO 修改缓存中的值
	 * @author maliang
	 * @time 2014-3-11 上午10:41:59
	 * @version
	 * @param cacheName
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean updateValueFromCache(String cacheName, String key,
			String value) {
		try {
			Cache cache = getCacheManager().getCache(cacheName);
			if (cache != null) {
				Element old = cache.get(key);
				if (old == null || "".equals(old)) {
					cache.put(new Element(key, value));
				} else {
					cache.replace(old, new Element(key, value));
				}
			}
			return true;
		} catch (Exception e) {
			throw new RuntimeException("修改缓存数据错误!");
		}
	}
}
