package com.zyt.web.publics.module.development.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;

import org.springframework.beans.BeanUtils;

public class DevelopmentExport implements Serializable {
	private static final long serialVersionUID = 1L;
	/**主键*/
	private String id;
	/**企业id*/
	private String hospitalId;
	/**企业名称*/
	private String hospitalName;
	/**统计时间*/
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
	
	/**产值*/
	private BigDecimal differchanzhi;
	/**企业销售*/
	private BigDecimal differxiaoshou;
	/**企业利润*/
	private BigDecimal differlirun;
	/**企业税收*/
	private BigDecimal differshuishou;
	/**企业用水*/
	private BigDecimal differyongshui;
	/**企业用电*/
	private BigDecimal differyongdian;
	private BigDecimal differyongqi;
	/**固定资产投资*/
	private BigDecimal differgudingzichantouzi;
	/**出口创汇*/
	private BigDecimal differchukouchuanghui;
	private String str1;
	private String str2;
	private BigDecimal str3;
	private BigDecimal str4;
	private Development lastYearDevelopment;
	private NumberFormat nf = NumberFormat.getInstance();
	public DevelopmentExport(Development dp,String hospitalId){
		BeanUtils.copyProperties(dp, this);
		this.hospitalId=hospitalId;
	}
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
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public Development getLastYearDevelopment() {
		return lastYearDevelopment;
	}
	public void setLastYearDevelopment(Development lastYearDevelopment) {
		this.lastYearDevelopment = lastYearDevelopment;
	}
	
/*	public BigDecimal getDifferchanzhi() {
		   if(this.getLastYearDevelopment()!=null && this.getLastYearDevelopment().getChanzhi()!=null){
			   if(chanzhi!=null){
				   return chanzhi.subtract(this.getLastYearDevelopment().getChanzhi());
			   }else{
				   return this.getLastYearDevelopment().getChanzhi().multiply(new BigDecimal(-1));
			   }
		   }else{
			   return chanzhi;
		   }
	}*/
	public String getDifferchanzhi() {
		if(this.getLastYearDevelopment()!=null && this.getLastYearDevelopment().getChanzhi()!=null){
			if(chanzhi!=null){
				return nf.format(new BigDecimal(100).multiply(chanzhi.divide(this.getLastYearDevelopment().getChanzhi(),4,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(1))))+"%";
			}else{
				return "-100%";
			}
		}else{
			return "100%";
		}
	}
	public void setDifferchanzhi(BigDecimal differchanzhi) {
		this.differchanzhi = differchanzhi;
	}
	
/*	public BigDecimal getDifferxiaoshou() {
		if(this.getLastYearDevelopment()!=null && this.getLastYearDevelopment().getXiaoshou()!=null){
			   if(xiaoshou!=null){
				   return xiaoshou.subtract(this.getLastYearDevelopment().getXiaoshou());
			   }else{
				   return this.getLastYearDevelopment().getXiaoshou().multiply(new BigDecimal(-1));
			   }
		   }else{
			   return xiaoshou;
		   }
	}*/
	public String getDifferxiaoshou() {
		if(this.getLastYearDevelopment()!=null && this.getLastYearDevelopment().getXiaoshou()!=null){
			if(xiaoshou!=null){
				return nf.format(new BigDecimal(100).multiply(xiaoshou.divide(this.getLastYearDevelopment().getXiaoshou(),4,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(1))))+"%";
			}else{
				return "-100%";
			}
		}else{
			return "100%";
		}
	}
	public void setDifferxiaoshou(BigDecimal differxiaoshou) {
		this.differxiaoshou = differxiaoshou;
	}
/*	public BigDecimal getDifferlirun() {
		if(this.getLastYearDevelopment()!=null && this.getLastYearDevelopment().getLirun()!=null){
			if(lirun!=null){
				return lirun.subtract(this.getLastYearDevelopment().getLirun());
			}else{
				return this.getLastYearDevelopment().getLirun().multiply(new BigDecimal(-1));
			}
		}else{
			return lirun;	
		}
	}*/
	public String getDifferlirun() {
		if(this.getLastYearDevelopment()!=null && this.getLastYearDevelopment().getLirun()!=null){
			if(lirun!=null){
				return nf.format(new BigDecimal(100).multiply(lirun.divide(this.getLastYearDevelopment().getLirun(),4,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(1))))+"%";
			}else{
				return "-100%";
			}
		}else{
			return "100%";	
		}
	}
	public void setDifferlirun(BigDecimal differlirun) {
		this.differlirun = differlirun;
	}
/*	public BigDecimal getDiffershuishou() {
		if(this.getLastYearDevelopment()!=null && this.getLastYearDevelopment().getShuishou()!=null){
			   if(shuishou!=null){
				   return shuishou.subtract(this.getLastYearDevelopment().getShuishou());
			   }else{
				   return this.getLastYearDevelopment().getShuishou().multiply(new BigDecimal(-1));
			   }
		   }else{
			   return shuishou;
		   }
	}*/
	
	public String getDiffershuishou() {
		if(this.getLastYearDevelopment()!=null && this.getLastYearDevelopment().getShuishou()!=null){
			if(shuishou!=null){
				return nf.format(new BigDecimal(100).multiply(shuishou.divide(this.getLastYearDevelopment().getShuishou(),4,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(1))))+"%";
			}else{
				return "-100%";
			}
		}else{
			return "100%";
		}
	}
	public void setDiffershuishou(BigDecimal differshuishou) {
		this.differshuishou = differshuishou;
	}
/*	public BigDecimal getDifferyongshui() {
		if(this.getLastYearDevelopment()!=null && this.getLastYearDevelopment().getYongshui()!=null){
			   if(yongshui!=null){
				   return yongshui.subtract(this.getLastYearDevelopment().getYongshui());
			   }else{
				   return this.getLastYearDevelopment().getYongshui().multiply(new BigDecimal(-1));
			   }
		   }else{
			   return yongshui;
		   }
	}*/
	public String getDifferyongshui() {
		if(this.getLastYearDevelopment()!=null && this.getLastYearDevelopment().getYongshui()!=null){
			if(yongshui!=null){
				return nf.format(new BigDecimal(100).multiply(yongshui.divide(this.getLastYearDevelopment().getYongshui(),4,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(1))))+"%";
			}else{
				return "-100%";
			}
		}else{
			return "100%";
		}
	}
	public void setDifferyongshui(BigDecimal differyongshui) {
		this.differyongshui = differyongshui;
	}
/*	public BigDecimal getDifferyongdian() {
		if(this.getLastYearDevelopment()!=null && this.getLastYearDevelopment().getYongdian()!=null){
			   if(yongdian!=null){
				   return yongdian.subtract(this.getLastYearDevelopment().getYongdian());
			   }else{
				   return this.getLastYearDevelopment().getYongdian().multiply(new BigDecimal(-1));
			   }
		   }else{
			   return yongdian;
		   }
	}*/
	public String getDifferyongdian() {
		if(this.getLastYearDevelopment()!=null && this.getLastYearDevelopment().getYongdian()!=null){
			if(yongdian!=null){
				return nf.format(new BigDecimal(100).multiply(yongdian.divide(this.getLastYearDevelopment().getYongdian(),4,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(1))))+"%";
			}else{
				return "-100%";
			}
		}else{
			return "100%";
		}
	}
	public void setDifferyongdian(BigDecimal differyongdian) {
		this.differyongdian = differyongdian;
	}
/*	public BigDecimal getDifferyongqi() {
		if(this.getLastYearDevelopment()!=null && this.getLastYearDevelopment().getYongqi()!=null){
			   if(yongqi!=null){
				   return yongqi.subtract(this.getLastYearDevelopment().getYongqi());
			   }else{
				   return this.getLastYearDevelopment().getYongqi().multiply(new BigDecimal(-1));
			   }
		   }else{
			   return yongqi;
		   }
	}*/
	public String getDifferyongqi() {
		if(this.getLastYearDevelopment()!=null && this.getLastYearDevelopment().getYongqi()!=null){
			if(yongqi!=null){
				return nf.format(new BigDecimal(100).multiply(yongqi.divide(this.getLastYearDevelopment().getYongqi(),4,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(1))))+"%";
			}else{
				return "-100%";
			}
		}else{
			return "100%";
		}
	}
	public void setDifferyongqi(BigDecimal differyongqi) {
		this.differyongqi = differyongqi;
	}
/*	public BigDecimal getDiffergudingzichantouzi() {
		if(this.getLastYearDevelopment()!=null && this.getLastYearDevelopment().getGudingzichantouzi()!=null){
			   if(gudingzichantouzi!=null){
				   return gudingzichantouzi.subtract(this.getLastYearDevelopment().getGudingzichantouzi());
			   }else{
				   return this.getLastYearDevelopment().getGudingzichantouzi().multiply(new BigDecimal(-1));
			   }
		   }else{
			   return gudingzichantouzi;
		   }
	}*/
	
	public String getDiffergudingzichantouzi() {
		if(this.getLastYearDevelopment()!=null && this.getLastYearDevelopment().getGudingzichantouzi()!=null){
			if(gudingzichantouzi!=null){
				return nf.format(new BigDecimal(100).multiply(gudingzichantouzi.divide(this.getLastYearDevelopment().getGudingzichantouzi(),4,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(1))))+"%";
			}else{
				return "-100.0%";
			}
		}else{
			return "100.0%";
		}
	}
	public void setDiffergudingzichantouzi(BigDecimal differgudingzichantouzi) {
		this.differgudingzichantouzi = differgudingzichantouzi;
	}
/*	public BigDecimal getDifferchukouchuanghui() {
		if(this.getLastYearDevelopment()!=null && this.getLastYearDevelopment().getChukouchuanghui()!=null){
			   if(chukouchuanghui!=null){
				   return chukouchuanghui.subtract(this.getLastYearDevelopment().getChukouchuanghui());
			   }else{
				   return this.getLastYearDevelopment().getChukouchuanghui().multiply(new BigDecimal(-1));
			   }
		   }else{
			   return chukouchuanghui;
		   }
	}*/
	public String getDifferchukouchuanghui() {
		if(this.getLastYearDevelopment()!=null && this.getLastYearDevelopment().getChukouchuanghui()!=null){
			   if(chukouchuanghui!=null){
				   return nf.format(new BigDecimal(100).multiply(chukouchuanghui.divide(this.getLastYearDevelopment().getChukouchuanghui(),4,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(1))))+"%";
			   }else{
				   return "-100.0%";
			   }
		   }else{
			   return "100%";
		   }
	}
	public void setDifferchukouchuanghui(BigDecimal differchukouchuanghui) {
		this.differchukouchuanghui = differchukouchuanghui;
	}
	public static void main(String[] args) {
		NumberFormat nf = NumberFormat.getInstance();
        System.out.println(nf.format(0.000));
	}
}
