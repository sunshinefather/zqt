package com.zyt.web.after.config.service;

import java.util.List;
import java.util.Map;

import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.module.config.bean.SysConfig;

/**
 * @author maliang
 */
public interface SysConfigService {

	/**
	 * @描述 TODO 获取所有的系统配置资源
	 * @author maliang
	 * @time 2014-3-11 上午10:08:03
	 * @version
	 * @return
	 */
	public List<SysConfig> getConfigs();

	/**
	 * @描述 TODO 分页获取配置信息
	 * @author maliang
	 * @time 2014-3-18 上午10:05:20
	 * @version v1.0
	 * @return
	 */
	public List<SysConfig> getConfigsPage(PaginationAble page,
			Map<String, Object> params);

	/**
	 * @描述:TODO 根据Id获取系统资源属性
	 * @auto:maliang
	 * @time:2014-3-11
	 * @param configId
	 * @return
	 */
	public SysConfig getConfigById(String configId);

	/**
	 * @描述:TODO 根据configID 修改系统资源属性
	 * @auto:maliang
	 * @time:2014-3-11
	 * @param configId
	 * @return
	 */
	public boolean updateConfigById(SysConfig config);

	/**
	 * @描述:TODO 根据configID删除属性
	 * @auto:maliang
	 * @time:2014-3-11
	 * @param configId
	 * @return
	 */
	@Deprecated
	public boolean deleteConfigById(String configId);

	/**
	 * @描述 TODO 批量删除系统属性信息
	 * @author maliang
	 * @time 2014-3-18 上午11:42:11
	 * @version v1.0
	 * @param configIds
	 * @return
	 */
	public Map<String, Object> deleteConfigsByIds(String[] configIds);

	/**
	 * @描述 TODO 添加系统配置属性
	 * @author maliang
	 * @time 2014-3-12 下午5:17:12
	 * @version
	 * @param config
	 * @return
	 */
	public Map<String, Object> insertConfig(SysConfig config);
	/**
	 * @description 根据配置的key 和对应的Json中的key 来获取Json 中key 对应的Value
	 * @author YuJ
	 * @param configkey 系统配置key
	 * @param key Json 中的key
	 * @date 2014-08-07 16:49
	 * @return value
	 */
	public String getValue(String configkey, String key);

}
