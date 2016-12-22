package com.zyt.web.publics.module.attachment.dao;

import com.zyt.web.publics.module.attachment.bean.Attachment;

public interface IAttachmentDao {
	/**
	 * 增加附件
	 * @param attachment
	 * @return
	 */
	int insert(Attachment attachment);

	/**
	 * 根据附件Id获取附件数据
	 * @param attId
	 * @return
	 */
	Attachment getById(String attId);
	
	/**
	 * 删除附件数据
	 * @param attachmentIds
	 * @return
	 */
	int deleteByIds(String ...attachmentIds);

}
