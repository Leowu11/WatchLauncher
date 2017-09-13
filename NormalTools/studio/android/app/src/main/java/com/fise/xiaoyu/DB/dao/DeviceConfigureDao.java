package com.fise.xiaoyu.DB.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.fise.xiaoyu.DB.entity.DeviceEntity;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * 设备请求信息
*/
  
public class DeviceConfigureDao extends AbstractDao<DeviceEntity, Long> {

    public static final String TABLENAME = "DeviceConfigureDao";
 
    /**
     * Properties of entity UserEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property DeviceId = new Property(1, int.class, "DeviceId", false, "DEVICE_ID"); 
        public final static Property MasterId = new Property(2, int.class, "MasterId", false, "MASTERID"); 
        public final static Property DevType = new Property(3, int.class, "DevType", false, "DEVTYPE"); 
        public final static Property Mobile = new Property(4, String.class, "Mobile", false, "MOBILE");
        public final static Property AlrBattery = new Property(5, int.class, "AlrBattery", false, "ALRBATTERY"); 
        public final static Property AlrPoweroff = new Property(6, int.class, "AlrPoweroff", false, "ALRPOWEROFF"); 
        public final static Property AlrCall = new Property(7, int.class, "AlrCall", false, "ALRCALL"); 
        public final static Property Mode = new Property(8, int.class, "Mode", false, "MODE");
        public final static Property BellMode = new Property(9, int.class, "BellMode", false, "BELLMODE"); 
        public final static Property Updated = new Property(10, int.class, "Updated", false, "UPDATED");  
        public final static Property FamilyGroupId = new Property(11, int.class, "FamilyGroupId", false, "FAMILYGROUPID");
        public final static Property Charge = new Property(12, int.class, "Charge", false, "CHARGE");

        public final static Property oldVersion = new Property(13, String.class, "OldVersion", false, "OLDVERSION");
        public final static Property newVersion = new Property(14, String.class, "NewVersion", false, "NEWVERSION");
        public final static Property updateInfo = new Property(15, String.class, "UpdateInfo", false, "UPDATEINFO");
        public final static Property updateUrl = new Property(16, String.class, "UpdateUrl", false, "UPDATEURL");

        public final static Property Diff = new Property(17, String.class, "Diff", false, "DIFF");
         
    };
   
	

    public DeviceConfigureDao(DaoConfig config) {
        super(config);
    }
    
    public DeviceConfigureDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'DeviceConfigureDao' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id 
                "'DEVICE_ID' INTEGER NOT NULL," + // 1: DeviceId
                "'MASTERID' INTEGER NOT NULL," + // 2: MasterId
                "'DEVTYPE' INTEGER NOT NULL," + // 3: DevType
                "'MOBILE' TEXT NOT NULL," + // 4: Mobile
                "'ALRBATTERY' INTEGER NOT NULL," + // 5: AlrBattery 
                "'ALRPOWEROFF' INTEGER NOT NULL," + // 6: AlrPoweroff 
                "'ALRCALL' INTEGER NOT NULL," + // 7: AlrCall 
                "'MODE' INTEGER NOT NULL," + // 8: Mode 
                "'BELLMODE' INTEGER NOT NULL," + // 9: BellMode
                "'UPDATED' INTEGER NOT NULL," + // 10: Updated
                "'FAMILYGROUPID' INTEGER NOT NULL," + // 11: FamilyGroupId
                "'CHARGE' INTEGER NOT NULL," + // 12: Charge
                "'OLDVERSION' TEXT NOT NULL," + // 13: OldVersion
                "'NEWVERSION' TEXT NOT NULL," + // 14: NewVersion
                "'UPDATEINFO' TEXT NOT NULL," + // 15: UpdateInfo
                "'UPDATEURL' TEXT NOT NULL," + // 16: UpdateUrl
        		"'DIFF' TEXT NOT NULL );");  // 17: Diff
        
        // Add Indexes 
        db.execSQL("CREATE INDEX " + constraint + "IDX_DeviceConfigureDao_DEVICE_ID ON DeviceConfigureDao" +
                " (DEVICE_ID);");
    }



     
    
    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'DeviceConfigureDao'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, DeviceEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getDeviceId());
        stmt.bindLong(3, entity.getMasterId());
        stmt.bindLong(4, entity.getDevType());
        stmt.bindString(5, entity.getMobile());
        stmt.bindLong(6, entity.getAlrBattery());
        stmt.bindLong(7, entity.getAlrPoweroff()); 
        stmt.bindLong(8, entity.getAlrCall()); 
        
        stmt.bindLong(9, entity.getMode());
        stmt.bindLong(10, entity.getBellMode());
        stmt.bindLong(11, entity.getUpdated());
        stmt.bindLong(12, entity.getFamilyGroupId());
        stmt.bindLong(13, entity.getCharge());

        stmt.bindString(14, entity.getOld_version());
        stmt.bindString(15, entity.getNew_version());
        stmt.bindString(16, entity.getUpdate_info());
        stmt.bindString(17, entity.getUpdate_url());
        stmt.bindString(18, entity.getDiff());


    }
    

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public DeviceEntity readEntity(Cursor cursor, int offset) {
    	DeviceEntity entity = new DeviceEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // device_id
            cursor.getInt(offset + 2), // master_id
            cursor.getInt(offset + 3), // devType
            cursor.getString(offset + 4), // mobile
            cursor.getInt(offset + 5), // alr_battery
            cursor.getInt(offset + 6),  // alr_poweroff
            cursor.getInt(offset + 7),  // alr_call
            cursor.getInt(offset + 8),  // mode
            cursor.getInt(offset + 9),  // bell_mode
            cursor.getInt(offset + 10),  // updated
            cursor.getInt(offset + 11),  // family_group_id
                cursor.getInt(offset + 12),  // Charge
                cursor.getString(offset + 13),  // OldVersion
                cursor.getString(offset + 14),  // NewVersion
                cursor.getString(offset + 15),  // UpdateInfo
                cursor.getString(offset + 16),  // UpdateUrl
            cursor.getString(offset + 17)  // diff
        );
         
        return entity;
    }



	
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, DeviceEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDeviceId(cursor.getInt(offset + 1));
        entity.setMasterId(cursor.getInt(offset + 2));
        entity.setDevType(cursor.getInt(offset + 3));
        entity.setMobile(cursor.getString(offset + 4));
        
        entity.setAlrBattery(cursor.getInt(offset + 5));
        entity.setAlrPoweroff(cursor.getInt(offset + 6)); 
        entity.setAlrCall(cursor.getInt(offset + 7)); 
        
        entity.setMode(cursor.getInt(offset + 8)); 
        entity.setBellMode(cursor.getInt(offset + 9)); 
        entity.setUpdated(cursor.getInt(offset + 10)); 
        entity.setFamilyGroupId(cursor.getInt(offset + 11));
        entity.setCharge(cursor.getInt(offset + 12));

        entity.setOld_version(cursor.getString(offset + 13));
        entity.setNew_version(cursor.getString(offset + 14));
        entity.setUpdate_info(cursor.getString(offset + 15));
        entity.setUpdate_url(cursor.getString(offset + 16));

        entity.setDiff(cursor.getString(offset + 17));
     }






    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(DeviceEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(DeviceEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
     
}