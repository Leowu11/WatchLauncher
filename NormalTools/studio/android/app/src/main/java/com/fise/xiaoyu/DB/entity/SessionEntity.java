package com.fise.xiaoyu.DB.entity;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
import com.fise.xiaoyu.protobuf.helper.EntityChangeEngine;
// KEEP INCLUDES END



/**
 * Entity mapped to table Session.
 * 聊天会话窗口 信息
 */
public class SessionEntity {

    private Long id;
    /** Not-null value. */
    private String sessionKey;
    private int peerId;
    private int peerType;
    private int latestMsgType;
    private int latestMsgId;
    /** Not-null value. */
    private String latestMsgData;
    private int talkId;
    private int created;
    private int updated;
    
    private int mute_notification; //静音
    private int sticky_on_top; //置顶
    

     
    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public SessionEntity() {
    }

    public SessionEntity(Long id) {
        this.id = id;
    }

    public SessionEntity(Long id, String sessionKey, int peerId, int peerType, int latestMsgType, int latestMsgId, String latestMsgData,
    		int talkId, int created, int updated , int mute_notification, int sticky_on_top) { //
        this.id = id;
        this.sessionKey = sessionKey;
        this.peerId = peerId;
        this.peerType = peerType;
        this.latestMsgType = latestMsgType;
        this.latestMsgId = latestMsgId;
        this.latestMsgData = latestMsgData;
        this.talkId = talkId;
        this.created = created;
        this.updated = updated;
        
        
        this.mute_notification = mute_notification;
        this.sticky_on_top = sticky_on_top;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getSessionKey() {
        return sessionKey;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public int getPeerId() {
        return peerId;
    }

    public void setPeerId(int peerId) {
        this.peerId = peerId;
    }

    public int getPeerType() {
        return peerType;
    }

    public void setPeerType(int peerType) {
        this.peerType = peerType;
    }

    public int getLatestMsgType() { 
        return latestMsgType;
    }

    public void setLatestMsgType(int latestMsgType) {
        this.latestMsgType = latestMsgType;
    }

    public int getLatestMsgId() {
        return latestMsgId;
    }

    public void setLatestMsgId(int latestMsgId) {
        this.latestMsgId = latestMsgId;
    }

    /** Not-null value. */
    public String getLatestMsgData() {
        return latestMsgData;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setLatestMsgData(String latestMsgData) {
        this.latestMsgData = latestMsgData;
    }

    public int getTalkId() {
        return talkId;
    }

    public void setTalkId(int talkId) {
        this.talkId = talkId;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public int getUpdated() {
        return updated;
    }

    public void setUpdated(int updated) {
        this.updated = updated;
    }
    
    
    public int getMuteNotification() {
        return mute_notification;
    }

    public void setMuteNotification(int mute_notification) {
        this.mute_notification = mute_notification;
    }
    
    
    public int getStockOnTop() {
        return sticky_on_top;
    }

    public void setStockOnTop(int sticky_on_top) {
        this.sticky_on_top = sticky_on_top;
    }
    

    // KEEP METHODS - put your custom methods here
    public String buildSessionKey(){
        if(peerType <=0 || peerId <=0){
            throw new IllegalArgumentException(
                    "SessionEntity buildSessionKey error,cause by some params <=0");
        }
        sessionKey = EntityChangeEngine.getSessionKey(peerId, peerType);
        return sessionKey;
    }
    // KEEP METHODS END

}
