package com.zyt.web.after.image.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zyt.web.publics.module.image.bean.Image;

public interface IImageService {

	/**
	 * @描述 TODO 保存图片信息
	 * @author maliang
	 * @time 2014-3-26 下午3:46:00
	 * @version v1.0
	 * @param image
	 * @return
	 */
	public Map<String, Object> insertImage(Image image);
	/**
	 * @描述 TODO 根据ID获取图片
	 * @author maliang
	 * @time 2014-3-27 下午3:42:39
	 * @version v1.0
	 * @param imageId
	 * @return
	 */
	Image getImageById(String imageId);
	
	/**
	 * 
	 *@Description: 根据图片ID获取图片，同时根据宽或者高对图片进行裁剪处理
	 *@param request
	 *@param response
	 *@param id
	 *@param width
	 *@param height
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014年12月1日下午5:51:00
	 */
	public void getImageById(HttpServletRequest request,
			HttpServletResponse response, String id, Integer width, Integer height,Boolean scale);
	
	/**
	 * 
	 *@Description: 根据图片地址获取图片，同时根据宽或者高对图片进行裁剪处理
	 *@param request
	 *@param response
	 *@param path
	 *@param width
	 *@param height
	 *@version: v1.0.0
	 *@author: LiuChuang
	 *@date: 2014年12月3日上午11:36:16
	 */
	public void getImageByPath(HttpServletRequest request,
			HttpServletResponse response, String path, Integer width,
			Integer height,Boolean scale);
	/**
	 * 删除图片数据，同时删除真实的文件
	 * @param imageIds
	 * @return
	 */
	boolean deleteByIds(String imageIds[]);
}
