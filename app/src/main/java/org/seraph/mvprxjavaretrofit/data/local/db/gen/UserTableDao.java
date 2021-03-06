package org.seraph.mvprxjavaretrofit.data.local.db.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import org.seraph.mvprxjavaretrofit.data.local.db.table.UserTable;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_TABLE".
*/
public class UserTableDao extends AbstractDao<UserTable, Long> {

    public static final String TABLENAME = "USER_TABLE";

    /**
     * Properties of entity UserTable.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_id");
        public final static Property Id = new Property(1, int.class, "id", false, "ID");
        public final static Property Token = new Property(2, String.class, "token", false, "TOKEN");
        public final static Property Name = new Property(3, String.class, "name", false, "NAME");
        public final static Property Gender = new Property(4, String.class, "gender", false, "GENDER");
        public final static Property HeadPortrait = new Property(5, String.class, "headPortrait", false, "HEAD_PORTRAIT");
    }

    private DaoSession daoSession;


    public UserTableDao(DaoConfig config) {
        super(config);
    }
    
    public UserTableDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_TABLE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: _id
                "\"ID\" INTEGER NOT NULL ," + // 1: id
                "\"TOKEN\" TEXT," + // 2: token
                "\"NAME\" TEXT," + // 3: name
                "\"GENDER\" TEXT," + // 4: gender
                "\"HEAD_PORTRAIT\" TEXT);"); // 5: headPortrait
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_TABLE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserTable entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
        stmt.bindLong(2, entity.getId());
 
        String token = entity.getToken();
        if (token != null) {
            stmt.bindString(3, token);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
 
        String gender = entity.getGender();
        if (gender != null) {
            stmt.bindString(5, gender);
        }
 
        String headPortrait = entity.getHeadPortrait();
        if (headPortrait != null) {
            stmt.bindString(6, headPortrait);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserTable entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
        stmt.bindLong(2, entity.getId());
 
        String token = entity.getToken();
        if (token != null) {
            stmt.bindString(3, token);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
 
        String gender = entity.getGender();
        if (gender != null) {
            stmt.bindString(5, gender);
        }
 
        String headPortrait = entity.getHeadPortrait();
        if (headPortrait != null) {
            stmt.bindString(6, headPortrait);
        }
    }

    @Override
    protected final void attachEntity(UserTable entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public UserTable readEntity(Cursor cursor, int offset) {
        UserTable entity = new UserTable( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
            cursor.getInt(offset + 1), // id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // token
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // name
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // gender
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // headPortrait
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserTable entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setId(cursor.getInt(offset + 1));
        entity.setToken(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setGender(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setHeadPortrait(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UserTable entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UserTable entity) {
        if(entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserTable entity) {
        return entity.get_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
