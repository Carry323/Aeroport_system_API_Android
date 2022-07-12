package com.example.finalprojectapi.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SQLiteManager extends SQLiteOpenHelper {

    private static SQLiteManager sqLiteManager;

    public static int DB_VERSION = 1;
    public static String DB_NAME = "DB_AIRPORTS";
    public static String DB_TABLE_NAME = "USERS";
    public static String DB_TABLE_NAME_AIR = "AIRPORT";


    public SQLiteManager(@Nullable Context context) {
        super(context,
                DB_NAME,
                null,
                DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE IF NOT EXISTS " + DB_TABLE_NAME +
                "(" +

                "id  INTEGER PRIMARY KEY AUTOINCREMENT, " +


                "firstName VARCHAR(100) NOT NULL," +
                "lastName VARCHAR(50) NOT NULL," +
                "emailAddress VARCHAR(50) NOT NULL," +
                "password VARCHAR(50) NOT NULL" +
                ")";

        String sql2 = "CREATE TABLE IF NOT EXISTS " + DB_TABLE_NAME_AIR +
                "(" +

                "id  INTEGER PRIMARY KEY AUTOINCREMENT, " +


                "city VARCHAR(100) NOT NULL," +
                "name VARCHAR(50) NOT NULL," +
                "phone VARCHAR(50) NOT NULL" +

                ")";


        try{
            sqLiteDatabase.execSQL(sql);
            sqLiteDatabase.execSQL(sql2);
            Log.i("Database", "Table created");
        }
        catch(Exception e){
            Log.i("Database", "Table not created\n" + e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String sql = "DROP TABLE IF EXISTS " + DB_TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        String sql2 = "DROP TABLE IF EXISTS " + DB_TABLE_NAME_AIR;
        sqLiteDatabase.execSQL(sql2);
        this.onCreate(sqLiteDatabase);
    }

    public Boolean checkUsername (String emailAddress) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from USERS where emailAddress = ?", new String[] {emailAddress});
        if (cursor.getCount() > 0) {
            return true;
        }
        else
            return false;
    }


    public Boolean checkPassword (String emailAddress, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from USERS where emailAddress = ? and password = ?", new String[] {emailAddress, password});

        if (cursor.getCount() > 0) {
            return true;
        }
        else
            return false;
    }
}
