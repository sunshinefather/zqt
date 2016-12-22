package com.zyt.web.publics.utils.fileparser;

import java.util.List;
import java.util.Map;
/**
 * 文件解析定义
 * @ClassName:  FileParser   
 * @Description:   
 * @author: sunshine  
 * @date:   2014年3月31日 上午11:31:18
 */
public interface FileParser
{
 
    /**
     * 解析一行
     * @Title: parserLine   
     * @Description: 
     * @param: @return      
     * @return: Map<String,String>      
     * @throws
     */
	public Map<String,String> parserLine();
	/**
	 * 解析全部
	 * @Title: parserAllLine
	 * @Description: 
	 * @param: @return      
	 * @return: Map<String,String>      
	 * @throws
	 */
	public List<Map<String,String>> parserAllLine();
    /**
     * 是否还有下一行
     * @Title: hasMore   
     * @Description: 
     * @param: @return      
     * @return: boolean      
     * @throws
     */
	public boolean hasMore();
	/**
	 * 是否为空
	 * @Title: isEmpty   
	 * @Description: 
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public boolean isEmpty();
    /**
     * 关闭文档
     * @Title: close   
     * @Description: 
     * @param:       
     * @return: void      
     * @throws
     */
	public void close();
}
