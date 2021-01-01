package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.util.Objects;


public class DiseasePrediction extends AppCompatActivity {

    ProjectDataBaseHelper myDb;
    public String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disease_prediction);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        myDb = new ProjectDataBaseHelper(this);

        Intent intent=getIntent();
        email=intent.getStringExtra("full_name");

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){

            }
            else{
                requestPermissions(new String[]{Manifest.permission.SEND_SMS},1   );
            }
        }



//checking if the ML algo and integration with Android studio works with a dummy UI
        if(!Python.isStarted())
            Python.start(new AndroidPlatform(this));

        Python py = Python.getInstance();
        final PyObject pyobj = py.getModule("script");

        final TextView textView1 = (TextView)findViewById(R.id.textView1);
        final Spinner s1 = (Spinner) findViewById(R.id.spinner1);
        final ImageButton image1 = (ImageButton)findViewById(R.id.image1);

        final TextView textView2 = (TextView)findViewById(R.id.textView2);
        final Spinner s2 = (Spinner) findViewById(R.id.spinner2);
        final ImageButton image2 = (ImageButton)findViewById(R.id.image2);

        final TextView textView3 = (TextView)findViewById(R.id.textView3);
        final Spinner s3 = (Spinner) findViewById(R.id.spinner3);
        final ImageButton image3 = (ImageButton)findViewById(R.id.image3);

        final TextView textView4 = (TextView)findViewById(R.id.textView4);
        final Spinner s4 = (Spinner) findViewById(R.id.spinner4);
        final ImageButton image4 = (ImageButton)findViewById(R.id.image4);

        final TextView textView5 = (TextView)findViewById(R.id.textView5);
        final Spinner s5 = (Spinner) findViewById(R.id.spinner5);



        Button button = (Button)findViewById(R.id.button);

        final EditText editText = (EditText)findViewById(R.id.editText1);


        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.setVisibility(View.VISIBLE);
                s2.setVisibility(View.VISIBLE);
                image2.setVisibility(View.VISIBLE);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView3.setVisibility(View.VISIBLE);
                s3.setVisibility(View.VISIBLE);
                image3.setVisibility(View.VISIBLE);
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView4.setVisibility(View.VISIBLE);
                s4.setVisibility(View.VISIBLE);
                image4.setVisibility(View.VISIBLE);
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView5.setVisibility(View.VISIBLE);
                s5.setVisibility(View.VISIBLE);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String symptom1 = s1.getSelectedItem().toString();
                final String symptom2 = s2.getSelectedItem().toString();
                final String symptom3 = s3.getSelectedItem().toString();
                final String symptom4 = s4.getSelectedItem().toString();
                final String symptom5 = s5.getSelectedItem().toString();

                PyObject obj = pyobj.callAttr("main", symptom1, symptom2, symptom3, symptom4, symptom5);
                editText.setText(obj.toString());

                sendSMS(obj.toString());
                senEmail(obj.toString());

                String symptoms = "";
                if(!symptom1.equals("Select"))
                    symptoms+=symptom1;
                if(!symptom2.equals("Select"))
                    symptoms+=(", " + symptom2);
                if(!symptom3.equals("Select"))
                    symptoms+=(", " + symptom3);
                if(!symptom4.equals("Select"))
                    symptoms+=(", " + symptom4);
                if(!symptom5.equals("Select"))
                    symptoms+=(", " + symptom5);

                myDb.previousrecordvalues(email,symptoms, obj.toString());

            }
        });


    }

    private void sendSMS(String message){
        String phno = null;

        Cursor res = myDb.fetchPOCNumber(email);
        while (res.moveToNext()) {
            phno = res.getString(0);
        }

        try{
            SmsManager sm = SmsManager.getDefault();
            sm.sendTextMessage(phno,null,message,null,null);
            Toast.makeText(this,"sms sent",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this,"sms not sent",Toast.LENGTH_SHORT).show();
        }

    }

    private void senEmail(String s) {

        String p_g_email = null, warden_email = null, doctor_email = null;
        Cursor res = myDb.fetchPOCEmails(email);

        while (res.moveToNext()) {
            p_g_email = res.getString(0);
            warden_email = res.getString(1);
            doctor_email = res.getString(2);
        }
        String mEmail = p_g_email+","+warden_email+","+doctor_email;
        System.out.println("---------------------------------------------------------------------");
        System.out.println(mEmail);

        String mSubject = "Diagnosis report";
        String mMessage = s;


        JavaMailAPI javaMailAPI = new JavaMailAPI(this, mEmail, mSubject, mMessage);

        javaMailAPI.execute();
        Toast.makeText(this, "message sent", Toast.LENGTH_SHORT).show();
        return;
    }

}
