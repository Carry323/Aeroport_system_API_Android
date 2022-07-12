package com.example.finalprojectapi.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.finalprojectapi.model.User;

public class UserDAO implements IUserDAO{

    private SQLiteDatabase write;
    private SQLiteDatabase read;

    public UserDAO(Context context){
        SQLiteManager db = new SQLiteManager(context);
        write = db.getWritableDatabase();
        read = db.getReadableDatabase();
    }

    @Override
    public boolean save(User user) {

        try{
            ContentValues contentValues = new ContentValues();

            contentValues.put("firstName", user.getFirstName());
            contentValues.put("lastName", user.getLastName());
            contentValues.put("emailAddress", user.getEmailAddress());
            contentValues.put("password", user.getPassword());

            Long newId = write.insert(SQLiteManager.DB_TABLE_NAME, null, contentValues);
            user.setId(Integer.valueOf(Long.toString(newId)));
        }
        catch (Exception e){
            Log.i("UserDAO", "Error to save" + e.toString());
            return false;
        }

        return true;
    }


}
