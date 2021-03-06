package com.fise.xiaoyu.imservice.entity;

import com.fise.xiaoyu.protobuf.helper.EntityChangeEngine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 未读消息
 *
 * 未读session实体，并未保存在DB中
 */
public class UnreadEntity {
    private String sessionKey;
    private int peerId;
    private int sessionType;
    private int unReadCnt;
    private int laststMsgId;
    private String latestMsgData;
    private boolean isForbidden = false;
    private boolean isDevMsg = false;
    private List<Integer> listMsgId = new ArrayList<Integer>();

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public int getPeerId() {
        return peerId;
    }

    public void setPeerId(int peerId) {
        this.peerId = peerId;
    }

    public int getSessionType() {
        return sessionType;
    }

    public void setSessionType(int sessionType) {
        this.sessionType = sessionType;
    }

    public int getUnReadCnt() {
        return unReadCnt;
    }

    public void setUnReadCnt(int unReadCnt) {
        this.unReadCnt = unReadCnt;
    }

    public int getLaststMsgId() {
        return laststMsgId;
    }

    public void setLaststMsgId(int laststMsgId) {
        this.laststMsgId = laststMsgId;
    }

    public String getLatestMsgData() {
        return latestMsgData;
    }

    public void setLatestMsgData(String latestMsgData) {
        this.latestMsgData = latestMsgData;
    }

    public boolean isForbidden() {
        return isForbidden;
    }

    public void setForbidden(boolean isForbidden) {
        this.isForbidden = isForbidden;
    }

    public List<Integer> getListMsgId() {
        return listMsgId;
    }

    public void setListMsgId(List<Integer> listMsgId) {
        this.listMsgId = listMsgId;
    }


    public void removList(int msgId) {

        Iterator<Integer> listIdIter = listMsgId.iterator();
        while (listIdIter.hasNext()) {
            int id = listIdIter.next();
            if (id == msgId)
                listIdIter.remove();//这里要使用Iterator的remove方法移除当前对象，如果使用List的remove方法，则同样会出现ConcurrentModificationException
        }
    }



    @Override
    public String toString() {
        return "UnreadEntity{" +
                "sessionKey='" + sessionKey + '\'' +
                ", peerId=" + peerId +
                ", sessionType=" + sessionType +
                ", unReadCnt=" + unReadCnt +
                ", laststMsgId=" + laststMsgId +
                ", latestMsgData='" + latestMsgData + '\'' +
                ", isForbidden=" + isForbidden +
                '}';
    }

    public String buildSessionKey(){
        if(sessionType <=0 || peerId <=0){
            throw new IllegalArgumentException(
                    "SessionEntity buildSessionKey error,cause by some params <=0");
        }
        sessionKey = EntityChangeEngine.getSessionKey(peerId,sessionType);
        return sessionKey;
    }

    public boolean isDevMsg() {
        return isDevMsg;
    }

    public void setDevMsg(boolean devMsg) {
        isDevMsg = devMsg;
    }
}
