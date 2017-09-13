package com.fise.xiaoyu.DB.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.fise.xiaoyu.DB.entity.GroupEntity;
import com.fise.xiaoyu.DB.entity.GroupNickEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * 普通群的表
*/

public class GroupNickDao extends AbstractDao<GroupNickEntity, Long> {

    public static final String TABLENAME = "GroupNickInfo";

    /**
     * Properties of entity GroupEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property GroupId = new Property(1, int.class, "groupId", false, "GROUP_ID");
        public final static Property UserId = new Property(2, int.class, "userId", false, "USERID");
        public final static Property Status = new Property(3, int.class, "status", false, "STATUS");
        public final static Property Nick = new Property(4, String.class, "nick", false, "NICK");
        public final static Property Created = new Property(5, int.class, "created", false, "CREATED");
        public final static Property Updated = new Property(6, int.class, "updated", false, "UPDATED");
        public final static Property Save = new Property(7, int.class, "Save", false, "SAVE");
       
         
    };


    public GroupNickDao(DaoConfig config) {
        super(config);
    }
    
    public GroupNickDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'GroupNickInfo' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'GROUP_ID' INTEGER NOT NULL UNIQUE ," + // 1: GROUP_ID
                "'USERID' INTEGER NOT NULL ," + // 2: USERID
                "'STATUS' INTEGER NOT NULL ," + // 3: STATUS
                "'NICK' TEXT NOT NULL ," + // 4: NICK
                "'CREATED' INTEGER NOT NULL ," + // 5: CREATED 
                "'UPDATED' INTEGER NOT NULL ," + // 6: save 
                "'SAVE' INTEGER NOT NULL );"); // 6: save
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'GroupNickInfo'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, GroupNickEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getGroupId());
        stmt.bindLong(3, entity.getUserId());
        stmt.bindLong(4, entity.getStatus());
        stmt.bindString(5, entity.getNick());
        stmt.bindLong(6, entity.getCreated());
        stmt.bindLong(7, entity.getUpdated());
        stmt.bindLong(8, entity.getSave());
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public GroupNickEntity readEntity(Cursor cursor, int offset) {
    	GroupNickEntity entity = new GroupNickEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // GroupId
            cursor.getInt(offset + 2), // UserId
            cursor.getInt(offset + 3), // Status
            cursor.getString(offset + 4), // Nick
            cursor.getInt(offset + 5), // Created
            cursor.getInt(offset + 6), // Updated
            cursor.getInt(offset + 7) // Updated
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, GroupNickEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setGroupId(cursor.getInt(offset + 1));
        entity.setUserId(cursor.getInt(offset + 2));
        entity.setStatus(cursor.getInt(offset + 3));
        entity.setNick(cursor.getString(offset + 4));
        entity.setCreated(cursor.getInt(offset + 5));
        entity.setUpdated(cursor.getInt(offset + 6));
        entity.setSave(cursor.getInt(offset + 7));
        
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(GroupNickEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(GroupNickEntity entity) {
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
