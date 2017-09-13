package com.fise.xw.DB.entity;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here

import android.text.TextUtils;
import android.util.Log;

import com.fise.xw.config.DBConstant;
import com.fise.xw.imservice.entity.SearchElement;
import com.fise.xw.utils.pinyin.PinYin.PinYinElement;
// KEEP INCLUDES END
/**
 * 卡片机的配置信息
 */
public class DeviceEntity {
	
	protected Long id; 
	protected int device_id ;
	protected int master_id ;
	protected int devType;
	protected String mobile ;  		//管理员号码
	protected int alr_battery ;     //电量报警
	protected int alr_poweroff ;   //关机报警
	protected int alr_call ;       //通话报警
	protected int mode ;           //工作模式
	protected int bell_mode ;      //响铃模式
	protected int updated ;        //更新时间
	protected int family_group_id ;//家庭群组ID
	protected String diff;    //设备之间区别 json
    
    public DeviceEntity() {
    	
    }

    public DeviceEntity(Long id) {
        this.id = id;
    }
    
 
	

    public DeviceEntity(Long id, int device_id, int master_id,int devType, String mobile, int  alr_battery, int alr_poweroff,
    						int alr_call,int mode,int bell_mode,int updated,int family_group_id,String diff) {
    	 this.id = id; 
    	 this.device_id = device_id;
    	 this.master_id = master_id;
    	 this.devType = devType;
    	 this.mobile = mobile;
    	 this.alr_battery = alr_battery;
    	 this.alr_poweroff = alr_poweroff;
    	 this.alr_call = alr_call;
    	 
    	 this.mode = mode;
    	 this.bell_mode = bell_mode;
    	 this.updated = updated;
    	 this.family_group_id = family_group_id;
    	 this.diff = diff;
    	  
    }
 
     
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) { 
        this.id = id;
    }
     
    public int getDeviceId() {
        return device_id;
    }

    public void setDeviceId(int device_id) {
        this.device_id = device_id;
    }
    
    public int getMasterId() {
        return master_id;
    }

    public void setMasterId(int master_id) {
        this.master_id = master_id;
    }
    
   
    public int getDevType() {
        return devType;
    }

    public void setDevType(int devType) {
        this.devType = devType;
    }
    
    
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    
    public int getAlrBattery() {
        return alr_battery;
    }

    public void setAlrBattery(int alr_battery) {
        this.alr_battery = alr_battery;
    }
    
     
    public int getAlrPoweroff() {
        return alr_poweroff;
    }

    public void setAlrPoweroff(int alr_poweroff) {
        this.alr_poweroff = alr_poweroff;
    }
    
    public int getAlrCall() {
        return alr_call;
    }

    public void setAlrCall(int alr_call) {
        this.alr_call = alr_call;
    }
    
     
    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
 
    public int getBellMode() {
        return bell_mode;
    }

    public void setBellMode(int bell_mode) {
        this.bell_mode = bell_mode;
    }
 
    
    public int getUpdated() {
        return updated;
    }

    public void setUpdated(int updated) {
        this.updated = updated;
    }
 
 
    public int getFamilyGroupId() {
        return family_group_id;
    }

    public void setFamilyGroupId(int family_group_id) {
        this.family_group_id = family_group_id;
    }
 
  
    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }
    
    
    // KEEP METHODS - put your custom methods here

 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeviceEntity)) return false;

        DeviceEntity entity = (DeviceEntity) o;

        if (device_id != entity.device_id) return false; 
        if (master_id != entity.master_id) return false; 
        if (devType != entity.devType) return false; 
        if (!mobile.equals(entity.mobile)) return false; 
        if (alr_battery != entity.alr_battery) return false;   
        if (alr_poweroff != entity.alr_poweroff) return false;  
        if (alr_call != entity.alr_call) return false;   
        
        if (mode != entity.mode) return false; 
        if (master_id != entity.master_id) return false; 
        if (bell_mode != entity.bell_mode) return false;  
        if (updated != entity.updated) return false;   
        if (family_group_id != entity.family_group_id) return false;  
        if (!diff.equals(entity.diff)) return false;  
         
    	 
        return true;
    }
 

    // KEEP METHODS END

}