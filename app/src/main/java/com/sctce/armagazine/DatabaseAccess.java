package com.sctce.armagazine;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public List<MagPage> getMagData() {
        List<MagPage> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM MagDB", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new MagPage(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getBlob(3),cursor.getBlob(4),cursor.getString(5),cursor.getString(6)));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<MagPage> searchMagData(String s) {
        List<MagPage> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM MagDB where ArtEng like '%"+s+"%' or AuthName  like'%"+s+"%'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new MagPage(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getBlob(3),cursor.getBlob(4),cursor.getString(5),cursor.getString(6)));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<MagPage> searchMagDatabyId(int i) {
        List<MagPage> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM MagDB where id ="+i, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new MagPage(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getBlob(3),cursor.getBlob(4),cursor.getString(5),cursor.getString(6)));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}