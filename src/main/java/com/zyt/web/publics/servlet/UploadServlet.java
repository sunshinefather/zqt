package com.zyt.web.publics.servlet;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.alibaba.fastjson.JSON;
import com.ibm.icu.text.SimpleDateFormat;
import com.zyt.web.after.config.service.SysConfigService;
import com.zyt.web.after.image.service.IImageService;
import com.zyt.web.publics.module.config.bean.SysConfig;
import com.zyt.web.publics.module.image.bean.Image;
import com.zyt.web.publics.servlet.uploadutils.UploadValiUtils;
import com.zyt.web.publics.utils.SystemUtils;

public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SysConfigService configService;

	@Autowired
	private IImageService imageService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	/**
	 * 文件上传servlet
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		SysConfig config = configService.getConfigById("uploadAdd");
		SysConfig addr = configService.getConfigById("imageAddr");
		// 文件上传保存路径
		String path = config == null ? "" : config.getConfigValue();
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();

		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 文件临时存放点
		String fileUploadDir = req.getSession().getServletContext()
				.getRealPath("")
				+ "/" + path;

		File file = this.getFileDir(fileUploadDir);
		factory.setRepository(file);
		factory.setSizeThreshold(1);

		// 文件上传
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		// 上传文件的列表
		List<?> fileItems = null;
		try {
			fileItems = fileUpload.parseRequest(req);
			Map<String, String> attrs = new HashMap<String, String>();
			// 获取表单属性--后续添加
			if (fileItems != null && fileItems.size() > 0) {
				for (Object obj : fileItems) {
					FileItem fileItem = (FileItem) obj;
					if (fileItem.getFieldName().equals("localid")) {
						attrs.put("localid", fileItem.getString());
					} else if (fileItem.getFieldName().equals("dir")) {
						attrs.put("dir", fileItem.getString());
					} else if (fileItem.getFieldName().equals("fileType")) {
						attrs.put("fileType", fileItem.getString());
					} else if (fileItem.getFieldName().equals("timenum")) {
						attrs.put("timenum", fileItem.getString());
					}
				}

				for (Object obj : fileItems) {
					FileItem fileItem = (FileItem) obj;
					// 非空获取
					String contextType = fileItem.getContentType();

					if (contextType != null) {
						// 获取上传文件名
						String fileName = fileItem.getName();
						// 富文本文件上传类型
						String fileContentType = req.getParameter("dir");
						if (fileContentType == null && attrs.containsKey("dir")) {
							fileContentType = attrs.get("dir");
						}else{
							fileContentType="file";
						}
						// 文件长度
						long fileLength = fileItem.getSize();
						// 检验上传文件
						Map<String, Object> results = UploadValiUtils.valiFile(
								fileContentType, fileName, fileLength);
						// 验证通过
						if (results != null && results.get("error").equals("0")) {
							String toDay = getToday();
							// 保存文件名
							String saveFileName = SystemUtils.getUUIDCode();
							// 保存路径
							final String savePath = fileUploadDir + "/" + toDay
									+ "/" + saveFileName + "."
									+ UploadValiUtils.getSuffix(fileName);
							// 访问路径
							String callPath = addr.getConfigValue() + req.getContextPath() + "/"
									+ path + "/" + toDay + "/" + saveFileName
									+ "." + UploadValiUtils.getSuffix(fileName);
							File newFile = getFile(savePath);
							try {
								fileItem.write(newFile);
								String miniCallPath = "";
								if (fileContentType.equals("image")) {
									com.aviyehuda.easyimage.Image easyimage = new com.aviyehuda.easyimage.Image(
											newFile);
									float oldWidth = (float) easyimage
											.getWidth();
									float oldHeight = (float) easyimage
											.getHeight();
									float bl = oldWidth / oldHeight;
									if (oldWidth > 200 && oldHeight <= 200) {
										easyimage.resize(100,
												(int) Math.floor(100 / bl));
									} else if (oldHeight > 200
											&& oldWidth <= 200) {
										easyimage
												.resize((int) Math
														.floor(100 / bl), 100);
									} else if (oldWidth > 200
											&& oldHeight > 200) {
										float wbl = oldWidth / 100;
										easyimage.resize((int) Math
												.floor(oldWidth / wbl),
												(int) Math.floor(oldHeight
														/ wbl));
									} else {
										easyimage.resize(80);
									}
									// 压缩过后的地址
									String miniPath = fileUploadDir
											+ "/"
											+ toDay
											+ "/"
											+ saveFileName
											+ "_mini."
											+ UploadValiUtils
													.getSuffix(fileName);
									// 访问路径
									miniCallPath = addr.getConfigValue()
											+ req.getContextPath()
											+ "/"
											+ path
											+ "/"
											+ toDay
											+ "/"
											+ saveFileName
											+ "_mini."
											+ UploadValiUtils
													.getSuffix(fileName);
									easyimage.saveAs(miniPath);
								}
								// 保存地址
								// 文件写入成功
								Map<String, Object> map = new HashMap<String, Object>();
								map.put("error", 0);
								map.put("url", callPath);
								if (fileContentType.equals("image")) {
									map.put("miniUrl", miniCallPath);
								}
								if (attrs.containsKey("localid")) {
									map.put("localid", attrs.get("localid"));
									map.put("url", callPath);
									map.put("miniUrl", miniCallPath);
								}
								if (attrs.containsKey("fileType")) {
									map.put("fileType", attrs.get("fileType"));
								}
								if (attrs.containsKey("timenum")) {
									map.put("timenum", attrs.get("timenum"));
								}
								String imageId = this.getImageSaveId(
										UploadValiUtils.getSuffix(fileName),
										savePath, callPath, fileName);
								// 图片保存数据库ID
								map.put("imageId", imageId);
								map.put("originalName", fileName);
								out.print(JSON.toJSONString(map).toString());
								out.close();
								// 手动清理临时文件
								new Thread(new Runnable() {
									@Override
									public void run() {
										File tmpFile = new File(StringUtils
												.substringBeforeLast(savePath,
														"."));
										if (tmpFile.isDirectory()) {
											tmpFile.listFiles(new FilenameFilter() {
												@Override
												public boolean accept(File dir,
														String name) {
													if (name.endsWith(".tmp")) {
														dir.delete();
														return true;
													}
													return false;
												}
											});
										}
									}
								}).start();
							} catch (Exception e) {
								System.err.println(e);
								resp.setStatus(500);
								out.print(JSON.toJSONString(
										UploadValiUtils.getResultMap("1",
												"写入文件失败." + e.getMessage()))
										.toString());
								out.close();
							}
						} else {
							fileItem.delete();
						}
					} else {
						fileItem.delete();
					}
				}
			}

		} catch (FileUploadException e) {
			resp.setStatus(500);
			out.print(JSON.toJSONString(UploadValiUtils.getResultMap("1",
					"获取文件上传列表错误." + e.getMessage())));
		} finally {
			out.flush();
			out.close();
		}
	}

	/**
	 * @描述 TODO 保存文件到数据库
	 * @author maliang
	 * @time 2014-3-26 下午3:48:29
	 * @version v1.0
	 * @return
	 */
	private String getImageSaveId(String fileSuffix, String SavePath,
			String callPath, String fileName) {
		Image image = new Image();
		image.setImageId(SystemUtils.getUUIDCode());
		image.setFileSuffix(fileSuffix);
		image.setSaveAdd(SavePath);
		image.setWebAdd(callPath);
		image.setFileName(fileName);
		Map<String, Object> valis = imageService.insertImage(image);
		if (valis != null && valis.containsValue(true)) {
			return valis.get("fileId").toString();
		}
		// 保存没有成功
		return "";
	}

	// 创建文件夹
	public File getFileDir(String path) {
		File file = new File(path + "/" + getToday() + "/");
		// 上传文件目录不存在则创建目录
		if (!file.exists()) {
			file.mkdirs();
		}
		// 设置可写写入权限
		if (!file.canWrite()) {
			file.setWritable(true);
		}
		return file;
	}

	// 创建文件夹
	public File getFile(String path) {
		File file = new File(path);
		return file;
	}

	public String getToday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(new Date());
	}

}
