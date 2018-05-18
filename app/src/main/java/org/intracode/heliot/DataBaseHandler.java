package org.intracode.heliot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by August on 2018-04-21.
 */

public class DataBaseHandler extends SQLiteOpenHelper {
    public static final String DATABASE_WATERING = "watering.db";
    public static final String TABLE_WATERING = "watering_table";
    public static final String COL1 = "Ordernumber";
    public static final String COL2 = "Plant";
    public static final String COL3 = "WaterAmount";
    public static final String COL4 = "TimeHour";
    public static final String COL5 = "TimeMin";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_WATERING, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_WATERING + "   (Ordernumber INTEGER PRIMARY KEY AUTOINCREMENT, Plant INTEGER, WaterAmount INTEGER, TimeHour INTEGER, TimeMin INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_WATERING);
        onCreate(sqLiteDatabase);

    }

    public boolean insertData(String Plant, String WaterAmount, String TimeHour, String TimeMin) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, Plant);
        contentValues.put(COL3, WaterAmount);
        contentValues.put(COL4, TimeHour);
        contentValues.put(COL5, TimeMin);
        long result = sqLiteDatabase.insert(TABLE_WATERING, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

        public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_WATERING, null);
        return res;

    }

    public boolean updateData(String OrderNumber, String Plant, String WaterAmount, String TimeHour, String TimeMin){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, OrderNumber);
        contentValues.put(COL2, Plant);
        contentValues.put(COL3, WaterAmount);
        contentValues.put(COL4, TimeHour);
        contentValues.put(COL5, TimeMin);
        sqLiteDatabase.update(TABLE_WATERING, contentValues, "OrderNumber = ?", new String[] {OrderNumber});
        return true;

    }

    public Integer deleteData(String OrderNumber){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_WATERING, "OrderNumber = ?", new String[] {OrderNumber});
    }

}
