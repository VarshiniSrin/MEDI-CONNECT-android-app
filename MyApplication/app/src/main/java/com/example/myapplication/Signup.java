package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {
    ProjectDataBaseHelper myDb;
    Button button;
    Button button1;
    EditText Email;
    EditText password;
    EditText Name;
    EditText age;
    EditText height;
    EditText weight;
    EditText p_phone;
    EditText p_email;
    EditText d_email;
    EditText w_email;
    Spinner gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up);
        myDb = new ProjectDataBaseHelper(this);
        String[] users = {"","Male", "Female", "Prefer not to say"};
         gender = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);

        //myDb = new ProjectDataBaseHelper(this);
        Name=(EditText)findViewById(R.id.editTextTextPersonName);
        Email=(EditText)findViewById(R.id.editTextTextEmailAddress);
        password=(EditText)findViewById(R.id.editTextTextPassword);
        age=(EditText)findViewById(R.id.editTextNumberSigned);
        height=(EditText)findViewById(R.id.editTextNumberSigned2);
        weight=(EditText)findViewById(R.id.editTextNumberSigned3);


      button =(Button)findViewById(R.id.buttonOK);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if ((Email.getText().toString().equals("")) || password.getText().toString().equals("") || Name.getText().toString().equals("") || age.getText().toString().equals("") || height.getText().toString().equals("") || weight.getText().toString().equals("") || gender.getSelectedItem().toString().equals("")) {
                    //Display Message
                    AlertDialog alertDialog = new AlertDialog.Builder(Signup.this).create();
                    alertDialog.setTitle("ALERT!");
                    alertDialog.setMessage("All fields must be filled");
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.show();
                }
                else {
                    setContentView(R.layout.pocregistration);
                    getSupportActionBar().hide();
                    p_phone=(EditText)findViewById(R.id.editTextPhone);
                    p_email=(EditText)findViewById(R.id.editTextTextEmailAddress2);
                    d_email=(EditText)findViewById(R.id.editTextTextEmailAddress3);
                    w_email=(EditText)findViewById(R.id.editTextTextEmailAddress4);
                    button1 = (Button) findViewById(R.id.button);
                    button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDb.register(Email.getText().toString(), Name.getText().toString(), Integer.parseInt(age.getText().toString()), gender.getSelectedItem().toString(), Integer.parseInt(height.getText().toString()), Integer.parseInt(weight.getText().toString()));
                            myDb.addvalues(Email.getText().toString(), password.getText().toString());
                            myDb.registerpoc(Email.getText().toString(), p_phone.getText().toString(), p_email.getText().toString(), w_email.getText().toString(), d_email.getText().toString());
                            Toast.makeText(Signup.this, "registration sucessfull", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(i);
                        }

                    });
                }
            }
    });
}
}

//Toast.makeText(Signup.this,"registration sucessfull",Toast.LENGTH_SHORT).show();
/*

 */