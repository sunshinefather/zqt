package com.zyt.web.publics.module.agreement.bean;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;

/**
 * @Description:协议约定bean
 * @ClassName:  Agreement 
 * @author: sunshine  
 */
@Alias("Agreement")
public class Agreement implements Serializable {
	private static final long serialVersionUID = 1L;
	/**主键*/
	private String id;
	private String hospitalId;
	private String hospitalName;
	/**签订时间*/
	private String signedTime;
	/**签约主题*/
	private String signedBody;
	/**总投资*/
	private String invest;
	/**用地面积*/
	private String userArea;
	/**生产内容*/
	private String productionContent;
	/**动工时间*/
	private String dgDate;
	/**投产时间*/
	private String tcDate;
	/**特殊扶持*/
	private String specialSupport;
	private String str1;
	private String str2;
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
	public void setSignedTime( String signedTime ) {
		this.signedTime = signedTime;
	}
	public String getSignedTime(){
		return signedTime;
	}
	public void setSignedBody( String signedBody ) {
		this.signedBody = signedBody;
	}
	public String getSignedBody(){
		return signedBody;
	}
	public void setInvest( String invest ) {
		this.invest = invest;
	}
	public String getInvest(){
		return invest;
	}
	public void setUserArea( String userArea ) {
		this.userArea = userArea;
	}
	public String getUserArea(){
		return userArea;
	}
	public void setProductionContent( String productionContent ) {
		this.productionContent = productionContent;
	}
	public String getProductionContent(){
		return productionContent;
	}
	public void setDgDate( String dgDate ) {
		this.dgDate = dgDate;
	}
	public String getDgDate(){
		return dgDate;
	}
	public void setTcDate( String tcDate ) {
		this.tcDate = tcDate;
	}
	public String getTcDate(){
		return tcDate;
	}
	public void setSpecialSupport( String specialSupport ) {
		this.specialSupport = specialSupport;
	}
	public String getSpecialSupport(){
		return specialSupport;
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
