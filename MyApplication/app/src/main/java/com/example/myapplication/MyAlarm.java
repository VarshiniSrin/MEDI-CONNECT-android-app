package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

//class extending the Broadcast Receiver
public class MyAlarm extends BroadcastReceiver {

    //the method will be fired when the alarm is triggered
    @Override
    public void onReceive(Context context, Intent intent) {

        //but you can do any task here that you want to be done at a specific time everyday
        Firstpage.process();
        System.out.println("-----------------------------------------------------");
        System.out.println("ALARM JUST FIRED-----------------------");
    }

}