package com.fise.xw.DB.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.fise.xw.DB.entity.ElectricFenceEntity;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table UserInfo.
*/
public class ElectricFenceDao extends AbstractDao<ElectricFenceEntity, Long> {

    public static final String TABLENAME = "ElectricFenceInfo";
 
    /**
     * Properties of entity UserEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "Id", true, "_id");
        public final static Property Fence_id = new Property(1, int.class, "Fence_id", false, "FENCE_ID");
        public final static Property Device_id = new Property(2, int.class, "Device_id", false, "DEVICE_ID");
        public final static Property Status = new Property(3, int.class, "Status", false, "STATUS");
        public final static Property Lng = new Property(4, long.class, "Lng", false, "LNG"); 
        public final static Property Lat = new Property(5, long.class, "Lat", false, "LAT"); 
        public final static Property Radius = new Property(6, long.class, "Radius", false, "RADIUS"); 
        public final static Property Mark = new Property(7, String.class, "Mark", false, "MARK"); 
        public final static Property Updated = new Property(8, int.class, "Updated", false, "UPDATED");
        public final static Property Created = new Property(9, int.class, "Created", false, "CREATED");
         
    };
 

    public ElectricFenceDao(DaoConfig config) {
        super(config);
    }
    
    public ElectricFenceDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'ElectricFenceInfo' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'FENCE_ID' INTEGER NOT NULL UNIQUE ," + // 1: fence_id
                "'DEVICE_ID' INTEGER NOT NULL ," + // 2: device_id
                "'STATUS' INTEGER NOT NULL ," + // 3: act_id
                "'LNG' DOUBLE NOT NULL ," + // 4: lng
                "'LAT' DOUBLE NOT NULL ," + // 5: lat 
                "'RADIUS' INTEGER NOT NULL ," + // 6: RADIUS 
                "'MARK' TEXT NOT NULL ," + // 7: MARK 
                "'UPDATED' INTEGER NOT NULL ," + // 8: UPDATED 
        		"'CREATED' INTEGER NOT NULL );");  // 9: CREATED
        // Add Indexes 
        db.execSQL("CREATE INDEX " + constraint + "IDX_ElectricFenceInfo_FENCE_ID ON ElectricFenceInfo" +
                " (FENCE_ID);"); 
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'ElectricFenceInfo'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, ElectricFenceEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getFenceId());
        stmt.bindLong(3, entity.getDeviceId()); 
        stmt.bindLong(4, entity.getStatus()); 
        stmt.bindDouble(5, entity.getLng()); 
        stmt.bindDouble(6, entity.getLat()); 
        stmt.bindLong(7, entity.getRadius()); 
        stmt.bindString(8, entity.getMark()); 
        stmt.bindLong(9, entity.getUpdated()); 
        stmt.bindLong(10, entity.getCreated()); 
        
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public ElectricFenceEntity readEntity(Cursor cursor, int offset) {
    	ElectricFenceEntity entity = new ElectricFenceEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // FenceId
            cursor.getInt(offset + 2), // DeviceId
            cursor.getInt(offset + 3), // Status
            cursor.getDouble(offset + 4), // Lng
            cursor.getDouble(offset + 5), // Lat
            cursor.getInt(offset + 6), // Radius 
            cursor.getString(offset + 7), // Mark 
            cursor.getInt(offset + 8), // Updated 
            cursor.getInt(offset + 9) // Created 
        );
         
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, ElectricFenceEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setFenceId(cursor.getInt(offset + 1)); 
        entity.setDeviceId(cursor.getInt(offset + 2)); 
        entity.setStatus(cursor.getInt(offset + 3)); 
        entity.setLng(cursor.getDouble(offset + 4)); 
        entity.setLat(cursor.getDouble(offset + 5)); 
        entity.setRadius(cursor.getInt(offset + 6));  
        entity.setMark(cursor.getString(offset + 7));
        entity.setUpdated(cursor.getInt(offset + 8)); 
        entity.setCreated(cursor.getInt(offset + 9)); 
        
     }
     
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(ElectricFenceEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(ElectricFenceEntity entity) {
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
