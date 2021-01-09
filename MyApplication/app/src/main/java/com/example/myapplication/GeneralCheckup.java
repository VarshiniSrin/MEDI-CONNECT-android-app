package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.Objects;

public class GeneralCheckup extends AppCompatActivity {

    CheckBox checkBox1, checkBox2,checkBox3,checkBox4,checkBox5,checkBox6,checkBox7,checkBox8,checkBox9,checkBox10;
    Button sendSMS, button2;

    ProjectDataBaseHelper myDb;
    public String email;
    int total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_checkup);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.General_Checkup);

        myDb = new ProjectDataBaseHelper(this);

        Intent intent=getIntent();
        email=intent.getStringExtra("full_name");
        total = intent.getIntExtra("steps",200);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){

            }
            else{
                requestPermissions(new String[]{Manifest.permission.SEND_SMS},1   );
            }
        }

        checkBox1 = (CheckBox)findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox)findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox)findViewById(R.id.checkBox4);
        checkBox5 = (CheckBox)findViewById(R.id.checkBox5);
        checkBox6 = (CheckBox)findViewById(R.id.checkBox6);
        checkBox7 = (CheckBox)findViewById(R.id.checkBox7);
        checkBox8 = (CheckBox)findViewById(R.id.checkBox8);
        checkBox9 = (CheckBox)findViewById(R.id.checkBox9);
        checkBox10 = (CheckBox)findViewById(R.id.checkBox10);

        sendSMS = (Button)findViewById(R.id.sendSMS);
        button2 = (Button)findViewById(R.id.button2);

        sendSMS.setOnClickListener(new View.OnClickListener() {

            String msg="";
            int count=0;

            @Override
            public void onClick(View v) {

                if(checkBox1.isChecked())
                {
                    count++;
                    msg+="  Common Cold";
                }

                if(checkBox2.isChecked())
                {
                    count++;
                    if(count>1)
                        msg+=", ";
                    msg+="  Ear infection";
                }

                if(checkBox3.isChecked())
                {
                    count++;
                    if(count>1)
                        msg+=", ";
                    msg+="  Flu(Influenza)";
                }

                if(checkBox4.isChecked())
                {
                    count++;
                    if(count>1)
                        msg+=", ";
                    msg+="  Sinus infection";
                }

                if(checkBox5.isChecked())
                {
                    count++;
                    if(count>1)
                        msg+=", ";
                    msg+="  Sore throat";
                }

                if(checkBox6.isChecked())
                {
                    count++;
                    if(count>1)
                        msg+=", ";
                    msg+="  Allergies";
                }

                if(checkBox7.isChecked())
                {
                    count++;
                    if(count>1)
                        msg+=", ";
                    msg+="  Conjuctivitis(pink eye)";
                }

                if(checkBox8.isChecked())
                {
                    count++;
                    if(count>1)
                        msg+=", ";
                    msg+="  Diarrhea";
                }

                if(checkBox9.isChecked())
                {
                    count++;
                    if(count>1)
                        msg+=", ";
                    msg+="  Migraine";
                }

                if(checkBox10.isChecked())
                {
                    count++;
                    if(count>1)
                        msg+=", ";
                    msg+="  Stomach aches";
                }

                System.out.println(msg);
                sendSMS(msg);
                myDb.previousrecordvalues(email,msg,null);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = (new Intent(getApplicationContext(), DiseasePrediction.class));
                i.putExtra("full", email);
                i.putExtra("steps",total);
                startActivity(i);
            }
        });
    }

    private void sendSMS(String message){
        String phno = null;

        Cursor res = myDb.fetchPOCNumber(email);
        while (res.moveToNext()) {
            phno = res.getString(0);
        }

        assert phno != null;
        phno = phno.replaceAll(" ","");

        try{
            SmsManager sm = SmsManager.getDefault();
            sm.sendTextMessage(phno,null,"Your ward may be suffering from " + message,null,null);
            Toast.makeText(this,"sms sent",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this,"sms not sent",Toast.LENGTH_SHORT).show();
        }

    }
}