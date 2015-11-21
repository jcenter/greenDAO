package de.greenrobot.daoexample;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import de.greenrobot.daoexample.Note;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NOTE".
*/
public class NoteDao extends AbstractDao<Note, Long> {

    public static final String TABLENAME = "NOTE";

    /**
     * Properties of entity Note.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Text = new Property(1, String.class, "text", false, "TEXT");
        public final static Property Comment = new Property(2, String.class, "comment", false, "COMMENT");
        public final static Property Date = new Property(3, java.util.Date.class, "date", false, "DATE");
        public final static Property Boo = new Property(4, Boolean.class, "boo", false, "BOO");
        public final static Property Bit = new Property(5, Byte.class, "bit", false, "BIT");
        public final static Property Dat = new Property(6, java.util.Date.class, "dat", false, "DAT");
        public final static Property Dou = new Property(7, Double.class, "dou", false, "DOU");
        public final static Property Flo = new Property(8, Float.class, "flo", false, "FLO");
        public final static Property Lon = new Property(9, Long.class, "lon", false, "LON");
        public final static Property In = new Property(10, Integer.class, "in", false, "IN");
    };

    private DaoSession daoSession;


    public NoteDao(DaoConfig config) {
        super(config);
    }
    
    public NoteDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NOTE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"TEXT\" TEXT NOT NULL ," + // 1: text
                "\"COMMENT\" TEXT," + // 2: comment
                "\"DATE\" INTEGER," + // 3: date
                "\"BOO\" INTEGER," + // 4: boo
                "\"BIT\" INTEGER," + // 5: bit
                "\"DAT\" INTEGER," + // 6: dat
                "\"DOU\" REAL," + // 7: dou
                "\"FLO\" REAL," + // 8: flo
                "\"LON\" INTEGER," + // 9: lon
                "\"IN\" INTEGER);"); // 10: in
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NOTE\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Note entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getText());
 
        String comment = entity.getComment();
        if (comment != null) {
            stmt.bindString(3, comment);
        }
 
        java.util.Date date = entity.getDate();
        if (date != null) {
            stmt.bindLong(4, date.getTime());
        }
 
        Boolean boo = entity.getBoo();
        if (boo != null) {
            stmt.bindLong(5, boo ? 1L: 0L);
        }
 
        Byte bit = entity.getBit();
        if (bit != null) {
            stmt.bindLong(6, bit);
        }
 
        java.util.Date dat = entity.getDat();
        if (dat != null) {
            stmt.bindLong(7, dat.getTime());
        }
 
        Double dou = entity.getDou();
        if (dou != null) {
            stmt.bindDouble(8, dou);
        }
 
        Float flo = entity.getFlo();
        if (flo != null) {
            stmt.bindDouble(9, flo);
        }
 
        Long lon = entity.getLon();
        if (lon != null) {
            stmt.bindLong(10, lon);
        }
 
        Integer in = entity.getIn();
        if (in != null) {
            stmt.bindLong(11, in);
        }
    }

    @Override
    protected void attachEntity(Note entity) {
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
    public Note readEntity(Cursor cursor, int offset) {
        Note entity = new Note( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // text
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // comment
            cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)), // date
            cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0, // boo
            cursor.isNull(offset + 5) ? null : (byte) cursor.getShort(offset + 5), // bit
            cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)), // dat
            cursor.isNull(offset + 7) ? null : cursor.getDouble(offset + 7), // dou
            cursor.isNull(offset + 8) ? null : cursor.getFloat(offset + 8), // flo
            cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9), // lon
            cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10) // in
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Note entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setText(cursor.getString(offset + 1));
        entity.setComment(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDate(cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)));
        entity.setBoo(cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0);
        entity.setBit(cursor.isNull(offset + 5) ? null : (byte) cursor.getShort(offset + 5));
        entity.setDat(cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)));
        entity.setDou(cursor.isNull(offset + 7) ? null : cursor.getDouble(offset + 7));
        entity.setFlo(cursor.isNull(offset + 8) ? null : cursor.getFloat(offset + 8));
        entity.setLon(cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9));
        entity.setIn(cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Note entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Note entity) {
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
    


    public Note saveToDb(Note model){
        if(model == null){
            return;
        }
        Note dbModel = this.load(this.getKey(model));
        if(dbModel != null){
            dbModel.merge(model);
        }else{
            dbModel = model;
        }
        this.insertOrReplace(dbModel);
        return dbModel;
    }
}
