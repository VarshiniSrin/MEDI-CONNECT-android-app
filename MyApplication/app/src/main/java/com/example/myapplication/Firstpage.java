package com.example.myapplication;
// Import the required libraries

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;
import android.app.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import android.widget.Button;

import android.widget.EditText;

import android.widget.ImageView;

import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;


public class Firstpage extends AppCompatActivity {
    PieChartView pieChartView;
    List pieData = new ArrayList<>();
    // Create the object of TextView
    // and PieChart class
    TextView height, weight ,age,pname;
    TextView tvR, tvPython, tvCPP, tvJava;
    private TextView tv;
    private double MagnitudePrevious = 0;
    public static Integer stepCount = 0;
    public Integer total=0;
    public Integer totalsteps=0;
    private DrawerLayout mDrawerLayout;
    ProjectDataBaseHelper myDb;
    Button button,button1,button2;
    private CircleImageView ProfileImage;
    private static final int PICK_IMAGE = 1;

    String email;
    ImageView editImage;

    private String your_total,today,this_month;

    public static final String MyPREFERENCES = "My" ;

    TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Locale language = getResources().getConfiguration().locale;
        final String current = language.toString();

//        Locale locale;
//        locale = new Locale(languageToLoad);
//        Locale.setDefault(locale);
//        Configuration config = new Configuration();
//        config.locale = locale;
//        Firstpage.getBaseContext().getResources().updateConfiguration(config, Firstpage.getBaseContext().getResources().getDisplayMetrics());


        setContentView(R.layout.firstpage);



        //getSupportActionBar().hide();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.Home);

        Intent intent = getIntent();
        email = intent.getStringExtra("full_name");
        myDb = new ProjectDataBaseHelper(Firstpage.this);
        Cursor res = myDb.fetchprofile1(email);


        SharedPreferences sharedPreferences = getSharedPreferences(Firstpage.MyPREFERENCES, Context.MODE_PRIVATE);
        stepCount = sharedPreferences.getInt("stepCount",0);
        total=sharedPreferences.getInt("total",0);
        totalsteps=sharedPreferences.getInt("totalsteps",0);



        txt=(TextView)findViewById(R.id.textView);
        manageBlinkEffect();

        your_total= getString(R.string.Your_total_steps);
        today= getString(R.string.Today);
        this_month= getString(R.string.This_Month);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.name);
        TextView navUsername1 = (TextView) headerView.findViewById(R.id.age);
        TextView navUsername2= (TextView) headerView.findViewById(R.id.height);
        TextView navUsername3 = (TextView) headerView.findViewById(R.id.weight);

        while (res.moveToNext()) {
            navUsername.setText(res.getString(0));
            navUsername1.setText(res.getString(1));
            navUsername2.setText(res.getString(2));
            navUsername3.setText(res.getString(3));
        }
//profile edit
        editImage = (ImageView)  headerView.findViewById(R.id.profile_image);

        myDb = new ProjectDataBaseHelper(this);
        Cursor data = myDb.getdata(email);
        data.moveToFirst();
        if (data.getCount() > 0) {
            byte[] image = data.getBlob(0);
            final Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            editImage.setImageBitmap(bitmap);
        }

        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gallery = new Intent();
                gallery.setType("image/*");
              gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Select Picture"),PICK_IMAGE);
            }
        });
        //profile edit end
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    menuItem.setChecked(true);
                    mDrawerLayout.closeDrawers();
                    switch (menuItem.getItemId()) {
                        case R.id.profile:
                            Intent iu = new Intent(getApplicationContext(), Profileupdate.class);
                            iu.putExtra("full_name", email);
                            startActivity(iu);
                            break;
                        case R.id.Poc:
                            Intent i = new Intent(getApplicationContext(), POCupdate.class);
                            i.putExtra("full_name", email);
                            startActivity(i);
                            // startActivity(new Intent(getApplicationContext(),SearchViewHandler.class));
                            break;
                        case R.id.password:
                            Intent ui = new Intent(getApplicationContext(), Passwordupdate.class);
                            ui.putExtra("full_name", email);
                            startActivity(ui);
                            break;

                        case R.id.privacy_policy:
                            Intent i3 = new Intent(getApplicationContext(), privacy_policy.class);
                            i3.putExtra("full_name", email);
                            startActivity(i3);
                            break;

                        case R.id.share:
                            Intent myIntent = new Intent(Intent.ACTION_SEND);
                            myIntent.setType("text/plain");
                            String shareBody ="Your body here";
                            String shareSub = "Your Subject here";
                            myIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                            myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                            myIntent.putExtra("full_name", email);
                            startActivity(Intent.createChooser(myIntent, "Share Using"));
                            break;

                        case R.id.about_us:
                            startActivity(new Intent(getApplicationContext(), about_us.class));
                            overridePendingTransition(0,0);
                            break;
                    }
                    return true;
                });
        // Link those objects with their
        // respective id's that
        // we have given in .XML file

        //Check for new day in order to reset stepCount to 0
        Calendar calendar = Calendar.getInstance();
//        if (android.os.Build.VERSION.SDK_INT >= 23) {
//            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 00, 00, 0);
//        } else {
//            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 00, 00, 0);
//        }


        if (android.os.Build.VERSION.SDK_INT >= 23) {
            calendar.set(2021, 0, 10, 00, 00, 0);
        } else {
            calendar.set(2021, 0, 10, 00, 00, 0);
        }

//
//        System.out.println("----------------------------------------------------------------------");
//        System.out.println(calendar.getTimeInMillis());
//        System.out.println("----------------------------------------------------------------------");
//        System.out.println(calendar.get(Calendar.YEAR) + ":" + calendar.get(Calendar.MONTH) + ":" + calendar.get(Calendar.DAY_OF_MONTH));
//        System.out.println("----------------------------------------------------------------------");

        //calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 10, 39, 00);
        //setAlarm(calendar.getTimeInMillis());


        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        pieChartView = findViewById(R.id.chart);

        SensorEventListener stepDetector = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent != null) {
                    float x_acceleration = sensorEvent.values[0];
                    float y_acceleration = sensorEvent.values[1];
                    float z_acceleration = sensorEvent.values[2];

                    double Magnitude = Math.sqrt(x_acceleration * x_acceleration + y_acceleration * y_acceleration + z_acceleration * z_acceleration);
                    double MagnitudeDelta = Magnitude - MagnitudePrevious;
                    MagnitudePrevious = Magnitude;

                    if (MagnitudeDelta > 3) {

                        total++;
                        stepCount++;
                        totalsteps++;
//
//                        total=0;
//                        stepCount=0;
//                        totalsteps=0;

                        setData();
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };

        sensorManager.registerListener(stepDetector, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        // Creating a method setData()
        // to set the text in text view and pie chart
        setData();

    }

    public void logout(MenuItem item) {
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        String userlogin="userlogin" ;
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.putBoolean(userlogin, false);
        editor.commit();
        finish();
        finishAffinity();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            // imageUri = data.getData();
            CropImage.activity(data.getData())
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                   .start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), resultUri);
                    editImage.setImageBitmap(bitmap);
                    byte[] NewEntry7 = imageViewToByte(editImage);

                    AddData(email, NewEntry7);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void AddData(String newEntry, byte[] newEntry7) {
        boolean insertData = myDb.updatevalues(newEntry, newEntry7);
        if (insertData) {
            Toast("Data inserted");

        } else {
            Toast("Data not inserted");
        }
    }

    private void Toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getSharedPreferences(Firstpage.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt("stepCount", stepCount);
        editor.putInt("total", total);
        editor.putInt("totalsteps", totalsteps);
        editor.apply();


    }

    protected void onStop() {
        super.onStop();

        SharedPreferences sharedPreferences = getSharedPreferences(Firstpage.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt("stepCount", stepCount);
        editor.putInt("total", total);
        editor.putInt("totalsteps", totalsteps);
        editor.apply();



    }
    protected void onResume() {
        super.onResume();
    }

    private void setData()
    {

        // Set the percentage of language used

        // tvR.setText(Integer.toString(totalsteps));
        // Set the data and color to the pie chart
        pieData.clear();
        pieData.add(new SliceValue( Integer.parseInt(Integer.toString(stepCount)), Color.MAGENTA).setLabel(today + Integer.toString(stepCount)));
        pieData.add(new SliceValue(Integer.parseInt(Integer.toString(total)), Color.BLUE).setLabel(this_month + Integer.toString(total)));


        //pieData.add(new SliceValue(10, Color.RED).setLabel("Q3: $18"));
        // pieData.add(new SliceValue(Integer.parseInt(tvR.getText().toString()), Color.MAGENTA).setLabel("total"));

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);

        // pieChartData.setHasCenterCircle(true).setCenterText1("STEPS").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
        pieChartData.setHasCenterCircle(true).setCenterText1(your_total).setCenterText2(Integer.toString(totalsteps)).setCenterText2FontSize(25).setCenterText1FontSize(15).setCenterText1Color(Color.parseColor("#FF03DAC5")).setCenterText2Color(Color.parseColor("#FF0000"));
        pieChartView.setPieChartData(pieChartData);
        pieChartView.animate();
        pieChartView.setChartRotationEnabled(true);
    }

    //To reset day steps to 0 each day
    private void setAlarm(long time) {
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(this, MyAlarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        am.setRepeating(AlarmManager.RTC, time, AlarmManager.INTERVAL_DAY, pi);
        Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show();
    }

    //To reset day steps to 0 each day
    public static void process() {
        stepCount = 0;
        //setData();
    }
    public  void p(View view) {
        Intent intent = new Intent(getApplicationContext(),GeneralCheckup.class);
        intent.putExtra("steps",total);
        intent.putExtra("full_name", email);

        startActivity(intent);
        //setData();
    }
    public void c(View view){
        Intent intent = new Intent(getApplicationContext(),DisplayReports.class);
        intent.putExtra("full_name", email);
        startActivity(intent);
    }

    public void languageChange(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(),Settings.class);
        startActivity(intent);
    }


    @SuppressLint("WrongConstant")
    private void manageBlinkEffect() {
        ObjectAnimator anim = ObjectAnimator.ofInt(txt, "textColor", Color.BLUE, Color.MAGENTA, Color.GREEN, Color.BLACK,Color.MAGENTA);
        anim.setDuration(5000);
        anim.setEvaluator(new ArgbEvaluator());
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        anim.start();
    }

}
