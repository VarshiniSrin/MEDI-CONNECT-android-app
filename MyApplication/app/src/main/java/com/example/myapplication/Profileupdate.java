package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class Profileupdate extends AppCompatActivity {
    ProjectDataBaseHelper myDb;
    EditText agetv,heighttv,weighttv,previous;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profileupdate);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        Intent intent=getIntent();
        email=intent.getStringExtra("full_name");
        agetv=findViewById(R.id.agetext);
        heighttv=findViewById(R.id.heighttext);
        weighttv=findViewById(R.id.weighttext);
        previous=findViewById(R.id.editTextTextMultiLine2);
        agetv.setEnabled(false);
        heighttv.setEnabled(false);
        weighttv.setEnabled(false);
        previous.setEnabled(false);
        Button b=findViewById(R.id.button);

        b.setVisibility(View.GONE);
        myDb = new ProjectDataBaseHelper(Profileupdate.this);
        Cursor res= myDb.fetchprofile(email);
        while(res.moveToNext()){
            agetv.setText(res.getString(0));
            heighttv.setText(res.getString(1));
            weighttv.setText(res.getString(2));
            previous.setText(res.getString(3));
        }

    }

    public void updatepoc(View view){

        String ua= String.valueOf(agetv.getText());
        String uh= String.valueOf(heighttv.getText());
        String uw= String.valueOf(weighttv.getText());
        myDb = new ProjectDataBaseHelper(this);
        if(TextUtils.isEmpty(ua)) {
            agetv.setError("Please enter a phone number");
            return;
        }
        if(TextUtils.isEmpty(uh)) {
            heighttv.setError("Please enter a phone number");
            return;
        }
        if(TextUtils.isEmpty(uw)) {
            weighttv.setError("Please enter a phone number");
            return;
        }
        myDb.updateprofile(email,ua,uh,uw,previous.getText().toString());
        Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),Firstpage.class));
    }

    public void edit(View view) {
        Button b1=findViewById(R.id.imagebutton);
        b1.setVisibility(View.GONE);
        agetv.setEnabled(true);
        heighttv.setEnabled(true);
        weighttv.setEnabled(true);
        previous.setEnabled(true);
        Button b=findViewById(R.id.button);
        b.setVisibility(View.VISIBLE);

    }
}