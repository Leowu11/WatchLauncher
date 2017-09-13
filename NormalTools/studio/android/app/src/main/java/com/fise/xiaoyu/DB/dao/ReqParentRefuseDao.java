package com.fise.xiaoyu.DB.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.fise.xiaoyu.DB.entity.ReqFriendsEntity;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * 管理员同意或拒绝角标 table角 表
*/
public class ReqParentRefuseDao extends AbstractDao<ReqFriendsEntity, Long> {

    public static final String TABLENAME = "ReqParentRefuseDao";

    /**
     * Properties of entity UserEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property user_id = new Property(1, int.class, "user_id", false, "USER_ID");
        public final static Property table_req = new Property(2, int.class, "table_req", false, "TABLE_REQ");
        public final static Property message_req = new Property(3, int.class, "message_req", false, "MESS_REQ");

    };


    public ReqParentRefuseDao(DaoConfig config) {
        super(config);
    }

    public ReqParentRefuseDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }
    

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'ReqParentRefuseDao' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'USER_ID' INTEGER NOT NULL UNIQUE ," + // 1: USER_ID
                "'TABLE_REQ' INTEGER NOT NULL ," + // 2: TABLE_REQ 
        		"'MESS_REQ'  INTEGER NOT NULL );");  // 3: MESS_REQ
        // Add Indexes 
        db.execSQL("CREATE INDEX " + constraint + "IDX_ReqParentRefuseDao_USER_ID ON ReqParentRefuseDao" +
                " (USER_ID);");
    }
     


    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'ReqParentRefuseDao'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, ReqFriendsEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getUserId());
        stmt.bindLong(3, entity.getTableReq()); 
        stmt.bindLong(4, entity.getMessageReq());  
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public ReqFriendsEntity readEntity(Cursor cursor, int offset) {
    	ReqFriendsEntity entity = new ReqFriendsEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // FromId
            cursor.getInt(offset + 2), // ToId
            cursor.getInt(offset + 3) // ActId 
        );
         
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, ReqFriendsEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUser(cursor.getInt(offset + 1)); 
        entity.setTableReq(cursor.getInt(offset + 2)); 
        entity.setMessageReq(cursor.getInt(offset + 3));  
        
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(ReqFriendsEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(ReqFriendsEntity entity) {
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
