package com.fise.xiaoyu.imservice.entity;

import com.fise.xiaoyu.DB.entity.MessageEntity;
import com.fise.xiaoyu.DB.entity.PeerEntity;
import com.fise.xiaoyu.DB.entity.UserEntity;
import com.fise.xiaoyu.Security;
import com.fise.xiaoyu.config.DBConstant;
import com.fise.xiaoyu.config.MessageConstant;
import com.fise.xiaoyu.imservice.support.SequenceNumberMaker;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * 文本消息
 */
public class TextMessage extends MessageEntity implements Serializable {

     public TextMessage(){
         msgId = SequenceNumberMaker.getInstance().makelocalUniqueMsgId();
     }

     private TextMessage(MessageEntity entity){
         /**父类的id*/
         id =  entity.getId();
         msgId  = entity.getMsgId();
         fromId = entity.getFromId();
         toId   = entity.getToId();
         sessionKey = entity.getSessionKey();
         content=entity.getContent();
         msgType=entity.getMsgType();
         displayType=entity.getDisplayType();
         status = entity.getStatus();
         created = entity.getCreated();
         updated = entity.getUpdated(); 
          
     }

     public static TextMessage parseFromNet(MessageEntity entity){
         TextMessage textMessage = new TextMessage(entity);
         textMessage.setStatus(MessageConstant.MSG_SUCCESS);
         textMessage.setDisplayType(DBConstant.SHOW_ORIGIN_TEXT_TYPE);
         return textMessage;
     }
      
 
     
    public static TextMessage parseFromDB(MessageEntity entity){
        if(entity.getDisplayType()!=DBConstant.SHOW_ORIGIN_TEXT_TYPE){
            throw new RuntimeException("#TextMessage# parseFromDB,not SHOW_ORIGIN_TEXT_TYPE");
        }
        TextMessage textMessage = new TextMessage(entity);
        return textMessage;
    }
    
    public static TextMessage parseFromNoteDB(MessageEntity entity){
      
        TextMessage textMessage = new TextMessage(entity);
        return textMessage;
    }

    public static TextMessage buildForSend(String content,UserEntity fromUser,PeerEntity peerEntity){
        TextMessage textMessage = new TextMessage();
        int nowTime = (int) (System.currentTimeMillis() / 1000);
        textMessage.setFromId(fromUser.getPeerId()); 
        textMessage.setToId(peerEntity.getPeerId());
        textMessage.setUpdated(nowTime);
        textMessage.setCreated(nowTime);
        textMessage.setDisplayType(DBConstant.SHOW_ORIGIN_TEXT_TYPE);
        textMessage.setGIfEmo(true);
        int peerType = peerEntity.getType();
        int msgType = peerType == DBConstant.SESSION_TYPE_GROUP ? DBConstant.MSG_TYPE_GROUP_TEXT
                : DBConstant.MSG_TYPE_SINGLE_TEXT;
        textMessage.setMsgType(msgType);
        textMessage.setStatus(MessageConstant.MSG_SENDING);
        textMessage.setMessageTime("");
        // 内容的设定
        textMessage.setContent(content);
        textMessage.buildSessionKey(true);
        textMessage.setMessageTime(""+nowTime);
        
         
        return textMessage;
    }
    
     
    public static TextMessage buildForSend(String content,UserEntity fromUser,UserEntity peerEntity,int peeId){
        TextMessage textMessage = new TextMessage();
        int nowTime = (int) (System.currentTimeMillis() / 1000);
        textMessage.setFromId(fromUser.getPeerId());
        textMessage.setToId(peerEntity.getPeerId());
        textMessage.setUpdated(nowTime);
        textMessage.setCreated(nowTime);
        textMessage.setDisplayType(DBConstant.SHOW_ORIGIN_TEXT_TYPE);
        textMessage.setGIfEmo(true);
        int peerType = peerEntity.getType();
//        int msgType = peerType == DBConstant.SESSION_TYPE_GROUP ? DBConstant.MSG_TYPE_GROUP_TEXT
//                : DBConstant.MSG_TYPE_SINGLE_TEXT;
        int msgType = DBConstant.MSG_TYPE_SINGLE_TEXT;
        textMessage.setMsgType(msgType);
        textMessage.setStatus(MessageConstant.MSG_SENDING);
        // 内容的设定
        textMessage.setContent(content);
        textMessage.buildSessionKey(true);
        textMessage.setMessageTime(""+nowTime);
        
         
        return textMessage; 
    }
     

    /**
     * Not-null value.
     * DB的时候需要
     */
    @Override
    public String getContent() {
        return content;
    }

    @Override
    public byte[] getSendContent() {
        try {
            /** 加密*/
            String sendContent =new String(Security.getInstance().EncryptMsg(content));
          //  String  base64Token = Base64.encodeToString(sendContent.getBytes(), Base64.DEFAULT);//  编码后
            return sendContent.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
