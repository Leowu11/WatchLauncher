package com.fise.xw.imservice.manager;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.util.Log;

import com.fise.xw.imservice.callback.Packetlistener;
import com.fise.xw.protobuf.IMBaseDefine;
import com.fise.xw.protobuf.IMOther;
import com.fise.xw.protobuf.IMUserAction;
import com.fise.xw.ui.activity.MainActivity;
import com.fise.xw.utils.Logger;

/**
 * @author : yingmu on 15-3-26.
 * @email : yingmu@mogujie.com.
 *
 * 备注: 之前采用netty(3.6.6-fianl)支持通道检测IdleStateHandler,发现有些机型
 * 手机休眠之后IdleStateHandler 定时器HashedWheelTimer可能存在被系统停止关闭的现象
 * 所以采用AlarmManager 进行心跳的检测
 *
 * 登陆之后就开始触发心跳检测 【仅仅是在线，重练就会取消的】
 * 退出reset 会释放alarmManager 资源
 */
@SuppressLint("NewApi") public class IMHeartBeatManager  extends  IMManager{
    // 心跳检测4分钟检测一次，并且发送心跳包
    // 服务端自身存在通道检测，5分钟没有数据会主动断开通道

    private static IMHeartBeatManager inst = new IMHeartBeatManager();
    private static IMLoginManager loginManager = IMLoginManager.instance();
    public static IMHeartBeatManager instance() {
        return inst;
    }

    private Logger logger = Logger.getLogger(IMHeartBeatManager.class);
    private final int HEARTBEAT_INTERVAL =  4* 60 * 1000;//  
    private final String ACTION_SENDING_HEARTBEAT = "com.fise.xw.imservice.manager.imheartbeatmanager";
    private PendingIntent pendingIntent;
      
    @Override
    public void doOnStart() {
    	
    }

    // 登陆成功之后
    public void onloginNetSuccess(){
       // logger.e("heartbeat#onLocalNetOk");
    	 
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_SENDING_HEARTBEAT);
        logger.d("heartbeat#register actions");
        ctx.registerReceiver(imReceiver, intentFilter);
        //获取AlarmManager系统服务
        scheduleHeartbeat(HEARTBEAT_INTERVAL);
    }

    @Override
    public void reset() {
        logger.d("heartbeat#reset begin");
        try {
            ctx.unregisterReceiver(imReceiver);
            cancelHeartbeatTimer();
            logger.d("heartbeat#reset stop");
        }catch (Exception e){
            logger.e("heartbeat#reset error:%s",e.getCause());
        }
    }

    // MsgServerHandler 直接调用
    public void onMsgServerDisconn(){
        logger.w("heartbeat#onChannelDisconn");
        cancelHeartbeatTimer();
    }

    private void cancelHeartbeatTimer() {
        logger.w("heartbeat#cancelHeartbeatTimer");
        if (pendingIntent == null) {
            logger.w("heartbeat#pi is null");
            return;
        }
        AlarmManager am = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        am.cancel(pendingIntent);
    }


    private void scheduleHeartbeat(int seconds){
        logger.d("heartbeat#scheduleHeartbeat every %d seconds", seconds);
        if (pendingIntent == null) {
            logger.w("heartbeat#fill in pendingintent");
            Intent intent = new Intent(ACTION_SENDING_HEARTBEAT);
            pendingIntent = PendingIntent.getBroadcast(ctx, 0, intent, 0);
            if (pendingIntent == null) {
                logger.w("heartbeat#scheduleHeartbeat#pi is null");
                return;
            }
        }
  
        AlarmManager am = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);   
       am.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() , seconds, pendingIntent);
                
        //am.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + seconds, seconds, pendingIntent);
    } 
       

    /**--------------------boradcast-广播相关-----------------------------*/
    private BroadcastReceiver imReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            logger.w("heartbeat#im#receive action:%s", action);
            if (action.equals(ACTION_SENDING_HEARTBEAT)) {
                sendHeartBeatPacket();
            }
        }
    };

    
    //发送心跳包
    public void sendHeartBeatPacket(){
        logger.d("heartbeat#reqSendHeartbeat");
        PowerManager pm = (PowerManager) ctx.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "teamtalk_heartBeat_wakelock");
        wl.acquire();
        try {
            final long timeOut = 5*1000;
            
            IMOther.IMHeartBeat imHeartBeat = IMOther.IMHeartBeat.newBuilder()
                    .build();
            int sid = IMBaseDefine.ServiceID.SID_OTHER_VALUE;
            int cid = IMBaseDefine.OtherCmdID.CID_OTHER_HEARTBEAT_VALUE;
             
//            
            IMSocketManager.instance().sendRequest(imHeartBeat,sid,cid,new Packetlistener(timeOut) {
                @Override
                public void onSuccess(Object response) { 
                }

                @Override
                public void onFaild() { 
                    IMSocketManager.instance().onMsgServerDisconn();
                }

                @Override
                public void onTimeout() { 
                    IMSocketManager.instance().onMsgServerDisconn();
                }
            }); 
            
        } finally {
            wl.release(); 
        }
    }
}