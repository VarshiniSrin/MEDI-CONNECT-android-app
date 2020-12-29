package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Healthapp.db";
    ;

    public DBhelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    SQLiteDatabase db = this.getWritableDatabase();
    @Override
    public void onCreate(SQLiteDatabase db) {
        String studentpoc="CREATE TABLE POC(usn text PRIMARY KEY  , p_g_mob_no text, p_g_email text , doctor_email text , warden_email text) ";
      //p_g_mob_no

        db.execSQL(studentpoc);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String studentpoc="DROP TABLE IF EXISTS POC";

        db.execSQL(studentpoc);

        onCreate(db);

    }
    public boolean addvalues(String USN,String PNO,String PEMAIL,String DEMAIL,String WEMAIL){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("usn",USN);
        contentValues.put("p_g_mob_no",PNO);
        contentValues.put("p_g_email",PEMAIL);
        contentValues.put("doctor_email",DEMAIL);
        contentValues.put("warden_email",WEMAIL);


        db.insert("POC",null,contentValues);
        //db.close();
        return true;
    }
    public Cursor fetchvalues(String un){
        SQLiteDatabase db=getWritableDatabase();
        Cursor crs = db.rawQuery("select * from POC where usn = ?", new String[]{un});
        return crs;
    }
    public boolean updatevalues(String un,String PNO,String PEMAIL,String DEMAIL,String WEMAIL){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("p_g_mob_no",PNO);
        contentValues.put("p_g_email",PEMAIL);
        contentValues.put("doctor_email",DEMAIL);
        contentValues.put("warden_email",WEMAIL);
        Cursor cursor = db.rawQuery("Select * from  POC where usn = ?", new String[]{un});
        if (cursor.getCount() > 0) {
            long result = db.update("POC", contentValues, "usn=?", new String[]{un});

        }
        return true;
    }
}