package com.fise.xiaoyu.DB.entity;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here

// KEEP INCLUDES END
/**
 * 卡片机的配置信息
 */
public class DeviceEntity {

	protected Long id;
	protected int device_id = 0 ;
	protected int master_id = 0 ;
	protected int devType= 0 ;
	protected String mobile = "";  		//管理员号码
	protected int alr_battery = 0 ;     //电量报警
	protected int alr_poweroff = 0 ;   //关机报警
	protected int alr_call= 0  ;       //通话报警
	protected int mode= 0  ;           //工作模式
	protected int bell_mode = 0 ;      //响铃模式
	protected int updated = 0 ;        //更新时间
	protected int family_group_id= 0  ;//家庭群组ID

    protected String old_version = "";  		//固件老版本
    protected String new_version = "" ;  		//固件新版本
    protected String update_info = "";  		//固件信息
    protected String update_url = "";  		//下载连接

	protected String diff="";    //设备之间区别 json
    private int charge = 0;

    public DeviceEntity() {
    	
    }

    public DeviceEntity(Long id) {
        this.id = id;
    }

    public DeviceEntity(Long id, int device_id, int master_id,int devType, String mobile, int  alr_battery, int alr_poweroff,
    						int alr_call,int mode,int bell_mode,int updated,int family_group_id,int charge,
                        String old_version,String new_version,String update_info,String update_url,String diff ) {
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
        this.charge = charge;

        this.old_version = old_version;
        this.new_version = new_version;
        this.update_info = update_info;
        this.update_url = update_url;
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


    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    // KEEP METHODS - put your custom methods here


    public String getOld_version() {
        return old_version;
    }

    public void setOld_version(String old_version) {
        this.old_version = old_version;
    }



    public String getNew_version() {
        return new_version;
    }

    public void setNew_version(String new_version) {
        this.new_version = new_version;
    }


    public String getUpdate_info() {
        return update_info;
    }

    public void setUpdate_info(String update_info) {
        this.update_info = update_info;
    }


    public String getUpdate_url() {
        return update_url;
    }

    public void setUpdate_url(String update_url) {
        this.update_url = update_url;
    }


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

        if (!old_version.equals(entity.old_version)) return false;
        if (!new_version.equals(entity.new_version)) return false;
        if (!update_info.equals(entity.update_info)) return false;
        if (!update_url.equals(entity.update_url)) return false;
        return true;

    }
 

    // KEEP METHODS END

}
