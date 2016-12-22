package com.zyt.web.publics.module.config.bean;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @author LiuChuang
 * @description 刘创（Kevin）
 * @version 1.0
 * @date 2015年3月26日
 */
@Alias("Config")
public class SysConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 属性key
	 */
	@NotBlank(message = "属性key不能为空")
	private String configKey;
	
	/**
	 * 父节点key（主键）
	 */
	private String parentConfingKey;
	
	/**
	 * 同层顺序
	 */
	private int levelSeq;
	
	/**
	 * 所在层级
	 */
	private int levelIndex;
	
	/**
	 * 所有上层ID（拼接字符串）
	 */
	private String formatCode;

	/**
	 * 属性类型
	 */
	@NotBlank(message = "属性类型不能为空")
	private String configType;

	/**
	 * 属性值
	 */
	@NotBlank(message = "属性值不能为空")
	private String configValue;

	/**
	 * 属性描述
	 */
	private String configDis;

	public String getConfigDis() {
		return configDis;
	}

	public void setConfigDis(String configDis) {
		this.configDis = configDis;
	}

	public String getConfigKey() {
		return configKey == null ? "" : configKey.trim();
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	public String getConfigType() {
		return configType == null ? "" : configType.trim();
	}

	public void setConfigType(String configType) {
		this.configType = configType;
	}

	public String getConfigValue() {
		return configValue == null ? "" : configValue.trim();
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	public String getParentConfingKey() {
		return parentConfingKey;
	}

	public void setParentConfingKey(String parentConfingKey) {
		this.parentConfingKey = parentConfingKey;
	}

	public String getFormatCode() {
		return formatCode;
	}

	public void setFormatCode(String formatCode) {
		this.formatCode = formatCode;
	}

	public int getLevelSeq() {
		return levelSeq;
	}

	public void setLevelSeq(int levelSeq) {
		this.levelSeq = levelSeq;
	}

	public int getLevelIndex() {
		return levelIndex;
	}

	public void setLevelIndex(int levelIndex) {
		this.levelIndex = levelIndex;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((configDis == null) ? 0 : configDis.hashCode());
		result = prime * result
				+ ((configKey == null) ? 0 : configKey.hashCode());
		result = prime * result
				+ ((configType == null) ? 0 : configType.hashCode());
		result = prime * result
				+ ((configValue == null) ? 0 : configValue.hashCode());
		result = prime * result
				+ ((formatCode == null) ? 0 : formatCode.hashCode());
		result = prime * result + levelIndex;
		result = prime * result + levelSeq;
		result = prime
				* result
				+ ((parentConfingKey == null) ? 0 : parentConfingKey.hashCode());
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
		SysConfig other = (SysConfig) obj;
		if (configDis == null) {
			if (other.configDis != null)
				return false;
		} else if (!configDis.equals(other.configDis))
			return false;
		if (configKey == null) {
			if (other.configKey != null)
				return false;
		} else if (!configKey.equals(other.configKey))
			return false;
		if (configType == null) {
			if (other.configType != null)
				return false;
		} else if (!configType.equals(other.configType))
			return false;
		if (configValue == null) {
			if (other.configValue != null)
				return false;
		} else if (!configValue.equals(other.configValue))
			return false;
		if (formatCode == null) {
			if (other.formatCode != null)
				return false;
		} else if (!formatCode.equals(other.formatCode))
			return false;
		if (levelIndex != other.levelIndex)
			return false;
		if (levelSeq != other.levelSeq)
			return false;
		if (parentConfingKey == null) {
			if (other.parentConfingKey != null)
				return false;
		} else if (!parentConfingKey.equals(other.parentConfingKey))
			return false;
		return true;
	}

}
