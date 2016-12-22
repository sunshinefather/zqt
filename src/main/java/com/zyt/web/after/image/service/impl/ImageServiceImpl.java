package com.zyt.web.after.image.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zyt.web.after.image.service.IImageService;
import com.zyt.web.publics.module.image.bean.Image;
import com.zyt.web.publics.module.image.dao.ImageDao;
import com.zyt.web.publics.utils.HibernateValidatorFactoryUtils;
import com.zyt.web.publics.utils.image.ImageUtil;

@Service
public class ImageServiceImpl implements IImageService {

	@Autowired
	private ImageDao imageDao;

	@Transactional
	@Override
	public Map<String, Object> insertImage(Image image) {
		Map<String, Object> valis = HibernateValidatorFactoryUtils
				.verifyObject(image);
		try {
			if (valis == null) {
				valis = new HashMap<String, Object>();
				int resultInt = imageDao.insertImage(image);
				valis.put("result", resultInt > 0 ? true : false);
				valis.put("message", resultInt > 0 ? "保存图片成功" : "保存图片失败");
				// 保存图片成功添加返回图片保存ID
				valis.put("fileId", resultInt > 0 ? image.getImageId() : "");
			}
		} catch (Exception e) {
			throw new RuntimeException("保存图片错误");
		}
		return valis;
	}

	@Override
	public Image getImageById(String imageId) {
		return imageDao.getImageById(imageId);
	}

	@Override
	public void getImageById(HttpServletRequest request,
			HttpServletResponse response, String id, Integer width,
			Integer height, Boolean scale) {
		Image image = this.getImageById(id);
		if (image != null && width != null && width > 0 && height > 0
				&& height != null) {
			this.getImage(request, response, image.getWebAdd(), width, height,
					scale == null ? false : scale);
		}
	}

	@Override
	public void getImageByPath(HttpServletRequest request,
			HttpServletResponse response, String path, Integer width,
			Integer height, Boolean scale) {
		if (width != null && width > 0 && height > 0 && height != null) {
			this.getImage(request, response, path, width, height,
					scale == null ? false : scale);
		}
	}

	/**
	 * 
	 * @Description: 下载图片
	 * @param request
	 * @param response
	 * @param path
	 *            图片地址
	 * @version: v1.0.0
	 * @author: LiuChuang
	 * @date: 2014年12月3日上午10:27:36
	 */
	private void getImage(HttpServletRequest request,
			HttpServletResponse response, String path, int width, int height,
			boolean scale) {
		ServletOutputStream out = null;
		InputStream in = null;
		try {
			String realPath = request.getSession().getServletContext()
					.getRealPath("");

			// 当连接有http://时，截取路径，再次拼装
			path = File.separator + "uploads"
					+ StringUtils.substringAfter(path, "/uploads");

			// 图片实际地址，自动识别path是真实路径还是相对路径
			String storagePath = path.startsWith(realPath) ? path : realPath
					+ (path.startsWith("/") ? "" : path.startsWith("\\") ? ""
							: File.separator) + path;
			File source = new File(storagePath);

			// 图片后缀
			String suffix = StringUtils.substringAfter(source.getName(), ".");
			if (source.exists()) {// 图片存在才进行检查以前是压缩过这种大小的图片，没有则压缩并返回
				boolean flag = false;
				File target = null;
				if (width > 0 && height > 0) {
					target = new File(
							source.getParent()
									+ File.separator
									+ StringUtils.substringBefore(
											source.getName(), ".") + "-"
									+ width + "-" + height + "." + suffix);
					if (target.exists()) {
						flag = true;
					} else {
						flag = ImageUtil.createThumb(source, target, suffix,
								width, height, scale);
					}
				}
				response.setContentType("image/" + suffix + ";charset=utf-8");
				out = response.getOutputStream();
				in = new FileInputStream(flag ? target : source);
				byte[] buffer = new byte[1024 * 4];
				int k = 0;
				while ((k = in.read(buffer, 0, 1024 * 4)) > 0) {
					out.write(buffer, 0, k);
				}
				out.flush();
			}
		} catch (Exception e) {

		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (Exception exce) {

			}
		}
	}

	@Transactional
	@Override
	public boolean deleteByIds(String[] imageIds) {
		if (imageIds != null && imageIds.length > 0) {
			for (String imageId : imageIds) {
				Image image = imageDao.getImageById(imageId);
				if (image != null && StringUtils.isNotBlank(image.getSaveAdd())) {
					File file = new File(image.getSaveAdd());
					if (file.exists())
						file.delete();
				}
			}
			return imageDao.deleteByIds(imageIds) == imageIds.length ? true
					: false;
		}
		return false;
	}

}
