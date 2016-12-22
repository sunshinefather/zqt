package com.zyt.web.publics.base.mybatis.pagination;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.zyt.web.publics.utils.SystemConstantUtils;

/**
 * 
 * @ClassName: PaginationAble
 * @Description: TODO
 * @author: sunshine
 * @date:2013-11-1 下午8:39:05
 * 修改并发问题 by sushine 2014-12-26
 */
public class PaginationAble implements Serializable {

	private static final long serialVersionUID = 5390242392714690503L;
	/**
	 * 每一页的显示条数
	 */
	private int pageSize;
	/**
	 * 总的页数
	 */

	private int totalPages;
	/**
	 * 查询的数据总条数
	 */
	private int totalResults;
	/**
	 * 当前页
	 */
	private int pageNo;
	/**
	 * 查询参数
	 */
	private Map<String, Object> whereParameters = new HashMap<String, Object>();
	
	private List<?> results = new LinkedList<Object>();
	
    @JsonIgnore
	private String paramsFormat = "";

	public PaginationAble() {
		this(1, SystemConstantUtils.PAGINATION_PAGE_SIZE);
	}
	
	public PaginationAble(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		if (pageSize > 0) {
			this.pageSize = pageSize;
		}
		if (this.pageNo < 1) {
			this.pageNo = 1;
		}
		
	}

	public int getTotalPages() {
		if (this.totalResults > 0) {
			this.totalPages = this.totalResults % this.pageSize == 0 ? this.totalResults
					/ this.pageSize
					: this.totalResults / this.pageSize + 1;
		}
		// 分页算法，计算总页数
		return this.totalPages;
	}

	public int getCurrentResult() {
		// 计算从第几条获取数据
		return (pageNo - 1) * pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public int getPageNo() {
		pageNo=(pageNo <= 0 ? 1 : pageNo > this.getTotalPages() ? this
				.getTotalPages() : pageNo);
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		
		this.pageNo = (pageNo <= 0 ? 1 : pageNo);
	}

	public Map<String, Object> getWhereParameters() {
		return whereParameters;
	}
	@JsonIgnore
	public void setWhereParameters(Map<String, Object> whereParameters) {
		if (whereParameters != null) {
			this.whereParameters = whereParameters;
			StringBuffer sbf = new StringBuffer();
			for (Entry<String, Object> entry : whereParameters.entrySet()) {
				sbf.append(entry.getKey()).append("=").append(entry.getValue())
						.append("&");
			}
			this.paramsFormat = sbf.toString();
			if (paramsFormat.endsWith("&")) {
				paramsFormat = paramsFormat.substring(0,paramsFormat.length() - 1);
			}
		} else {
			this.whereParameters = new HashMap<String, Object>();
			paramsFormat = "";
		}
	}

	public static PaginationAble getPaginationAble() {
		PaginationAble page=new PaginationAble();
		return page;
	}

	public String getParamsFormat() {
		return paramsFormat;
	}

	public List<?> getResults() {
		return results;
	}

	public void setResults(List<?> result) {
		this.results = result;
	}

	@Override
	public String toString() {
		return "PaginationAble [pageSize=" + pageSize + ", totalPages="
				+ totalPages + ", totalResults=" + totalResults + ", pageNo="
				+ pageNo + ", whereParameters=" + whereParameters
				+ ", results=" + results + ", paramsFormat=" + paramsFormat
				+ "]";
	}
}