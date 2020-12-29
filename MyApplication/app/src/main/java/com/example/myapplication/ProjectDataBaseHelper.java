package com.example.myapplication;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Date;

import static android.widget.Toast.LENGTH_LONG;

public class ProjectDataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "medical_app";
    ;

    public ProjectDataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    SQLiteDatabase db = this.getWritableDatabase();
    @Override
    public void onCreate(SQLiteDatabase db) {
        String studentrecord="create table LOGIN(email varchar(50) primary key, password varchar(10)) ";
        String studentpoc="create table POC(email varchar(50) primary key,p_g_mob_no varchar(10),p_g_email varchar(30),warden_email varchar(10),doctor_email varchar(30),foreign key (email) references LOGIN(email)) ";
        String medicalrecord= "create table MEDICAL_RECORD(email varchar(50) primary key,name varchar(15),age int,gender varchar(15),height int,weight int,foreign key (email) references LOGIN(email))";
        db.execSQL("create table PREVIOUS_RECORD(email varchar(50) ,timestamp varchar(15) primary key,disease varchar(1000),symptoms varchar(1000),foreign key (email) references LOGIN(email))");
        db.execSQL("CREATE TABLE mytable(email varchar(50) primary key,newimage blob,foreign key (email) references LOGIN(email))");
        db.execSQL(studentpoc);
        db.execSQL(studentrecord);
        db.execSQL(medicalrecord);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String student="DROP TABLE IF EXISTS LOGIN";
        String studentpoc="DROP TABLE IF EXISTS POC";
        String studentrecord="DROP TABLE IF EXISTS PREVIOUS_RECORD";
        String medicalrecord="DROP TABLE IF EXISTS MEDICAL_RECORD";
        db.execSQL(student);
        db.execSQL(studentpoc);
        db.execSQL(studentrecord);
        db.execSQL(medicalrecord);
        db.execSQL("DROP TABLE IF EXISTS mytable");
        onCreate(db);

    }
    public boolean addvalues(String EMAIL,String PASSWORD){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("EMAIL",EMAIL);
        contentValues.put("PASSWORD",PASSWORD);
        db.insert("LOGIN",null,contentValues);
        //db.close();
        return true;
    }

    public boolean register(String EMAIL ,String fullname,Integer age,String gender,Integer height,Integer weight ){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("EMAIL",EMAIL);
        contentValues.put("NAME",fullname);
        contentValues.put("AGE",age);
        contentValues.put("GENDER",gender);
        contentValues.put("HEIGHT",height);
        contentValues.put("WEIGHT",weight);
        db.insert("MEDICAL_RECORD",null,contentValues);
        //db.close();
        return true;
    }
    public boolean registerpoc(String EMAIL ,String p_g_mob_no,String p_g_email,String warden_email,String doctor_email ){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("EMAIL",EMAIL);
        contentValues.put("p_g_mob_no",p_g_mob_no);
        contentValues.put("p_g_email",p_g_email);
        contentValues.put("warden_email",warden_email);
        contentValues.put("doctor_email",doctor_email);
        db.insert("POC",null,contentValues);
        //db.close();
        return true;
    }

    public boolean checkUserExist(String username, String password){
        String[] columns = {"email"};
        String selection = "email=? and password=?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query("LOGIN", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();

        cursor.close();


        if(count > 0){
            return true;
        } else {
            return false;
        }
    }

    public Cursor fetchvalues(String un){
        SQLiteDatabase db=getWritableDatabase();
        Cursor crs = db.rawQuery("Select * from POC where email = ?", new String[]{un});
        return crs;
    }
    public boolean updatevalues(String un,String PNO,String PEMAIL,String DEMAIL,String WEMAIL){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("p_g_mob_no",PNO);
        contentValues.put("p_g_email",PEMAIL);
        contentValues.put("doctor_email",DEMAIL);
        contentValues.put("warden_email",WEMAIL);
        Cursor cursor = db.rawQuery("Select * from  POC where email = ?", new String[]{un});
        if (cursor.getCount() > 0) {
            long result = db.update("POC", contentValues, "email=?", new String[]{un});

        }
        return true;
    }

    public boolean updateprofile(String email, String ua, String uh, String uw) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("AGE",ua);
        contentValues.put("HEIGHT",uh);
        contentValues.put("WEIGHT",uw);
        Cursor cursor = db.rawQuery("Select * from  MEDICAL_RECORD where email = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            long result = db.update("MEDICAL_RECORD", contentValues, "email=?", new String[]{email});
        }
        return true;
    }

    public Cursor fetchprofile(String email) {
        SQLiteDatabase db=getWritableDatabase();
        Cursor crs = db.rawQuery("select age,height,weight from MEDICAL_RECORD where email = ?", new String[]{email});
        return crs;
    }
    public Cursor fetchprofile1(String email) {
        SQLiteDatabase db=getWritableDatabase();
        Cursor crs = db.rawQuery("select name,age,height,weight from MEDICAL_RECORD where email = ?", new String[]{email});
        return crs;
    }

    public Cursor fetchpass(String email) {
        SQLiteDatabase db=getWritableDatabase();
        Cursor crs = db.rawQuery("select password from LOGIN where email = ?", new String[]{email});
        return crs;
    }

    public Cursor fetchPOCEmails(String email) {
        SQLiteDatabase db=getWritableDatabase();
        Cursor crs = db.rawQuery("select p_g_email, warden_email, doctor_email from POC where email = ?", new String[]{email});
        return crs;
    }

    public Cursor fetchPOCNumber(String email) {
        SQLiteDatabase db=getWritableDatabase();
        Cursor crs = db.rawQuery("select p_g_mob_no from POC where email = ?", new String[]{email});
        return crs;
    }

    public void passupdate(String email,String re) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("password",re);
        Cursor cursor = db.rawQuery("Select * from  LOGIN where email = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            long result = db.update("LOGIN", contentValues, "email=?", new String[]{email});
        }

    }
    public boolean addData(String name,byte[] img)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", name);
        contentValues.put("newimage", img);
        long result = db.insert("mytable",null,contentValues);
        if (result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }
    public Cursor getdata(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor data = db.rawQuery("Select newimage from mytable where email=?",new String[]{name});
        return data;
    }

    public boolean updatevalues(String name,byte[] img){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("newimage", img);

        Cursor cursor = db.rawQuery("Select * from mytable  where email = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = db.update("mytable", contentValues, "email=?", new String[]{name});

        }
        else{
            addData(name,img);
        }
        return true;
    }

    public boolean previousrecordvalues(String EMAIL, String symptoms, String disease){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("EMAIL",EMAIL);

        String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
        contentValues.put("timestamp",currentDateTimeString);

        contentValues.put("symptoms",symptoms);
        contentValues.put("disease",disease);
        db.insert("PREVIOUS_RECORD",null,contentValues);
        //db.close();
        return true;
    }

    public Cursor fetchPreviousRecord(String email) {
        SQLiteDatabase db=getWritableDatabase();
        Cursor crs = db.rawQuery("Select * from PREVIOUS_RECORD where email = ?", new String[]{email});
        return crs;
    }

}