package com.zyt.web.publics.utils.fileparser;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.zyt.web.publics.utils.reflection.ReflectionUtils;
/**
 * 导出demo:
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
 new ExportExcel("医院信息", list, templateColumns, response).exportExcel();
 * @ClassName:  ExportExcel   
 * @Description:   
 * @author: sunshine  
 * @date:   2014年5月14日 上午10:30:33
 */
public class ExportExcel {  

    public static final String EXCEL_Ext = ".xlsx";
  
    private Collection<?> dataset; //电子表格数据
  
    private String title;// 电子表格标题  

    private Map<String,String> mapper;// 列名与类属性名的对应关系  
    
    private OutputStream out = null;//输出流
    /**
     * 导出到输出流
     * @Title:  ExportExcel   
     * @Description:    
     * @param:  @param title
     * @param:  @param dataset
     * @param:  @param mapper
     * @param:  @param os  
     * @throws
     */
    public ExportExcel(String title,Collection<?> dataset,Map<String,String> mapper,OutputStream os) {  
        this.out=os; 
        this.dataset = dataset; 
        this.mapper=mapper;
        this.title=title;
    }
    /**
     * 专用导出到http 响应流
     * @Title:  ExportExcel   
     * @Description:    
     * @param:  @param title 表格标题
     * @param:  @param dataset 导出数据源
     * @param:  @param mapper 导出模板映射
     * @param:  @param response  响应
     * @throws
     */
    public ExportExcel(String title,Collection<?> dataset,Map<String,String> mapper,HttpServletResponse response) { 
        this.dataset = dataset; 
        this.mapper=mapper;
        this.title=(title==null || "".equals(title))?"快乐孕宝":title;
        try {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment; filename="+ new String(title.getBytes("utf-8"), "ISO8859-1")+EXCEL_Ext);
			this.out=response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    /**
     * 导入Excel数据到输出流中
     * @Title: exportExcel   
     * @Description: 
     * @param: @param title 标题
     * @param: @param headers 表格属性列名
     * @param: @param out 流对象
     * @return: void      
     * @throws
     */
    public void exportExcel() {
        String[] headers = new String[mapper.size()];
        int hindex = 0;  
        for (String colField : mapper.keySet()) {
        	headers[hindex++] = colField;
        }
        //创建一个工作薄  
    	XSSFWorkbook workbook = new XSSFWorkbook();  
        // 创建一张电子表格
        XSSFSheet sheet = workbook.createSheet(title);
        // 首行样式  
        XSSFCellStyle style = workbook.createCellStyle();  
        style.setFillForegroundColor(HSSFColor.WHITE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        XSSFFont font = workbook.createFont();  
        font.setFontHeightInPoints((short)14);  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);  
        
        //内容样式  
        XSSFCellStyle style2 = workbook.createCellStyle();  
        style2.setFillForegroundColor(HSSFColor.WHITE.index);  
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        XSSFFont font2 = workbook.createFont();
        font2.setFontHeightInPoints((short)12); 
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        style2.setFont(font2);  

        // 产生表格标题行  
        XSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {  
            XSSFCell cell = row.createCell(i);    
            cell.setCellStyle(style);
            XSSFRichTextString text = new XSSFRichTextString(headers[i].trim());
            cell.setCellValue(text);  
        }
        // 遍历集合数据，产生数据行  
        Iterator<?> it = dataset.iterator();  
        int index = 1;  
        while (it.hasNext()) {
            row = sheet.createRow(index++);  
            row.setRowStyle(style2);
            Object t = it.next();
            short cellIndex = 0;// Excel列索引 
            for (String fieldName : mapper.values()) {  
                XSSFCell cell = row.createCell(cellIndex++);
                cell.setCellStyle(style2);
                try { 
                	Object value=ReflectionUtils.invokeGetter(t, fieldName);
                    // 判断值的类型后进行强制类型转换  
                    String textValue = null;
                    if (value == null) {
                        textValue = "";  
                    }else if (value instanceof Date) {
                        Date date = (Date) value;  
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                        textValue = sdf.format(date);  
                    } else {  
                        textValue = value.toString();
                    }
                    
                    if (textValue != null) {  
                    	XSSFRichTextString richString = new XSSFRichTextString(textValue);
                    	cell.setCellValue(richString);
                    }
                    
                } catch (Exception e) {  
                    e.printStackTrace();  
                }
            }  
  
        }  
  
        try {  
            workbook.write(out);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (out != null) {  
                try {  
                    out.close();
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }
    
    public Collection<?> getDataset() {
		return dataset;
	}
	public void setDataset(Collection<?> dataset) {
		this.dataset = dataset;
	}
	public Map<String, String> getMapper() {
		return mapper;
	}
	public void setMapper(Map<String, String> mapper) {
		this.mapper = mapper;
	}
	public OutputStream getOut() {
		return out;
	}
	public void setOut(OutputStream out) {
		this.out = out;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {  
        this.title = title;  
    }
}  