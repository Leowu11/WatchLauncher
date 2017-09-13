package com.fise.xw.DB.entity;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here

import java.io.Serializable;
// KEEP INCLUDES END


/**
 * 电子围栏数据
 * @author weileiguan
 *
 */
public class ElectricFenceEntity implements Serializable{
 
	protected Long id; 
	int fence_id;
	int device_id;
	int status;
	double lng;
	double lat;
	int radius;
	String mark;
	int updated;
	int created;
    
    public ElectricFenceEntity() {
    	
    }

    public ElectricFenceEntity(Long id) {
        this.id = id;
    }

    public ElectricFenceEntity(Long id, int fence_id, int device_id, int status, 
    							double  lng, double lat,int radius,
    							String mark,int updated,int created) {
    	 this.id = id; 
    	 this.fence_id = fence_id;
    	 this.device_id = device_id;
    	 this.status = status;
    	 this.lng = lng; 
    	 this.lat = lat; 
    	 this.radius = radius; 
    	 this.mark = mark; 
    	 this.updated = updated; 
    	 this.created = created; 
    }
 
     
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) { 
        this.id = id;
    }
    
    
    
    public int getFenceId() {
        return fence_id;
    }

    public void setFenceId(int fence_id) {
        this.fence_id = fence_id;
    }
 
    
    public int getDeviceId() {
        return device_id;
    }

    public void setDeviceId(int device_id) {
        this.device_id = device_id;
    }
 
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
     
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
    
    
    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    
    

    public int getUpdated() {
        return updated;
    }

    public void setUpdated(int updated) {
        this.updated = updated;
    }

    
    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    } 
    
    
    
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    } 
    
    
    // KEEP METHODS - put your custom methods here

 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ElectricFenceEntity)) return false;

        ElectricFenceEntity entity = (ElectricFenceEntity) o;

        if (fence_id != entity.fence_id) return false;
        if (device_id != entity.device_id) return false;
        if (status != entity.status) return false;
        if (lng != entity.lng) return false;
        if (lat != entity.lat) return false;
        if (radius != entity.radius) return false;
        if (mark != entity.mark) return false;
        if (created != entity.created) return false; 
        if (updated != entity.updated) return false;  
         
         
        return true;
    }
 

    public  void setElectricFenceEntity(
			int FenceId,int DeviceId,int status,String Lng,
			String Lat,int radius,String Mark,int updated,int created){ 
		this.setFenceId(FenceId);  
		this.setDeviceId(DeviceId);  
		this.setStatus(status);  
		this.setLng(Double.valueOf(Lng));  
		this.setLat(Double.valueOf(Lat));  
		this.setRadius(radius);   
		this.setMark(Mark);  
		this.setUpdated(updated);  
		this.setCreated(created); 
 
} 

    // KEEP METHODS END

}
