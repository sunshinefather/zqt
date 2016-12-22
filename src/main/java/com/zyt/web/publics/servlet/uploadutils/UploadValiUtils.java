package com.zyt.web.publics.servlet.uploadutils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * @author maliang
 *         <p>
 *         文件上传验证
 */
public class UploadValiUtils {

	private UploadValiUtils() {
	};

	/**
	 * @描述 TODO 上传文件验证
	 * @author maliang
	 * @time 2014-3-21 下午1:49:45
	 * @version v1.0
	 * @param contentType
	 *            文件类型
	 * @param fileName
	 *            文件名称
	 * @param fileSuffix
	 *            文件后缀
	 * @param fileLength
	 *            文件大小
	 * @return
	 */
	public static Map<String, Object> valiFile(String contentType,
			String fileName, long fileLength) {
		// 类型文件限制参数
		Map<String, Object> params = null;
		// 文件类型检查
		if (contentType != null && !contentType.trim().equals("")) {
			for (UploadFileType fileType : UploadFileType.values()) {
				if (fileType.toString().toLowerCase()
						.equals(contentType.toLowerCase())) {
					// 包含该文件类型获取文件类型的限制要求
					params = UploadFileType.valueOf(contentType)
							.getFileTypeParams();
					break;
				}
			}
			// 没有匹配到类型信息
			if (params == null) {
				return getResultMap("1", "系统不支持该类型的文件上传.");
			}
		}
		// 文件名检查
		if (fileName == null || fileName.trim().equals("")) {
			return getResultMap("1", "文件名不能为空.");
		} else {
			String suffix = getSuffix(fileName);
			if (params == null
					|| !params.get("suffix").toString().toLowerCase()
							.contains(suffix.toLowerCase())) {
				return getResultMap("1", "不支持的文件格式上传.");
			}
		}

		long minSize = Long.valueOf(params.get("minSize").toString());
		long maxSize = Long.valueOf(params.get("maxSize").toString());

		// 文件大小检查
		if (fileLength < minSize) {
			return getResultMap("1", "文件不能小于: " + maxSize);
		}
		if (fileLength > maxSize) {
			return getResultMap("1", MessageFormat.format(
					"文件不能大于:{0},当前文件大小:{1}", maxSize, fileLength));
		}
		// 验证通过
		return getResultMap("0", "验证通过");
	}

	/**
	 * @描述 TODO 获取文件后缀
	 * @author maliang
	 * @time 2014-3-21 下午2:42:38
	 * @version v1.0
	 * @param fileName
	 * @return
	 */
	public static String getSuffix(String fileName) {
		return StringUtils.substringAfterLast(fileName, ".");
	}

	/**
	 * @描述 TODO 返回提示信息
	 * @author maliang
	 * @time 2014-3-21 下午2:11:35
	 * @version v1.0
	 * @param code
	 * @param msg
	 * @return
	 */
	public static Map<String, Object> getResultMap(String code, String msg) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 返回错误code
		resultMap.put("error", code);
		// 错误信息
		resultMap.put("message", msg);
		return resultMap;
	}
}
