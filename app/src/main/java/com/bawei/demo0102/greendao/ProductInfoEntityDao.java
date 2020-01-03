package com.bawei.demo0102.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.bawei.demo0102.ProductInfoEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PRODUCT_INFO_ENTITY".
*/
public class ProductInfoEntityDao extends AbstractDao<ProductInfoEntity, Void> {

    public static final String TABLENAME = "PRODUCT_INFO_ENTITY";

    /**
     * Properties of entity ProductInfoEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property CommodityName = new Property(0, String.class, "commodityName", false, "COMMODITY_NAME");
        public final static Property MasterPic = new Property(1, String.class, "masterPic", false, "MASTER_PIC");
        public final static Property Price = new Property(2, int.class, "price", false, "PRICE");
    }


    public ProductInfoEntityDao(DaoConfig config) {
        super(config);
    }
    
    public ProductInfoEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PRODUCT_INFO_ENTITY\" (" + //
                "\"COMMODITY_NAME\" TEXT," + // 0: commodityName
                "\"MASTER_PIC\" TEXT," + // 1: masterPic
                "\"PRICE\" INTEGER NOT NULL );"); // 2: price
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PRODUCT_INFO_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ProductInfoEntity entity) {
        stmt.clearBindings();
 
        String commodityName = entity.getCommodityName();
        if (commodityName != null) {
            stmt.bindString(1, commodityName);
        }
 
        String masterPic = entity.getMasterPic();
        if (masterPic != null) {
            stmt.bindString(2, masterPic);
        }
        stmt.bindLong(3, entity.getPrice());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ProductInfoEntity entity) {
        stmt.clearBindings();
 
        String commodityName = entity.getCommodityName();
        if (commodityName != null) {
            stmt.bindString(1, commodityName);
        }
 
        String masterPic = entity.getMasterPic();
        if (masterPic != null) {
            stmt.bindString(2, masterPic);
        }
        stmt.bindLong(3, entity.getPrice());
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public ProductInfoEntity readEntity(Cursor cursor, int offset) {
        ProductInfoEntity entity = new ProductInfoEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // commodityName
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // masterPic
            cursor.getInt(offset + 2) // price
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ProductInfoEntity entity, int offset) {
        entity.setCommodityName(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setMasterPic(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPrice(cursor.getInt(offset + 2));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(ProductInfoEntity entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(ProductInfoEntity entity) {
        return null;
    }

    @Override
    public boolean hasKey(ProductInfoEntity entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}