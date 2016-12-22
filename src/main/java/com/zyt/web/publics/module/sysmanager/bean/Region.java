package com.zyt.web.publics.module.sysmanager.bean;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.ibatis.type.Alias;
/**
 * 区域 bean
 * @ClassName:  Region   
 * @Description:   
 * @author: sunshine  
 * @date:   2014年3月12日 上午10:59:28   
 */
@Alias("Region")
public class Region implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	@NotNull(message = "区域父id不能为空")
	private String parentId;

	@NotNull(message = "区域名称不能为空")
	private String regionName;

	private Integer levelSeq;

	private Integer levelIndex;

	private String formatCode;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId == null ? "0" : parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Integer getLevelSeq() {
		return levelSeq;
	}

	public void setLevelSeq(Integer levelSeq) {
		this.levelSeq = levelSeq;
	}

	public Integer getLevelIndex() {
		return levelIndex;
	}

	public void setLevelIndex(Integer levelIndex) {
		this.levelIndex = levelIndex;
	}

	public String getFormatCode() {
		return formatCode;
	}

	public void setFormatCode(String formatCode) {
		this.formatCode = formatCode;
	}

	@Override
	public String toString() {
		return "Region [id=" + id + ", parentId=" + parentId + ", regionName="
				+ regionName + ", levelSeq=" + levelSeq + ", levelIndex="
				+ levelIndex + ", formatCode=" + formatCode + "]";
	}

}
