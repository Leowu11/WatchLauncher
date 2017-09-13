package com.fise.xiaoyu.DB.entity;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here

import android.text.TextUtils;
import android.util.Log;

import com.fise.xiaoyu.config.DBConstant;
import com.fise.xiaoyu.imservice.entity.SearchElement;
import com.fise.xiaoyu.utils.pinyin.PinYin.PinYinElement;
// KEEP INCLUDES END


/**
 * 好友请求或为雨友请求 table角标统计
 */
public class ReqFriendsEntity {
 
	protected Long id; 
    protected int userId;  
    protected int tableReq;  
    protected int messReq;  
    
     
    public ReqFriendsEntity() {
    	
    }

    public ReqFriendsEntity(Long id) {
        this.id = id;
    }

    public ReqFriendsEntity(Long id,int userId,int tableReq,int messReq) {
    	 this.id = id;  
    	 this.userId = userId; 
    	 this.tableReq = tableReq; 
    	 this.messReq = messReq; 
    }
 
     
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) { 
        this.id = id;
    }
     
    
    public int getUserId() {
        return userId;
    }

    public void setUser(int userId) {
        this.userId = userId;
    }
    
    
    public int getTableReq() {
        return tableReq;
    }

    public void setTableReq(int tableReq) {
        this.tableReq = tableReq;
    }
  
    
    public int getMessageReq() {
        return messReq;
    }

    public void setMessageReq(int messReq) {
        this.messReq = messReq;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReqFriendsEntity)) return false;

        ReqFriendsEntity entity = (ReqFriendsEntity) o; 
        if (tableReq != entity.tableReq) return false; 
        if (messReq != entity.messReq) return false; 
        
        return true;
    }
  

}
