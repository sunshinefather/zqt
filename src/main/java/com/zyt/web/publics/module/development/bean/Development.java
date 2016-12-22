package com.zyt.web.publics.module.development.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.ibatis.type.Alias;

/**
 * @Description:企业发展管理bean
 * @ClassName:  Development 
 * @author: sunshine  
 */
@Alias("Development")
public class Development implements Serializable {
	private static final long serialVersionUID = 1L;
	/**主键*/
	private String id;
	/**企业id*/
	private String hospitalId;
	
	private String hospitalName;
	
	private String statisticsTime;
	/**产值*/
	private BigDecimal chanzhi;
	/**企业销售*/
	private BigDecimal xiaoshou;
	/**企业利润*/
	private BigDecimal lirun;
	/**企业税收*/
	private BigDecimal shuishou;
	/**企业用水*/
	private BigDecimal yongshui;
	/**企业用电*/
	private BigDecimal yongdian;
	private BigDecimal yongqi;
	/**固定资产投资*/
	private BigDecimal gudingzichantouzi;
	/**出口创汇*/
	private BigDecimal chukouchuanghui;
	private String str1;
	private String str2;
	private BigDecimal str3;
	private BigDecimal str4;
	public void setId( String id ) {
		this.id = id;
	}
	public String getId(){
		return id;
	}
	public void setHospitalId( String hospitalId ) {
		this.hospitalId = hospitalId;
	}
	public String getHospitalId(){
		return hospitalId;
	}
	public void setStatisticsTime( String statisticsTime ) {
		this.statisticsTime = statisticsTime;
	}
	public String getStatisticsTime(){
		return statisticsTime;
	}
	public void setChanzhi( BigDecimal chanzhi ) {
		this.chanzhi = chanzhi;
	}
	public BigDecimal getChanzhi(){
		return chanzhi;
	}
	public void setXiaoshou( BigDecimal xiaoshou ) {
		this.xiaoshou = xiaoshou;
	}
	public BigDecimal getXiaoshou(){
		return xiaoshou;
	}
	public void setLirun( BigDecimal lirun ) {
		this.lirun = lirun;
	}
	public BigDecimal getLirun(){
		return lirun;
	}
	public void setShuishou( BigDecimal shuishou ) {
		this.shuishou = shuishou;
	}
	public BigDecimal getShuishou(){
		return shuishou;
	}
	public void setYongshui( BigDecimal yongshui ) {
		this.yongshui = yongshui;
	}
	public BigDecimal getYongshui(){
		return yongshui;
	}
	public void setYongdian( BigDecimal yongdian ) {
		this.yongdian = yongdian;
	}
	public BigDecimal getYongdian(){
		return yongdian;
	}
	public void setYongqi( BigDecimal yongqi ) {
		this.yongqi = yongqi;
	}
	public BigDecimal getYongqi(){
		return yongqi;
	}
	public void setGudingzichantouzi( BigDecimal gudingzichantouzi ) {
		this.gudingzichantouzi = gudingzichantouzi;
	}
	public BigDecimal getGudingzichantouzi(){
		return gudingzichantouzi;
	}
	public void setChukouchuanghui( BigDecimal chukouchuanghui ) {
		this.chukouchuanghui = chukouchuanghui;
	}
	public BigDecimal getChukouchuanghui(){
		return chukouchuanghui;
	}
	public void setStr1( String str1 ) {
		this.str1 = str1;
	}
	public String getStr1(){
		return str1;
	}
	public void setStr2( String str2 ) {
		this.str2 = str2;
	}
	public String getStr2(){
		return str2;
	}
	public void setStr3( BigDecimal str3 ) {
		this.str3 = str3;
	}
	public BigDecimal getStr3(){
		return str3;
	}
	public void setStr4( BigDecimal str4 ) {
		this.str4 = str4;
	}
	public BigDecimal getStr4(){
		return str4;
	}
	/**  
	 * @Title:  getHospitalName <BR>    
	 * @return: String <BR>  
	 */
	public String getHospitalName() {
		return hospitalName;
	}
	/**  
	 * @Title:  setHospitalName <BR>   
	 * @return: String <BR> 
	 */
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

    
}
