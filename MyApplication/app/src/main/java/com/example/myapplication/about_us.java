package com.example.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;


public class about_us extends AppCompatActivity {

    private ImageButton imageButton;
    Button emailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.About_Us);

        final GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                switch (position) {
                    case 0:
                        openBrowser();
                        break;
                    case 1:
                        openEmail();
                        break;
                    case 2:
                        openInsta();
                        break;
                    case 3:
                        openMaps();
                        break;
                    default:
                        break;
                }
            }
        });

    }

//SENDING EMAIL
    public void openEmail() {
        startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:example@gmail.com")));
    }

//OPENING WEBSITE
    public void openBrowser() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.naturalreaders.com/online/"));
        startActivity(browserIntent);
    }

//OPENING INSTA
    public void openInsta() {
        Uri uri = Uri.parse("https://www.instagram.com/dyslexia_in_adults/");
        Intent insta = new Intent(Intent.ACTION_VIEW, uri);
        insta.setPackage("com.instagram.android");
        try{
            startActivity(insta);
        }
        catch (ActivityNotFoundException e){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/dyslexia_in_adults/")));
        }

    }

//OPENING MAPS
public void openMaps() {
    Uri gmmIntentUri = Uri.parse("geo:0,0?z=4&q=current+location");
    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
    mapIntent.setPackage("com.google.android.apps.maps");
    startActivity(mapIntent);
}

}
