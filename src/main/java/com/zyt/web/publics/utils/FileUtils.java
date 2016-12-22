package com.zyt.web.publics.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;
/**
 * 文件操作工具类
 * @ClassName:  FileUtils   
 * @Description:   
 * @author: sunshine  
 * @date:   2014年3月31日 下午5:18:51
 */
public class FileUtils
{
	private static final String DOWNLOADDIR = "template/";

    /**
     * 检查上传路径的文件是否存在，不存在则直接创建
     * @Title: checkAndCreateFile   
     * @Description: 
     * @param: @param path      
     * @return: void      
     * @throws
     */
	public static void checkAndCreateFile(String path)
	{
		checkAndCreateFile(new File(path));
	}

    /**
     * 检查上传路径的文件是否存在，不存在则直接创建
     * @Title: checkAndCreateFile   
     * @Description: 
     * @param: @param file      
     * @return: void      
     * @throws
     */
	public static void checkAndCreateFile(File file)
	{
		if (!file.exists())
		{
			file.mkdirs();
		}
	}
    /**
     * 保存文件
     * @Title: SaveFileFromInputStream   
     * @Description: 
     * @param: @param stream
     * @param: @param path
     * @param: @param filename
     * @param: @throws IOException      
     * @return: void      
     * @throws
     */
	public static void SaveFileFromInputStream(InputStream stream, String path, String filename) throws IOException
	{
		checkAndCreateFile(path);
		File ff = new File(path + filename);
		if (!ff.exists())
		{
			ff.createNewFile();
		}
		FileOutputStream fs = new FileOutputStream(path + filename);
		byte[] buffer = new byte[1024 * 1024];
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1)
		{
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		stream.close();
	}

    /**
     * 下载文件
     * @Title: download   
     * @Description: 
     * @param: @param request
     * @param: @param response
     * @param: @param storeName 服务器上对应的文件名
     * @param: @param contentType 下载内容MIME
     * @param: @param realName   下载文件名   
     * @return: void      
     * @throws
     */
	public static void download(HttpServletRequest request, HttpServletResponse response, String storeName,
			String contentType, String realName)
	{

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try
		{
			String ctxPath = request.getSession().getServletContext().getRealPath("/") + FileUtils.DOWNLOADDIR;
			String downLoadPath = ctxPath + storeName;
			long fileLength = new File(downLoadPath).length();
			
			request.setCharacterEncoding("UTF-8");
			response.setContentType(contentType);
			
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(realName.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));

			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length)))
			{
				bos.write(buff, 0, bytesRead);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				bis.close();
				bos.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	/**
	 * 校验是否为图片类型
	 * @Description: 
	 * @Title: isImage 
	 * @author: sunshine  
	 * @param: @param formFile
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public static boolean isImage(MultipartFile formFile) {  
        List<String> allowType = Arrays.asList("image/bmp","image/png","image/gif","image/jpg","image/jpeg","image/pjpeg","image/ico","image/icon");  
        return allowType.contains(formFile.getContentType());
      
    } 
	
	private static final FieldPosition HELPER_POSITION = new FieldPosition(0);
    private static final Format dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static final NumberFormat numberFormat = new DecimalFormat("000");
    private static int seq = 0;
    private static final int MAX = 999;
    
    /**
     * 
     *@Description: 创建唯一文件名
     *@param prefix 前缀
     *@param suffix 文件后缀
     *@return String 文件名
     *@version: v1.0.0
     *@author: LiuChuang
     *@date: 2014-2-25上午11:42:46
     * 
     * Modification History:
     *     Date		      Author		   Version		Description
     * ---------------------------------------------------------*
     * 2014-2-25	     LiuChuang		v1.0.0		   新建
     */
	public static synchronized String createUniqueName(String prefix,String suffix){
		  Calendar rightNow = Calendar.getInstance();
	      StringBuffer sb = new StringBuffer();
	      if(StringUtils.isNotBlank(prefix))
	    	  sb.append(prefix);
	      dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION);
	      numberFormat.format(seq, sb, HELPER_POSITION);
	      if (seq == MAX)
	    	  seq = 0;
	      else
	          seq++;
	      if(StringUtils.isNotBlank(suffix))
	    	  sb.append(".").append(suffix);
	      return sb.toString();
	}	

}
