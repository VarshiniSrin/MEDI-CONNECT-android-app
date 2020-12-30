package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class POCupdate extends AppCompatActivity {
    ProjectDataBaseHelper myDb;
    EditText pemail,pnumber,demail,wemail;
    public String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pocupdate);

        //getSupportActionBar().hide();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Intent intent=getIntent();
        email=intent.getStringExtra("full_name");
        pnumber=findViewById(R.id.pn);
        pemail=findViewById(R.id.pm);
        demail=findViewById(R.id.dm);
        wemail=findViewById(R.id.wm);
        myDb = new ProjectDataBaseHelper(POCupdate.this);
        Cursor res1= myDb.fetchvalues(email);
        while(res1.moveToNext()){

            pnumber.setText(res1.getString(1));
            pemail.setText(res1.getString(2));
            demail.setText(res1.getString(3));
            wemail.setText(res1.getString(4));
        }

    }

    public void updatepoc(View view){
        myDb = new ProjectDataBaseHelper(POCupdate.this);
        String pn= String.valueOf(pnumber.getText());
        String pe= String.valueOf(pemail.getText());
        String de= String.valueOf(demail.getText());
        String we= String.valueOf(wemail.getText());
        if(TextUtils.isEmpty(pn)) {
            pnumber.setError("Please enter a phone number");
            return;
        }
        if(TextUtils.isEmpty(pn)) {
            pnumber.setError("Please enter a phone number");
            return;
        }
        if(TextUtils.isEmpty(pe)) {
            pemail.setError("Please enter a phone number");
            return;
        }
        if(TextUtils.isEmpty(de)) {
           demail.setError("Please enter a phone number");
            return;
        }
        if(TextUtils.isEmpty(we)) {
            wemail.setError("Please enter a phone number");
            return;
        }
        myDb.updatevalues(email,pn, pe, de,we);

        Toast.makeText(this, "saved successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),Firstpage.class));
    }
}