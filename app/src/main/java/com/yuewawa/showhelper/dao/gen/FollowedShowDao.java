package com.yuewawa.showhelper.dao.gen;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.yuewawa.showhelper.entity.Show;
import com.yuewawa.showhelper.entity.User;

import com.yuewawa.showhelper.entity.FollowedShow;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "FOLLOWED_SHOW".
*/
public class FollowedShowDao extends AbstractDao<FollowedShow, Long> {

    public static final String TABLENAME = "FOLLOWED_SHOW";

    /**
     * Properties of entity FollowedShow.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property UserId = new Property(1, Long.class, "userId", false, "USER_ID");
        public final static Property ShowId = new Property(2, Long.class, "showId", false, "SHOW_ID");
    }

    private DaoSession daoSession;

    private Query<FollowedShow> show_FollowedShowsQuery;
    private Query<FollowedShow> user_FollowedShowsQuery;

    public FollowedShowDao(DaoConfig config) {
        super(config);
    }
    
    public FollowedShowDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"FOLLOWED_SHOW\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"USER_ID\" INTEGER," + // 1: userId
                "\"SHOW_ID\" INTEGER);"); // 2: showId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"FOLLOWED_SHOW\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, FollowedShow entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(2, userId);
        }
 
        Long showId = entity.getShowId();
        if (showId != null) {
            stmt.bindLong(3, showId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, FollowedShow entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(2, userId);
        }
 
        Long showId = entity.getShowId();
        if (showId != null) {
            stmt.bindLong(3, showId);
        }
    }

    @Override
    protected final void attachEntity(FollowedShow entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public FollowedShow readEntity(Cursor cursor, int offset) {
        FollowedShow entity = new FollowedShow( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // userId
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2) // showId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, FollowedShow entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUserId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setShowId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(FollowedShow entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(FollowedShow entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(FollowedShow entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "followedShows" to-many relationship of Show. */
    public List<FollowedShow> _queryShow_FollowedShows(Long showId) {
        synchronized (this) {
            if (show_FollowedShowsQuery == null) {
                QueryBuilder<FollowedShow> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.ShowId.eq(null));
                show_FollowedShowsQuery = queryBuilder.build();
            }
        }
        Query<FollowedShow> query = show_FollowedShowsQuery.forCurrentThread();
        query.setParameter(0, showId);
        return query.list();
    }

    /** Internal query to resolve the "followedShows" to-many relationship of User. */
    public List<FollowedShow> _queryUser_FollowedShows(Long userId) {
        synchronized (this) {
            if (user_FollowedShowsQuery == null) {
                QueryBuilder<FollowedShow> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.UserId.eq(null));
                user_FollowedShowsQuery = queryBuilder.build();
            }
        }
        Query<FollowedShow> query = user_FollowedShowsQuery.forCurrentThread();
        query.setParameter(0, userId);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getUserDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getShowDao().getAllColumns());
            builder.append(" FROM FOLLOWED_SHOW T");
            builder.append(" LEFT JOIN USER T0 ON T.\"USER_ID\"=T0.\"_id\"");
            builder.append(" LEFT JOIN SHOW T1 ON T.\"SHOW_ID\"=T1.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected FollowedShow loadCurrentDeep(Cursor cursor, boolean lock) {
        FollowedShow entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        User user = loadCurrentOther(daoSession.getUserDao(), cursor, offset);
        entity.setUser(user);
        offset += daoSession.getUserDao().getAllColumns().length;

        Show show = loadCurrentOther(daoSession.getShowDao(), cursor, offset);
        entity.setShow(show);

        return entity;    
    }

    public FollowedShow loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<FollowedShow> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<FollowedShow> list = new ArrayList<FollowedShow>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<FollowedShow> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<FollowedShow> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
