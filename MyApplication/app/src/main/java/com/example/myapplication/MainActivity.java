package com.example.myapplication;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    ProjectDataBaseHelper myDb;
    Button button;
    Button button1;
    EditText Email;
    EditText password;
    public static final String MyPREFERENCES = "MyPrefs" ;

    public static final String pass = "phoneKey";
    public static final String username = "emailKey";
    public static final String userlogin= "userlogin" ;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        boolean is = true;
        is=sharedpreferences.getBoolean(userlogin, false);
        if(is){
            Intent i=new Intent(getApplicationContext(),Firstpage.class);
            i.putExtra("full_name", sharedpreferences.getString(username,"error"));
            startActivity(i);
        }
        myDb = new ProjectDataBaseHelper(this);
        Email=(EditText)findViewById(R.id.Email);
        password=(EditText)findViewById(R.id.Password);
        button =(Button)findViewById(R.id.buttonSignIn);
        button1=(Button)findViewById(R.id.buttonSignUp);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Boolean validate;

                validate=myDb.checkUserExist(Email.getText().toString(),password.getText().toString());
                if(validate) {
                    Toast.makeText(MainActivity.this, "welcome", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(pass, password.getText().toString());
                    editor.putString(username,Email.getText().toString());
                    editor.putBoolean(userlogin, true);
                    editor.commit();
                    Intent i=new Intent(getApplicationContext(),Firstpage.class);
                    i.putExtra("full_name", Email.getText().toString());
                    startActivity(i);
                }
                else{
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("LOGIN FAILED");
                    alertDialog.setMessage("Incorrect email or password..! \n Try again!");
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.show(); }
                //myDb.close();
                //myDb.addvalues("prajwal]@gmail.com","prajwal");
            }
        });
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent = new Intent( getApplicationContext() ,Signup.class);
                startActivity(intent);
                //myDb.close();
                //myDb.addvalues("prajwal]@gmail.com","prajwal");
            }
        });
    }


}

/*
Okay. After that is done, type and press the Enter key: sfc /scannow
03:19 PM Raghavendra: ohh ok
03:19 PM Songo: After that, run this next command too: Get-AppxPackage Microsoft.Office | Remove-AppxPackage
03:19 PM Songo: If this last command is successful, then the Office app should be removed completely.
Run this last one still in PowerShell to refresh all your native Windows apps:
Get-AppXPackage -AllUsers | Foreach {Add-AppxPackage -DisableDevelopmentMode -Register “$($_.InstallLocation)\AppXManifest.xml”}

 */