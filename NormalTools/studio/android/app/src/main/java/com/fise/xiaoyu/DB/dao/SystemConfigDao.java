package com.fise.xiaoyu.DB.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.fise.xiaoyu.DB.entity.SystemConfigEntity;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/*
 *  软件配置信息表
 */

public class SystemConfigDao extends AbstractDao<SystemConfigEntity, Long> {

    public static final String TABLENAME = "systemConfigDao";

    /**
     * Properties of entity GroupEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
    	public final static Property id = new Property(0, Long.class, "id", true, "_id"); 
        public final static Property comment_url = new Property(1, String.class, "comment_url", false, "COMMENT_URL");
        public final static Property launch = new Property(2, String.class, "launch", false, "LAUNCH");
        public final static Property launch_time = new Property(3, int.class, "launch_time", false, "LAUNCH_TIME");
        public final static Property system_notice = new Property(4, String.class, "system_notice", false, "SYSTEM_NOTICE");
        public final static Property website = new Property(5, String.class, "website", false, "WEBSITE");
        public final static Property version_support = new Property(6, String.class, "version_support", false, "VERSION_SUPPORT");
        public final static Property version = new Property(7, String.class, "version", false, "VSERSION");
        public final static Property launchAction = new Property(8, String.class, "launchAction", false, "LAUNCHACTION");
        public final static Property updateUrl = new Property(9, String.class, "updateUrl", false, "UPDATEURL");
        public final static Property versionComment = new Property(10, String.class, "versionComment", false, "VERSIONCOMMENT");
        public final static Property suggest_url = new Property(11, String.class, "suggest_url", false, "SUGGEST_URL");
        public final static Property mall_url = new Property(12, String.class, "mall_url", false, "MALL_URL");
    };


    public SystemConfigDao(DaoConfig config) {
        super(config);
    }
    
    public SystemConfigDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'systemConfigDao' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'COMMENT_URL' TEXT NOT NULL UNIQUE ," + // 1: COMMENT_URL
                "'LAUNCH' TEXT NOT NULL ," + // 2: _device_id
                "'LAUNCH_TIME' INTEGER NOT NULL ," + // 3: _action_type
                "'SYSTEM_NOTICE' TEXT NOT NULL ," + // 4: _action_value
                "'WEBSITE' TEXT NOT NULL ," + // 5: _action_param
                "'VERSION_SUPPORT' TEXT NOT NULL ," + // 6: _status
                "'VSERSION' TEXT NOT NULL ," + // 7: updated  
                "'LAUNCHACTION' TEXT NOT NULL ," + // 8: updated  
                "'UPDATEURL' TEXT NOT NULL ," + // 9: updateUrl
                "'VERSIONCOMMENT' TEXT NOT NULL ," +  //10versioncomment
                "'SUGGEST_URL' TEXT NOT NULL ," +  //11  suggest_url
                "'MALL_URL' TEXT NOT NULL);"); //  12 mall_url
        
        
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'systemConfigDao'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, SystemConfigEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getCommentUrl());
        stmt.bindString(3, entity.getLaunch());
        stmt.bindLong(4, entity.getLaunchTime());
        stmt.bindString(5, entity.getSystemNotice());
        stmt.bindString(6, entity.getWebsite());
        stmt.bindString(7, entity.getVersionSupport());
        stmt.bindString(8, entity.getVersion());
        stmt.bindString(9, entity.getLaunchAction());  
        stmt.bindString(10, entity.getUpdateUrl());  
        stmt.bindString(11, entity.getVersionComment());
        stmt.bindString(12, entity.getSuggestUrl());
        stmt.bindString(13, entity.getMallUrl());

    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public SystemConfigEntity readEntity(Cursor cursor, int offset) {
    	SystemConfigEntity entity = new SystemConfigEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // 
            cursor.getString(offset + 2), //  
            cursor.getInt(offset + 3), //  
            cursor.getString(offset + 4), //  
            cursor.getString(offset + 5), //  
            cursor.getString(offset + 6), //  
            cursor.getString(offset + 7), //  
            cursor.getString(offset + 8), //  
            cursor.getString(offset + 9), // version  
            cursor.getString(offset + 10), //
            cursor.getString(offset + 11), //
                 cursor.getString(offset + 12) //
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, SystemConfigEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        
        entity.setCommentUrl(cursor.getString(offset + 1));
        entity.setLaunch(cursor.getString(offset + 2));
        entity.setLaunchTime(cursor.getInt(offset + 3));
        entity.setSystemNotice(cursor.getString(offset + 4));
        entity.setWebsite(cursor.getString(offset + 5));
        entity.setVersionSupport(cursor.getString(offset + 6));
        entity.setVersion(cursor.getString(offset + 7));
        entity.setLaunchAction(cursor.getString(offset + 8)); 
        entity.setUpdateUrl(cursor.getString(offset + 9));  
        entity.SetVersionComment(cursor.getString(offset + 10));
        entity.setSuggestUrl(cursor.getString(offset + 11));
        entity.setMallUrl(cursor.getString(offset + 12));

    }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(SystemConfigEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }  
    
    /** @inheritdoc */
    @Override
    public Long getKey(SystemConfigEntity entity) {
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
