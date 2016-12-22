package com.zyt.web.publics.cluster;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.spring.MemcachedClientFactoryBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 分布式session
 * @ClassName:  DistributedSession   
 * @Description:TODO   
 * @author: sunshine  
 * @date:   2016年11月10日 下午2:33:23
 */
@SuppressWarnings("deprecation")
public class DistributedSession implements HttpSession,Serializable{

	private static final long serialVersionUID = 5088034124756528073L;
	
	private static final Logger logger = LoggerFactory.getLogger(DistributedSession.class);
	
	private String id;
	private int maxInactiveInterval = 30*60;//默认30分钟
	private HttpServletRequest localRequest;
	private String attribute_cache_key;
	private DistributedSessionData sessionData;
	private  static MemcachedClient memcachedClient=null;
	public DistributedSession(HttpServletRequest request, String sessionId, int	maxInactiveInterval) throws Exception{
		this.id = sessionId;
		this.localRequest=request;
		this.attribute_cache_key = this.id + "_attribute";
		if(maxInactiveInterval != 0){
			this.maxInactiveInterval = maxInactiveInterval;
		}
		sessionData = (DistributedSessionData)getCacheClient().get(this.attribute_cache_key);
		if(sessionData == null){
			sessionData = new DistributedSessionData();
		}else{
			if (sessionData.isNew()) {
				sessionData.setNew(false);
			}
		}
		sessionData.setLastAccessedTime(System.currentTimeMillis());
		syncCache();
		
	}
	@Override
	public long getCreationTime() {
		return getsessionData().getCreationTime();
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public long getLastAccessedTime() {
		return getsessionData().getLastAccessedTime();
	}

	@Override
	public ServletContext getServletContext() {
		return this.localRequest.getServletContext();
	}

	@Override
	public void setMaxInactiveInterval(int interval) {
		this.maxInactiveInterval=interval;
		
	}

	@Override
	public int getMaxInactiveInterval() {
		return this.maxInactiveInterval;
	}

	@Override
	@Deprecated
	public HttpSessionContext getSessionContext() {
		return this.localRequest.getSession().getSessionContext();
	}

	@Override
	public Object getAttribute(String name) {
		Object obj= this.localRequest.getSession().getAttribute(name);
		if(obj==null){
			try {
				obj=getsessionData().getAttribute(name);
			} catch (Exception e) 
			{
			}
		}
		return obj;
	}

	@Override
	public Object getValue(String name) {
		return getAttribute(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		Enumeration<String> em=this.localRequest.getSession().getAttributeNames();
		if(em==null){
			try {
			    em=getsessionData().getAttributeNames();
			   } catch (Exception e){
				   
			   }
		}
		return em;
	}

	@Override
	public String[] getValueNames() {
		List<String> list = new ArrayList<String>();
		Enumeration<String> enums = getAttributeNames();
		while (enums.hasMoreElements()){
			list.add(enums.nextElement());
		}
		return list.toArray(new String[list.size()]);
	}

	@Override
	public void setAttribute(String name, Object value) {
		this.localRequest.getSession().setAttribute(name, value);
		getsessionData().setAttribute(name,(Serializable)value);
		try{
		   syncCache();
		}catch(Exception e){
			logger.error("@sunshine:同步session数据异常",e);
		}
	}

	@Override
	public void putValue(String name, Object value) {
		setAttribute(name,value);
	}

	@Override
	public void removeAttribute(String name) {
		this.localRequest.getSession().removeAttribute(name);
		try {
		    getsessionData().removeAttribute(name);
		    syncCache();
		}catch(Exception e){
			
		}
	}

	@Override
	public void removeValue(String name) {
		   removeAttribute(name);
	}

	@Override
	public void invalidate(){
		this.localRequest.getSession().invalidate();
		try {
			clearCache();
		} catch (Exception e) {
			logger.error("@sunshine:注销session失败",e);
		}
	}

	@Override
	public boolean isNew() {
		return getsessionData().isNew();
	}

	public DistributedSessionData getsessionData() {
		return sessionData;
	}

	public void setsessionData(DistributedSessionData sessionData) {
		this.sessionData = sessionData;
	}
	
	public void syncCache() throws Exception{
		getCacheClient().add(this.attribute_cache_key,maxInactiveInterval,this.sessionData);
	}
	
	public void clearCache() throws Exception{
		getCacheClient().delete(this.attribute_cache_key);
	}
	
	public synchronized MemcachedClient  getCacheClient() throws Exception{
		if(memcachedClient!=null){
			return memcachedClient;
		}else{ 
			synchronized(DistributedSession.class){
				MemcachedClientFactoryBean factory = new MemcachedClientFactoryBean();
		        factory.setServers("sessionserver:60004");
		        factory.setOpTimeout(5000);
		        factory.setProtocol(ConnectionFactoryBuilder.Protocol.BINARY);
				memcachedClient = (MemcachedClient) factory.getObject();
			}
		}
		return  memcachedClient;
	}
}