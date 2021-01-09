package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class Passwordupdate extends AppCompatActivity {
    ProjectDataBaseHelper myDb;
    EditText opswd,npswd,rpswd;
    String email;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String pass = "phoneKey";
    public static final String username = "emailKey";
    public static final String userlogin= "userlogin" ;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getSupportActionBar().hide();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.Password_Change);


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        Intent intent=getIntent();
        email=intent.getStringExtra("full_name");

        setContentView(R.layout.passwordupdate);
        opswd=findViewById(R.id.prevpswd);
        npswd=findViewById(R.id.newpswd);
        rpswd=findViewById(R.id.repswd);
        myDb = new ProjectDataBaseHelper(Passwordupdate.this);
        // rpswd.setEnabled(false);
    }
    public  void pswdupdate(View view) {
        // Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT).show();
        String s = String.valueOf(opswd.getText());
        Cursor res = myDb.fetchpass(email);
        res.moveToFirst();
        String s1 = res.getString(0);
        s1 = s1.replaceAll(" ", "");
        String new_pswd = String.valueOf(npswd.getText());
        String re_pswd = String.valueOf(rpswd.getText());
        if (TextUtils.isEmpty(s) || TextUtils.isEmpty(new_pswd) || TextUtils.isEmpty(re_pswd)) {
            Toast.makeText(this, "ALL FIELDS NEED TO BE FILLED", Toast.LENGTH_SHORT).show();
        } else {
            if (s.equals(s1)) {

                if (new_pswd.equals(re_pswd)) {
                    myDb.passupdate(email,re_pswd);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(pass, new_pswd.toString());
                    Toast.makeText(this, "saved successfully", Toast.LENGTH_SHORT).show();


                    Intent ui = new Intent(getApplicationContext(), Firstpage.class);
                    ui.putExtra("full_name", email);
                    startActivity(ui);

                }

                else {
                    npswd.setError("password mismatch");
                    rpswd.setError("password mismatch");
                }
            } else {
                // Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

                opswd.setError("old password incorrect");
            }

        }


    }


}