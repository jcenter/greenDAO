package de.greenrobot.daoexample;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import de.greenrobot.daoexample.Order;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ORDERS".
*/
public class OrderDao extends AbstractDao<Order, Long> {

    public static final String TABLENAME = "ORDERS";

    /**
     * Properties of entity Order.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Date = new Property(1, java.util.Date.class, "date", false, "DATE");
        public final static Property CustomerId = new Property(2, long.class, "customerId", false, "CUSTOMER_ID");
    };

    private DaoSession daoSession;

    private Query<Order> customer_OrdersQuery;

    public OrderDao(DaoConfig config) {
        super(config);
    }
    
    public OrderDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ORDERS\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"DATE\" INTEGER," + // 1: date
                "\"CUSTOMER_ID\" INTEGER NOT NULL );"); // 2: customerId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ORDERS\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Order entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        java.util.Date date = entity.getDate();
        if (date != null) {
            stmt.bindLong(2, date.getTime());
        }
        stmt.bindLong(3, entity.getCustomerId());
    }

    @Override
    protected void attachEntity(Order entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Order readEntity(Cursor cursor, int offset) {
        Order entity = new Order( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)), // date
            cursor.getLong(offset + 2) // customerId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Order entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDate(cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)));
        entity.setCustomerId(cursor.getLong(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Order entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Order entity) {
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
    
    /** Internal query to resolve the "orders" to-many relationship of Customer. */
    public List<Order> _queryCustomer_Orders(long customerId) {
        synchronized (this) {
            if (customer_OrdersQuery == null) {
                QueryBuilder<Order> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.CustomerId.eq(null));
                queryBuilder.orderRaw("T.'DATE' ASC");
                customer_OrdersQuery = queryBuilder.build();
            }
        }
        Query<Order> query = customer_OrdersQuery.forCurrentThread();
        query.setParameter(0, customerId);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getCustomerDao().getAllColumns());
            builder.append(" FROM ORDERS T");
            builder.append(" LEFT JOIN CUSTOMER T0 ON T.\"CUSTOMER_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Order loadCurrentDeep(Cursor cursor, boolean lock) {
        Order entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Customer customer = loadCurrentOther(daoSession.getCustomerDao(), cursor, offset);
         if(customer != null) {
            entity.setCustomer(customer);
        }

        return entity;    
    }

    public Order loadDeep(Long key) {
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
    public List<Order> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Order> list = new ArrayList<Order>(count);
        
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
    
    protected List<Order> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Order> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 


    public Order saveToDb(Order model){
        if(model == null){
            return;
        }
        Order dbModel = this.load(this.getKey(model));
        if(dbModel != null){
            dbModel.merge(model);
        }else{
            dbModel = model;
        }
        this.insertOrReplace(dbModel);
        return dbModel;
    }
}
