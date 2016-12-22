package com.zyt.web.publics.cluster;

import java.io.Serializable;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
/**
 * @ClassName:  DistributedSessionAttribute   
 * @Description:TODO   
 * @author: sunshine  
 * @date:   2016年11月10日 下午4:35:22
 */
public class DistributedSessionData implements Serializable{

	private static final long serialVersionUID = -3861048010555183175L;
	
	private Long creationTime;
	
    private Long lastAccessedTime;
    
    private boolean isNew = true;
    
	private Map<String,Serializable> map;
	
    public DistributedSessionData() {
        creationTime = System.currentTimeMillis();
        map = new HashMap<String, Serializable>();
    }
	
    public void setAttribute(String key, Serializable value) {
    	this.map.put(key,value);
    }
    
    public void removeAttribute(String key) {
    	this.map.remove(key);
    }
    
    public Enumeration<String> getAttributeNames() {
        return Collections.enumeration(this.map.keySet());
    }
    
    public Enumeration<Serializable> getAttributeValues() {
    	return Collections.enumeration(this.map.values());
    }

    public Object getAttribute(String key) {
        return this.map.get(key);
    }

    public void removeAllAttribute() {
        this.map.clear();
    }

	public Long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}

	public Long getLastAccessedTime() {
		return lastAccessedTime;
	}


	public void setLastAccessedTime(Long lastAccessedTime) {
		this.lastAccessedTime = lastAccessedTime;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
    
}