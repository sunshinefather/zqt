package com.zyt.web.publics.utils;

import java.lang.reflect.Field;

/**
 * @author 反射工具类
 */
public class ReflectHelper {

	private ReflectHelper(){};
	
	/**
	 * 描述:根据名字获取字段
	 * 时间:2014-3-4
	 * 作者:maliang
	 * @param object
	 * @param filedName
	 * @return
	 */
	public static Field getFieldByFieldName(Object object,String filedName){
		 for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass  
	                .getSuperclass()) {  
	            try {  
	                return superClass.getDeclaredField(filedName);  
	            } catch (NoSuchFieldException e) {  
	            }  
	        }  
		 return null;
	}
	
	/**
	 * 描述:根据名字获取字段值
	 * 时间:2014-3-4
	 * 作者:鲜虎
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	 public static Object getValueByFieldName(Object obj, String fieldName)  
	            throws SecurityException, NoSuchFieldException,  
	            IllegalArgumentException, IllegalAccessException {  
		    String[] fields=fieldName.split("[.]");
		    Object value = null;
		    if(fields.length==1){
		    	Field field = getFieldByFieldName(obj, fieldName);  
			        if(field!=null){  
			            if (field.isAccessible()) {  
			                value = field.get(obj);  
			            } else {  
			                field.setAccessible(true);  
			                value = field.get(obj);  
			                field.setAccessible(false);  
			            }  
			        }
			        return value;
		    }else{
		        int i=0;
			    while(i<fields.length){
			    	value= getValueByFieldName(obj, fields[0]); 
			    	if(value!=null){
				    	return getValueByFieldName(value, fieldName.replace(fields[i]+".", ""));
			    	}else{
			    		return null;
			    	}
			    }
		    }
	        return null;  
	    }   
	 
	 /**
	  * 描述:根据名字设置字段值
	  * 时间:2014-3-4
	  * 作者:maliang
	  * @param obj
	  * @param fieldName
	  * @param value
	  * @throws SecurityException
	  * @throws NoSuchFieldException
	  * @throws IllegalArgumentException
	  * @throws IllegalAccessException
	  */
	 public static void setValueByFieldName(Object obj, String fieldName,  
	            Object value) throws SecurityException, NoSuchFieldException,  
	            IllegalArgumentException, IllegalAccessException {  
	        Field field = obj.getClass().getDeclaredField(fieldName);  
	        if (field.isAccessible()) {  
	            field.set(obj, value);  
	        } else {  
	            field.setAccessible(true);  
	            field.set(obj, value);  
	            field.setAccessible(false);  
	        }  
	    }  
}
