package com.zyt.web.publics.base.mybatis.pagination;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author LiuChuang
 * @description 分页对象封装，支持分布式缓存
 * @version 1.0
 * @date 2014年8月20日
 */
public class PageModel<E> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 第几页
	 */
    private int pageNo;
    
    /**
     * 每页显示数量
     */
    private int pageSize;
    
    /**
     * 从第几行开始获取数据
     */
    private int currentResult;
    
    /**
     * 总页数
     */
    private int totalPages;
    
    /**
     * 总记录数
     */
    private long totalResults;
    
    /**
	 * 查询参数
	 */
	private Map<String, Object> whereParameters = new HashMap<String, Object>();
    
    /**
     * 返回的数据
     */
    private List<E> list;

	public PageModel(PaginationAble page,List<E> list) {
        if (page != null) {
            this.pageNo = page.getPageNo();
            this.pageSize = page.getPageSize();
            this.currentResult = page.getCurrentResult();
            this.totalPages = page.getTotalPages();
            this.totalResults = page.getTotalResults();
            this.whereParameters = page.getWhereParameters();
            this.list = list;
        }
    }
	
	/**
     * 上一页
     */
    public long getPreviousPageNo() {
        if (pageNo <= 1) {
            return 1;
        }
        return pageNo - 1;
    }

    /**
     * 下一页
     */
    public long getNextPageNo() {
        if (pageNo >= getTotalPages()) {
            return getTotalPages() == 0 ? 1 : getTotalPages();
        }
        return pageNo + 1;
    }

    /**
     * 最后一页
     */
    public long getBottomPageNo() {
        return getTotalPages() == 0 ? 1 : getTotalPages();
    }

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentResult() {
		return currentResult;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public long getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(long totalResults) {
		this.totalResults = totalResults;
	}
	
	public Map<String, Object> getWhereParameters() {
		return whereParameters;
	}

	public void setWhereParameters(Map<String, Object> whereParameters) {
		this.whereParameters = whereParameters;
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "PageModel [pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", currentResult=" + currentResult + ", totalPages="
				+ totalPages + ", totalResults=" + totalResults + "]";
	}
	   
}
