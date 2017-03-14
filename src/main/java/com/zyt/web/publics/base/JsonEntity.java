package com.zyt.web.publics.base;

import java.io.Serializable;
/**
 * 通用Controller json数据返回
 * @ClassName:  JsonEntity   
 * @Description:   
 * @author: sunshine  
 * @date:   2014年3月28日 上午11:26:44
 */
public class JsonEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
    public static final String success="1";
    public static final String fail="0";
    public static final String paramerror="401";//参数错误
    public static final String fmterror="400";//参数格式不正确
	private String status=success;//成功失败状态码

	private String msg;//校验失败原因
	private Object data;//传递返回回调数据

	private int code;//影响数据条数,用于判断是否修改、插入或删除是否成功
	
	private Integer totals;//总记录数
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
		if(code>0){
			this.status = success;
		}else{
			this.status = fail;
		}
	}

	public Integer getTotals() {
		return totals;
	}

	public void setTotals(Integer totals) {
		this.totals = totals;
	}

	/**
	 * 执行成功
	 * @Title:  JsonEntity   
	 * @Description:    
	 * @param:    
	 * @throws
	 */
	public JsonEntity() {
		super();
	}
	/**
	 * 失败
	 * @Title:  JsonEntity   
	 * @Description:    
	 * @param:  @param msg  错误提示
	 * @throws
	 */
	public JsonEntity(String msg) {
		super();
		this.status = fail;
		this.msg = msg;
	}
	/**
	 * 执行成功,并返回数据
	 * @Title:  JsonEntity   
	 * @Description:    
	 * @param:  @param entity  业务实体数据
	 * @throws
	 */
	public JsonEntity(Serializable data) {
		super();
		this.data = data;
	}
	/**
	 * 自定义返回
	 * @Title:  JsonEntity   
	 * @Description:    
	 * @param:  @param status 返回状态码
	 * @param:  @param msg 返回提示消息
	 * @param:  @param entity  业务实体数据
	 * @throws
	 */
	JsonEntity(String status, String msg, Serializable data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	public JsonEntity(int code) {
		super();
        setCode(code);
	}
}