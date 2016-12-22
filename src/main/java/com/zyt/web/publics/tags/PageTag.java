package com.zyt.web.publics.tags;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.zyt.web.publics.utils.StringUtil;
import com.zyt.web.publics.utils.SystemConstantUtils;

/**
 * @author jsp 分页标签
 */
public class PageTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	// 请求地址
	private String uri;

	// 每页显示条数,跟分页插件保持同步
	private int pageSize = SystemConstantUtils.PAGINATION_PAGE_SIZE;

	// 当前页数
	private int currentPage = 0;

	// 总页数
	private int totalPage = 0;

	private String requestUrl = "";

	private String pageWord = "pageNo";

	private void init() {
		reset();
		processUri();
	}

	/**
	 * @描述 TODO 参数拼接
	 * @auto maliang
	 * @time 2014-3-7 上午9:13:57
	 */
	private void processUri() {

		HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();
		Map<String, String[]> params = this.pageContext.getRequest()
				.getParameterMap();

		if (params != null && params.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (String paramKey : params.keySet()) {
				String param = request.getParameter(paramKey);
				if (!paramKey.equals(this.pageWord) && paramKey != null) {
					if(request.getMethod().equalsIgnoreCase("get")){
	                    try {
	                    	param = StringUtil.toUTF8(param);
						} catch (Exception e) {
							param =  request.getParameter(paramKey);
						}
					}
					sb.append(paramKey + "=" + param + "&");
				}
			}
			// 加上参数
			this.requestUrl += sb.toString();
			// 截取最后一个连接符
			if (this.requestUrl.endsWith("&")) {
				this.requestUrl = this.requestUrl.substring(0,
						this.requestUrl.length() - 1);
			}
		}
		// 添加页码标记
		this.requestUrl += this.requestUrl.indexOf("=") >= 0 ? "&"
				+ this.pageWord + "=" : this.pageWord + "=";
	}

	@Override
	public int doEndTag() throws JspException {
		init();
		JspWriter out = this.pageContext.getOut();
		try {
			out.write(createHtmlDom());
		} catch (Exception e) {
			e.printStackTrace();
			throw new JspException("分页标签渲染错误");
		}
		return super.doEndTag();
	}

	/**
	 * @描述 TODO 分页标签字符串生成
	 * @auto maliang
	 * @time 2014-3-5 下午5:49:36
	 * @return
	 */
	private String createHtmlDom() {
		// this.pageContext.getServletContext();
		StringBuilder sb = new StringBuilder("<div class='pages'>");
		if (this.totalPage > 0) {
			sb.append(getPrePageDomString());
			sb.append(getPageContentDomString());
			sb.append(getNextPageDomString());
			// sb.append(getCurrentAndTotalPageDomString());
		} else {
			sb.append("没有相关信息!");
		}
		sb.append("</div>");
		return sb.toString();
	}

	/**
	 * @描述:TODO 下一页标签
	 *          <p>
	 *          class:hasNext,noNext
	 * @auto:maliang
	 * @time:2014-3-5
	 * @return
	 */
	private String getNextPageDomString() {
		StringBuilder sb = new StringBuilder();
		if (this.currentPage < this.totalPage) {
			sb.append("<a class='NextPage' href='" + this.requestUrl
					+ (this.currentPage + 1) + "'>下一页</a>");
		} else {
			sb.append("<a class='noNext'>下一页</a>");
		}
		return sb.toString();
	}

	/**
	 * @描述 TODO 获取显示页码字符串
	 *     <p>
	 *     格式显示: 1...3,4,5,6,7...8
	 * @auto maliang
	 * @time 2014-3-5 下午6:08:01
	 * @return
	 */
	private String getPageContentDomString() {
		StringBuilder sb = new StringBuilder();

		// 前置
		if (this.currentPage - 2 > 1) {
			sb.append("<a href='" + this.requestUrl + 1
					+ "' class='PageLink'>1</a>");
			if (this.currentPage - 3 > 1) {
				sb.append("<a class='PageMore'>...</a>");
			}
		}

		int startPage = this.currentPage - 2 <= 0 ? 1 : this.currentPage - 2;
		int endPage = this.currentPage + 2 > this.totalPage ? this.totalPage
				: this.currentPage + 2;

		for (int i = startPage; i <= endPage; i++) {
			if (this.currentPage == i) {
				sb.append("<a href='" + this.requestUrl + i
						+ "' class='PageSel'>" + i + "</a>");
			} else {
				sb.append("<a href='" + this.requestUrl + i
						+ "' class='PageLink'>" + i + "</a>");
			}
		}
		// 后置
		if (this.currentPage + 2 < this.totalPage) {
			if (this.currentPage + 3 < this.totalPage) {
				sb.append("<a class='PageMore'>...</a>");
			}
			sb.append("<a href='" + this.requestUrl + this.totalPage
					+ "'  class='PageLink' >" + this.totalPage + "</a>");
		}
		return sb.toString();
	}

	/**
	 * @描述 TODO 上一页标签
	 *     <p>
	 *     class:hasPre,noPre
	 * @auto maliang
	 * @time 2014-3-5 下午5:55:00
	 * @return
	 */
	private String getPrePageDomString() {
		StringBuilder sb = new StringBuilder(100);
		// 当前页码在第二页才会有上一页
		if (this.currentPage > 1) {
			sb.append(
					"<a class='PrevPage' href='" + this.requestUrl
							+ (this.currentPage - 1) + "'>").append("上一页</a>");
		} else {
			sb.append("<a class='noPre'>上一页</a>");
		}
		return sb.toString();
	}

	private void reset() {
		this.requestUrl = this.uri.indexOf("=") >= 0 ? this.uri + "&"
				: this.uri + "?";
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getPageWord() {
		return pageWord;
	}

	public void setPageWord(String pageWord) {
		this.pageWord = pageWord;
	}

}
