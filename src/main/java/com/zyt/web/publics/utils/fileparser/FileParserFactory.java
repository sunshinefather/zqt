package com.zyt.web.publics.utils.fileparser;

import java.io.InputStream;
import java.util.Map;
/**
 * 文件解析工厂(目前支持excel)
 * @ClassName:  FileParserFactory   
 * @Description:   
 * @author: sunshine  
 * @date:   2014年3月31日 上午10:59:20
 * 
 * 导入demo:
 Map<String, String> templateColumns=new LinkedHashMap<String, String>()
 {
		templateColumns.put("医院名称", "name");
		templateColumns.put("医院等级", "grade");
		templateColumns.put("医院电话", "tel");
		templateColumns.put("医院地址", "address");
		templateColumns.put("经度", "lng");
		templateColumns.put("纬度", "lat");
		templateColumns.put("医院简介", "introduction");
	}
 FileParser parser = FileParserFactory.getExcelFileParser(
							fileName, fileUpload.getInputStream(),
							templateColumns);
							
 */
public class FileParserFactory
{
	private static final String XLS = "xls";

	private static final String XLSX = "xlsx";

	/**
	 * 根据文件名创建文件解析器
	 * 
	 * @param file 文件完整路径
	 * @return
	 */
	public static FileParser getExcelFileParser(String file,Map<String, String> mapper)
	{
		try
		{
			String[] suffixs = file.split("\\.");
			String suffix = suffixs[suffixs.length - 1];
			if (XLS.equalsIgnoreCase(suffix) || XLSX.equalsIgnoreCase(suffix))
			{
				return new ExcelFileParser(file,mapper);
			}

			return null;
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	/**
	 * 创建文件解析器
	 * 
	 * @param fileName 文件名
	 * @param is 文件流
	 * @return
	 */
	public static FileParser getExcelFileParser(String fileName,InputStream is,Map<String, String> mapper)
	{
		try
		{
			String[] suffixs = fileName.split("\\.");
			String suffix = suffixs[suffixs.length - 1];
            if (XLS.equalsIgnoreCase(suffix) || XLSX.equalsIgnoreCase(suffix))
			{
				return new ExcelFileParser(is,mapper);
			}
			return null;
		}
		catch (Exception e)
		{
			return null;
		}
	}
}
