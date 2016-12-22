package com.zyt.web.publics.module.image.dao;

import com.zyt.web.publics.module.image.bean.Image;

public interface ImageDao {
	/**
	 * @描述 TODO 保存图片
	 * @author maliang
	 * @time 2014-3-26 下午3:39:26
	 * @version v1.0
	 * @param iamge
	 * @return
	 */
	int insertImage(Image image);

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
	 * 删除图片数据和图片文件
	 * @param imageIds
	 * @return
	 */
	int deleteByIds(String imageIds[]);

}
