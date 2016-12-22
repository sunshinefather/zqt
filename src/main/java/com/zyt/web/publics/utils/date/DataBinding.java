package com.zyt.web.publics.utils.date;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author LiuChuang
 * @description 注册自定义日期转换控制器
 * @version 1.0
 * @date 2014-3-28
 */
public class DataBinding implements WebBindingInitializer {

	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		MyDateFormat mdf = new MyDateFormat("yyyy-MM-dd HH:mm:ss");
    	mdf.setLenient(false); 
    	binder.registerCustomEditor(Date.class,  new CustomDateEditor(mdf, true));
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));  
	    binder.registerCustomEditor(java.sql.Timestamp.class,new CustomTimestampEditor(mdf, true));
	}
	
	class CustomTimestampEditor extends PropertyEditorSupport {
		private final SimpleDateFormat dateFormat;
		private final boolean allowEmpty;
		private final int exactDateLength;

		public CustomTimestampEditor(SimpleDateFormat dateFormat, boolean allowEmpty) {
			this.dateFormat = dateFormat;
			this.allowEmpty = allowEmpty;
			this.exactDateLength = -1;
		}

		public CustomTimestampEditor(SimpleDateFormat dateFormat,
				boolean allowEmpty, int exactDateLength) {
			this.dateFormat = dateFormat;
			this.allowEmpty = allowEmpty;
			this.exactDateLength = exactDateLength;
		}
		
		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			if ((this.allowEmpty) && (!(StringUtils.hasText(text)))) {
				setValue(null);
			} else {
				if ((text != null) && (this.exactDateLength >= 0)
						&& (text.length() != this.exactDateLength)) {
					throw new IllegalArgumentException(
							"Could not parse date: it is not exactly"
									+ this.exactDateLength + "characters long");
				}
				try {
					setValue(new Timestamp(this.dateFormat.parse(text).getTime()));
				} catch (ParseException ex) {
					throw new IllegalArgumentException("Could not parse date: "
							+ ex.getMessage(), ex);
				}
			}
		}

		public String getAsText() {
			Timestamp value = (Timestamp) getValue();
			return ((value != null) ? this.dateFormat.format(value) : "");
		}
	}
	
	class MyDateFormat extends SimpleDateFormat{
		private static final long serialVersionUID = 1L;
		private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		private static final String REG_YMD="\\d{4}\\-\\d{2}\\-\\d{2}";
		private static final String REG_YMDHMS="\\d{4}\\-\\d{2}\\-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}";
		
		public MyDateFormat(String pattern) {
			super(pattern);
		}

		
		@Override
		public Date parse(String source) throws ParseException {
			Date date = null;
			try{
				if(source.matches(REG_YMDHMS)){
					date =super.parse(source);
				}else if(source.matches(REG_YMD)){
					date =this.sdf.parse(source);
				}
			}catch(ParseException e){
				System.err.println("Could not parse date: it is not exactly.");
			}
			return date;
		}
		
	}

}
