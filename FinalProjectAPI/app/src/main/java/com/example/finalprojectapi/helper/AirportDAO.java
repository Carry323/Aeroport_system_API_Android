package com.example.finalprojectapi.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.finalprojectapi.model.Airport;

import java.util.ArrayList;
import java.util.List;

public class AirportDAO implements IAirportDAO{

    private SQLiteDatabase write;
    private SQLiteDatabase read;

    public AirportDAO(Context context){
        SQLiteManager db = new SQLiteManager(context);
        write = db.getWritableDatabase();
        read = db.getReadableDatabase();
    }

    @Override
    public boolean save(Airport airport) {

        try{
            ContentValues contentValues = new ContentValues();

            contentValues.put("city", airport.getCity());
            contentValues.put("name", airport.getNameAirport());
            contentValues.put("phone", airport.getPhone());


            Long newId = write.insert(SQLiteManager.DB_TABLE_NAME_AIR, null, contentValues);
            airport.setId(Integer.valueOf(Long.toString(newId)));
        }
        catch (Exception e){
            Log.i("AirportDAO", "Error to save" + e.toString());
            return false;
        }

        return true;
    }

    public boolean delete(int id) {
        try{
            String where = "id = ?";
            String[] args = {String.valueOf(id)};
            int rowAffected = write.delete(SQLiteManager.DB_TABLE_NAME_AIR, where, args);

            if (rowAffected > 0) {
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            Log.i("AirportDAO", "delete error" + e.toString());
        }

        return false;
    }


    @SuppressLint("Range")
    @Override
    public ArrayList<Airport> listAll() {
        ArrayList<Airport> airports = new ArrayList<>();
        try{
            String sql = "SELECT id, city, name, phone " +
                    "FROM " + SQLiteManager.DB_TABLE_NAME_AIR + ";";

            Cursor cursor = read.rawQuery(sql, null);

            while (cursor.moveToNext()){

                Airport airport = new Airport();

                airport.setId(cursor.getInt(cursor.getColumnIndex("id")));

                airport.setCity(cursor.getString(cursor.getColumnIndex("city")));
                airport.setNameAirport(cursor.getString(cursor.getColumnIndex("name")));
                airport.setPhone(cursor.getString(cursor.getColumnIndex("phone")));


                airports.add(airport);




            }


        } catch(Exception e){
            Log.i("AirportDAO", "Error to list all" + e.toString());
        }


        return airports;
    }
}
