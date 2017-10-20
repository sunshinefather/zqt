package com.zyt.web.publics.utils.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

/**
 * 
 * @author LiuChuang
 * @description 自定义日期转换
 * @version 1.0
 * @date 2014-4-2
 */
public class DateConverter implements Converter<String, Date>{
    private List<String> patterns = new ArrayList<String>();

    public DateConverter() {//循序不能调整
    	patterns.add("yyyy-MM-dd HH:mm:ss");
    	patterns.add("yyyy-MM-dd HH:mm");
    	patterns.add("yyyy-MM-dd");
    }
    
    @Override
    public Date convert(String text) {
        if (text == null) {
            return null;
        }
        for (String pattern : patterns) {
            Date date = tryConvert(text, pattern);
            if (date != null) {
                return date;
            }
        }
        return null;
    }

    public Date tryConvert(String text, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(text);
        } catch (ParseException ex) {
        	return null;
        }
    }

    public void setPatterns(List<String> patterns) {
        this.patterns = patterns;
    }
}
