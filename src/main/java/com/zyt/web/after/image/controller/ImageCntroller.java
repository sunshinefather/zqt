package com.zyt.web.after.image.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zyt.web.after.image.service.IImageService;
import com.zyt.web.publics.base.BaseController;

/**
 * 
 * @author LiuChuang
 * @description 图片控制器
 * @version 1.0
 * @date 2014年12月1日
 */
@Controller
@RequestMapping(value = "/image")
public class ImageCntroller extends BaseController {
	
	@Autowired
	private IImageService iImageService;
	
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
	 *@date: 2014年12月1日下午5:30:25
	 */
	@RequestMapping(value="public/getImageById/{scale}/{width}/{height}/{id}",method=RequestMethod.GET)
	public void getImageById(HttpServletRequest request,
			HttpServletResponse response, @PathVariable String id,
			@PathVariable Integer width, @PathVariable Integer height, @PathVariable Boolean scale) {
		
		iImageService.getImageById(request,response,id,width,height,scale);
		
	}
	
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
	 *@date: 2014年12月1日下午5:29:35
	 */
	@RequestMapping(value="public/getImageByPath/{scale}/{width}/{height}",method=RequestMethod.GET)
	public void getImageByPath(HttpServletRequest request,
			HttpServletResponse response, @RequestParam(required=true) String path,
			@PathVariable Integer width, @PathVariable Integer height, @PathVariable Boolean scale) {
		
		iImageService.getImageByPath(request,response,path,width,height,scale);
		
	}
	
}
