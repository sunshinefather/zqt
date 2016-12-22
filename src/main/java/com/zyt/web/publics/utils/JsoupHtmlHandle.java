package com.zyt.web.publics.utils;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

/**
 * @author maliang
 *         <p>
 *         上传富文本处理
 */
public class JsoupHtmlHandle {

	private JsoupHtmlHandle() {
	};

	private static Whitelist whitelist = null;

	static {
		// add whitelist attribute example
		// add whitelist tag for example Whitelist.addTag("em");
		whitelist = Whitelist.relaxed().addAttributes("h1", "style");
	}

	public static String cleanHtml(String bodyHtml) {
		return Jsoup.clean(bodyHtml, whitelist);
	}
	/**
	 * 给文本中的符合正则表达的元素添加属性
	 * @Title: addAttr
	 * @Description: TODO  
	 * @param: @param content
	 * @param: @param regex
	 * @param: @param attrName
	 * @param: @param attrValue
	 * @param: @return      
	 * @return: String
	 * @author: sunshine  
	 * @throws
	 */
	public static String addAttr(String content,String regex,String attrName,String attrValue) {
		  if(StringUtils.isNotBlank(content)){
			  Document doc = Jsoup.parse(content);
		      Elements eles = doc.select(regex);
		      for (Element element : eles) {
		          element.attr(attrName,attrValue);
		      }
		      return doc.toString();
		  }else{
			  return content; 
		  }
	}
	/*
	 * public static void main(String[] args) { String html =
	 * "<h1 style='color:red'>3333</h1><img src='http://123'/>";
	 * System.out.println(JsoupHtmlHandle.cleanHtml(html)); ; }
	 */

}
