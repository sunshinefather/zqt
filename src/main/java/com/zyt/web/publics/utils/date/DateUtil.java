package com.zyt.web.publics.utils.date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 
 * @author LiuChuang
 * @description 日期工具类
 * @version 1.0
 * @date 2014-4-4
 */
public class DateUtil {
    /**
     * 
     *@Description: 是否为闰年
     *@param year
     *@return boolean true是 false 不是
     *@version: v1.0.0
     *@author: LiuChuang
     *@date: 2014-4-4上午9:35:15
     */
    public boolean leapYear(int year) {
        boolean leap;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    	leap = true;
                    }
                else {
                	leap = false;
                }
            }
            else {
            	leap = true;
            }
        }
        else {
        	leap = false;
        }
        return leap;
    }
    
    /**
     * 
     *@Description: 按照格式格式化字符串为日期
     *@param dateStr
     *@param pattern
     *@return
     *Date
     *@version: v1.0.0
     *@author: LiuChuang
     *@date: 2014-4-4上午9:35:52
     */
    public static Date parseToDate(String dateStr,String pattern){
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    		 return  sdf.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
    }
    /**
     * 根据身份证号码计算生日
     * @Title: parseToBirthday
     * @Description: TODO  
     * @param: @param identity
     * @param: @return      
     * @return: Date
     * @author: sunshine  
     * @throws
     */
    public static Date parseToBirthday(String identity){
    	try {
        	String birthday = identity.substring(6,14);
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    		return sdf.parse(birthday);
		} catch (Exception e) {
			return null;
		}
    }
    /**
     * 根据身份证号码计算年龄
     * @Title: parseToAge
     * @Description: TODO  
     * @param: @param identity
     * @param: @return      
     * @return: Date
     * @author: sunshine  
     * @throws
     */
    public static Integer parseToAge(String identity){
    	try {
    	Date birthDay=parseToBirthday(identity);
    	Calendar cal = Calendar.getInstance();
    	if (cal.before(birthDay)) {
                throw new IllegalArgumentException("出生时间大于当前时间!");
          }
            int yearNow = cal.get(Calendar.YEAR);
            int monthNow = cal.get(Calendar.MONTH) + 1;
            int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
            
            cal.setTime(birthDay);
            int yearBirth = cal.get(Calendar.YEAR);
            int monthBirth = cal.get(Calendar.MONTH)+1;
            int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
            int age = yearNow - yearBirth;

            if (monthNow <= monthBirth) {
                if (monthNow == monthBirth) {
                    if (dayOfMonthNow < dayOfMonthBirth) {
                        age--;
                    }
                } else {
                    age--;
                }
            }
            if(age<0){
            	return null;
            }
            return age;
		} catch (Exception e) {
			return null;
		}
    }
    /**
     * 获取本周周一(本周第一天，以星期一开始)
     * @Title: getMondayOfCurrentWeek
     * @Description: TODO  
     * @param: @return      
     * @return: Date
     * @author: sunshine  
     * @throws
     */
    public static Date getMondayOfCurrentWeek(){
    	Calendar c = Calendar.getInstance();
    	c.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);//本周第一天，以星期日开始
    	return c.getTime();
    }
    /**
     * 获取本周周日(本周第一天，以星期一开始)
     * @Title: getSundayOfCurrentWeek
     * @Description: TODO  
     * @param: @return      
     * @return: Date
     * @author: sunshine  
     * @throws
     */
    public static Date getSundayOfCurrentWeek(){
    	Calendar c = Calendar.getInstance();
    	c.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
    	c.add(Calendar.DAY_OF_WEEK,1);
    	return c.getTime();
    }
    /**
     * 获取本月第一天
     * @Title: getDayOfCurrentMonth
     * @Description: TODO  
     * @param: @return      
     * @return: Date
     * @author: sunshine  
     * @throws
     */
    public static Date getFirstDayOfCurrentMonth(){
    	Calendar c = Calendar.getInstance();
    	c.set(Calendar.DAY_OF_MONTH,1);
    	return c.getTime();
    }
    /**
     * 获取本月最后一天
     * @Title: getLastDayOfCurrentMonth
     * @Description: TODO  
     * @param: @return      
     * @return: Date
     * @author: sunshine  
     * @throws
     */
    public static Date getLastDayOfCurrentMonth(){
    	Calendar c = Calendar.getInstance();
    	c.add(Calendar.MONTH, 1);
    	c.set(Calendar.DAY_OF_MONTH, 1);
    	c.add(Calendar.DAY_OF_MONTH, -1);
    	return c.getTime();
    }
    
    /**
     * 
     *@Description: 获取日期
     *@param date
     *@return
     *@version: v1.0.0
     *@author: Kevin
     *@date: 2015年9月8日下午1:42:12
     */
    public static Date getDate(Date date){
    	return parseToDate(parseToString(date, "yyyy-MM-dd"), "yyyy-MM-dd");
    }
    
    /**
     * 
     *@Description: 获取日期时间
     *@param date
     *@return
     *@version: v1.0.0
     *@author: Kevin
     *@date: 2015年9月8日下午1:42:22
     */
    public static Date getDateTime(Date date){
    	return parseToDate(parseToString(date, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
    }
    
    
    /**
     * 
     *@Description: 按照格式把日期转换为字符串
     *@param date
     *@param pattern
     *@return String
     *@version: v1.0.0
     *@author: LiuChuang
     *@date: 2014-4-4上午9:39:18
     */
    public static String parseToString(Date date,String pattern){
    	if(date==null)return null;
    	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    	return sdf.format(date);
    }
    
    
    /**
     * 
     *@Description: 计算两个日期之间相差的天数，其中不包括d1和d2
     *@param d1
     *@param d2
     *@return int
     *@version: v1.0.0
     *@author: LiuChuang
     *@date: 2014-4-4上午9:41:08
     */
    public static int calculateDayIn(Date d1, Date d2)  {
		long n1 = d1.getTime();
		long n2 = d2.getTime();
		long diff = Math.abs(n1 - n2);
		diff /= 3600 * 1000 * 24;
		return (int)(diff);
	}
    

    /**
     * 
     *@Description: 计算两个日期之间相差的周，精确到天
     *@param d1
     *@param d2
     *@return String (周 + 天)
     *@version: v1.0.0
     *@author: LiuChuang
     *@date: 2014-4-4上午9:40:46
     */
    public static String calculateWeekIn(Date d1, Date d2)  {
    	int cal = calculateDayIn(d1, d2);
    	int z=((int)cal / 7);
    	int t=cal%7;
		return z>=40?"39+6":z+"+"+t;
	}
    
    /**
     * 
     *@Description: 计算预产期
     *末次月经 当月如果大于3就月份-3  如果小于3就月份+9
     *@param lmp 末次月经
     *@return String
     *@version: v1.0.0
     *@author: LiuChuang
     *@date: 2014-4-4上午10:26:53
     */
    public static String edc(Date lmp){
    	Calendar  cal = Calendar.getInstance();
    	cal.setTime(lmp);
    	if(cal.get(Calendar.MONTH) > 3){
    		cal.add(Calendar.MONTH,-3);
    		cal.add(Calendar.YEAR,1);
    	}else{
    		cal.add(Calendar.MONTH,9);
    	}
    	cal.add(Calendar.DAY_OF_MONTH,7);
    	return parseToString(cal.getTime(), "yyyy-MM-dd");
    }
    
    /**
     * 
     *@Description: 预产期逆推末次月经
     *@param edc
     *@return
     *@version: v1.0.0
     *@author: Kevin
     *@date: 2015年7月15日下午7:19:13
     */
    public static String curMenstruation(Date edc){
    	Calendar  cal = Calendar.getInstance();
    	cal.setTime(edc);
    	if(cal.get(Calendar.MONTH) < 10){
    		cal.add(Calendar.MONTH,3);
    		cal.add(Calendar.YEAR,-1);
    	}else{
    		cal.add(Calendar.MONTH,-9);
    	}
    	cal.add(Calendar.DAY_OF_MONTH,-7);
    	return parseToString(cal.getTime(), "yyyy-MM-dd");
    }
    
    public static void main(String[] args) {
    	System.out.println(edc(getDate(parseToDate("2015-02-24", "yyyy-MM-dd"))));
    	System.out.println(curMenstruation(parseToDate("2015-11-27", "yyyy-MM-dd")));

    	System.out.println(calculateWeekIn(getDate(new Date()),getDate(parseToDate("2015-02-24", "yyyy-MM-dd"))));
    	
    	System.out.println(calculateDayIn(getDate(new Date()),getDate(parseToDate("2015-09-22", "yyyy-MM-dd"))));
	}
}