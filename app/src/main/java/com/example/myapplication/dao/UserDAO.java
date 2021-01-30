package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.DBHelper;
import com.example.myapplication.Entity.User;

public class UserDAO {

    private DBHelper dbHelper;
    public UserDAO(Context context){
        this.dbHelper = new DBHelper(context);
    }

    public void add(User user){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());
        database.insert("USER", null, values);
        database.close();
    }

    /**
     *
     * @param email
     * @return null if not exist
     */
    public String queryPasswordByEmail(String email){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query("USER", null, "email=?", new String[]{email}, null, null, null);
        if(cursor.getCount()==0) return null;
        cursor.moveToFirst();
        String password = cursor.getString(1);
        database.close();
        cursor.close();
        return password;
    }

    public void update(User user){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
//        values.put("email", user.getEmail());
        values.put("password", user.getPassword());
        int rows = database.update("USER", values, "email=?",new String[]{user.getEmail()});
        Log.e("TAG", "affected row"+rows);
        database.close();
    }
}
