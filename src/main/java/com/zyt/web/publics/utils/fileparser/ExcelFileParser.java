package com.zyt.web.publics.utils.fileparser;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * excel解析器
 * @ClassName:  ExcelFileParser   
 * @Description:   
 * @author: sunshine  
 * @date:   2014年3月31日 上午11:12:30
 */
public class ExcelFileParser implements FileParser
{
	private final Logger log = LoggerFactory.getLogger(ExcelFileParser.class);
	private Sheet sheet = null;

	private int currentRow = 0;

	private Workbook wb = null;
	
	private Map<Integer, String> columnIndexMap = new HashMap<Integer, String>();//行首
	
	private Map<String, String> columns = new HashMap<String, String>();//解析映射配置
	
	public ExcelFileParser(String filePath,Map<String, String> mapper)
	{
		File file = new File(filePath);
		if(mapper==null || mapper.size()==0){
		   log.info("excel导入模板不能为空");
           return;
		}
		this.setColumns(mapper);
		try
		{
			wb = WorkbookFactory.create(file);
			this.init();
		}
		catch (Exception e)
		{
          e.printStackTrace();
		}
	}
	
	public ExcelFileParser(InputStream inp,Map<String, String> mapper)
	{
		if(mapper==null || mapper.size()==0){
			   log.info("excel导入模板不能为空");
	           return;
			}
		this.setColumns(mapper);
		try
		{
			wb = WorkbookFactory.create(inp);
			this.init();
		}
		catch (Exception e)
		{

		}
	}
	
	public Map<String, String> parserLine() {
		Row row;
		Map<String, String> result = new HashMap<String, String>();
		
		for (int i = 0; i < columns.size(); i++) {
			row = sheet.getRow(currentRow);
			if ((row != null) && (row.getLastCellNum() != 0)) {
				Cell cell = row.getCell(i);
				String column = columnIndexMap.get(i);
				String key = columns.get(column);
				if (columns.containsKey(column)) {
					if (cell!=null) {
						int cellType = cell.getCellType();
						switch (cellType) {
						case Cell.CELL_TYPE_NUMERIC:
							String ss;
						    if (DateUtil.isCellDateFormatted(cell)) {
						        ss = DateUtil.getJavaDate(cell.getNumericCellValue()).toString();   
						    } else {
						    	ss = String.valueOf(cell.getNumericCellValue());
						    	if(ss.endsWith(".0")){
						    		ss=ss.replaceAll("[\\.0]", "");
						    	}
						    }
							result.put(key, ss);
							break;
						case Cell.CELL_TYPE_STRING:
							result.put(key, cell.getRichStringCellValue().getString().trim());
							break;
						case Cell.CELL_TYPE_FORMULA:
							result.put(key, cell.getCellFormula().trim());
							break;
						default:
							result.put(key, cell.toString().trim());
							break;
						}
					}else{
						result.put(key, "");
					}
				}
			}
		}

		return result;
	}
	
	public boolean hasMore()
	{
		this.currentRow++;
		if (this.currentRow <= sheet.getLastRowNum())
		{
			return true;
		}
		return false;
	}
	
	public synchronized void close()
	{
		this.wb.cloneSheet(0);
	}
	
	
	private void init() throws Exception
	{
		sheet = wb.getSheetAt(0);
		if (sheet.getLastRowNum() == 0)
		{
			throw new Exception("导入文件中数据不存在！");
		}
		// 解析行首
		Row row = sheet.getRow(0);
		if (row != null) {
			for (int i = 0; i < row.getLastCellNum() + 1; i++) {
				Cell cell = row.getCell(i);
				columnIndexMap.put(i, cell.toString());
			}
		}
		currentRow++;
	}

	@Override
	public boolean isEmpty() {
		if(sheet.getLastRowNum() != 0){
			return false;
		}else {
			return true;
		}
	}

	public Map<String, String> getColumns() {
		return columns;
	}

	public void setColumns(Map<String, String> columns) {
		this.columns = columns;
	}

	@Override
	public List<Map<String, String>> parserAllLine() {
		List<Map<String, String>> list =new ArrayList<Map<String, String>>();
		while (this.hasMore()){
			list.add(parserLine());
		}
		return list;
	}
}
