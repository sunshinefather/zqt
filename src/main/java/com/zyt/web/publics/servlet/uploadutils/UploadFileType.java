package com.zyt.web.publics.servlet.uploadutils;

import java.util.HashMap;
import java.util.Map;

/**
 * 附件支持类型
 * @author YuJ
 * @date 2015-06-08
 * @since 1.0
 */
public enum UploadFileType {
	// 图片文件
	image {
		@Override
		public Map<String, Object> getFileTypeParams() {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("minSize", 1);
			// 图片文件最大值,2M
			params.put("maxSize", 1024 * 1024 * 10);
			// 文件后缀
			params.put("suffix", "png,jpg,bmp,cgm");
			return params;
		}
	},
	// 视频文件
	video {
		public Map<String, Object> getFileTypeParams() {
			Map<String, Object> params = new HashMap<String, Object>();
			// 文件最小值
			params.put("minSize", 1);
			// 文件最大值,5M
			params.put("maxSize", 5 * 1024 * 1024);
			// 文件后缀
			params.put("suffix", "mp4,rmvb,3gp,avi");
			return params;
		}
	},
	// 音频文件
	audio {
		public Map<String, Object> getFileTypeParams() {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("minSize", 1);
			// 音频文件最大值,5M
			params.put("maxSize", 1024 * 1024 * 5);
			// 文件后缀
			params.put("suffix", "wav,amr,wov");
			return params;
		}
	},
	// flash 文件
	flash,
	// doc 文件
	doc {
		public Map<String, Object> getFileTypeParams() {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("minSize", 1);
			// 文档文件最大值,10M
			params.put("maxSize", 10 * 1024 * 1024);
			// 文件后缀
			params.put("suffix", "doc,docx,xls,xlsx,ppt,pptx");
			return params;
		}
	},
	// 其他文件
	other {
		public Map<String, Object> getFileTypeParams() {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("minSize", 1);
			// 其它文件最大值,10M
			params.put("maxSize", 10 * 1024 * 1024);
			// 文件后缀
			params.put("suffix", "wav,amr,wov");
			return params;
		}
	},
	file {
		public Map<String, Object> getFileTypeParams() {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("minSize", 1);
			// 其它文件最大值,10M
			params.put("maxSize", 10 * 1024 * 1024);
			// 文件后缀
			params.put("suffix", "zip,rar,7z,doc,wav,amr,wov,doc,docx,xls,xlsx,ppt,pptx,txt,mp4,mp3,rmvb,3gp,png,jpg,gif,apk,ipa,mp4,rmvb,3gp,avi,pdf");
			return params;
		}
	};
	
	// 默认值
	public Map<String, Object> getFileTypeParams() {
		Map<String, Object> params = new HashMap<String, Object>();
		// 文件最小值
		params.put("minSize", 1);
		// 文件最大值10M
		params.put("maxSize", 10 * 1024 * 1024);
		// 文件后缀
		params.put("suffix", "txt,mp4,mp3,rmvb,3gp,png,jpg,gif,apk,ipa");
		return params;
	};
}
