package com.app.jgconsultants.budgetmanager;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Anmol on 7/9/2015.
 */
public class BudgetManagerHelper extends SQLiteOpenHelper implements BaseColumns {
    private static final String DATABASE_NAME = "Budget";
    public static final String TABLE_NAME = "BudgetRevenueExpense";
    private static final int DATABASE_VERSION = 1;

    public static final String COLUMN_NAME_DATE = "date";
    public static final String COLUMN_NAME_CATEGORY = "category";
    public static final String COLUMN_NAME_ITEM = "item";
    public static final String COLUMN_NAME_LOCATION = "location";
    public static final String COLUMN_NAME_AMOUNT = "amount";
    public static final String COLUMN_NAME_AMOUNT_TYPE = "amount_type";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    BudgetManagerHelper._ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_DATE + " INT" + COMMA_SEP +
                    COLUMN_NAME_CATEGORY + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_ITEM + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_LOCATION + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_AMOUNT_TYPE + " INT" + COMMA_SEP +
                    COLUMN_NAME_AMOUNT + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES = (
            "DROP TABLE IF EXISTS " + TABLE_NAME
    );

    public BudgetManagerHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    // CRUD Operations

    void addFinanceItem(FinanceItem fi) {
        String putCategory = fi.getCategory();
        String putItem = fi.getItem();
        String putLocation = fi.getLocation();
        String putDouble = Double.toString(fi.getAmount());
        Boolean isRevenue = fi.isAddMoney();

        Integer addMoney;
        if (isRevenue) {
            addMoney = 1;
        }
        else {
            addMoney = 0;
        }

        Calendar date = Calendar.getInstance();
        date.set(fi.getYear(), fi.getMonth(), fi.getDay());
        Long putDate = date.getTimeInMillis();

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_ITEM, putItem);
        values.put(COLUMN_NAME_AMOUNT, putDouble);
        values.put(COLUMN_NAME_DATE, putDate);
        values.put(COLUMN_NAME_AMOUNT_TYPE, addMoney);
        values.put(COLUMN_NAME_CATEGORY, putCategory);
        values.put(COLUMN_NAME_LOCATION, putLocation);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<FinanceItem> getAllFinanceItem() {
        List<FinanceItem> fiList = new ArrayList<FinanceItem>();

        String selectAllQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectAllQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String strID = cursor.getString(0);
                Long date = cursor.getLong(1);
                String category = cursor.getString(2);
                String item = cursor.getString(3);
                String location = cursor.getString(4);
                int amountType = cursor.getInt(5);
                String strAmount = cursor.getString(6);

                Integer id = Integer.parseInt(strID);
                Calendar itemDate = Calendar.getInstance();
                itemDate.setTimeInMillis(date);
                Integer year = itemDate.get(Calendar.YEAR);
                Integer month = itemDate.get(Calendar.MONTH);
                Integer day = itemDate.get(Calendar.DAY_OF_YEAR);

                Boolean addMoney;

                if (amountType == 1) {
                    addMoney = true;
                }
                else {
                    addMoney = false;
                }

                Double amount = Double.parseDouble(strAmount);


                FinanceItem fi = new FinanceItem(id, category, location, item, year, month, day, amount, addMoney);
                fiList.add(fi);
            } while (cursor.moveToNext());
        }

        return fiList;

    }


}
