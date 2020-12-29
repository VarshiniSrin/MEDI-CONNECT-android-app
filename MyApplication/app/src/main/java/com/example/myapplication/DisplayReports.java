package com.example.myapplication;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DisplayReports extends AppCompatActivity {
    static ArrayList<Items_DisplayReports> items;
    ProjectDataBaseHelper myDb;
    Button getTotal;
    TextView result, text;
    ListView lv;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_reports);

        text = (TextView)findViewById(R.id.text);
        Intent intent=getIntent();
        email=intent.getStringExtra("full_name");

        items = new ArrayList<Items_DisplayReports>();

        myDb = new ProjectDataBaseHelper(DisplayReports.this);
        Cursor res= myDb.fetchPreviousRecord(email);

        while(res.moveToNext()){
                items.add(new Items_DisplayReports(res.getString(1),res.getString(2),res.getString(3)));
        }

        lv=(ListView)findViewById(R.id.lv);
        CustomAdapter adapter = new CustomAdapter(this,items);
        lv.setAdapter(adapter);

    }
}