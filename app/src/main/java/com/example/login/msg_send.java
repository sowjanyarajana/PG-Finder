package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;

public class msg_send extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_send);

        Intent intent=getIntent();
        String s= intent.getStringExtra("key");

        String str = "Sending message as booking PG!!!!";
        //create intent
        Intent bookpg = new Intent(getApplicationContext(), msg_send.class);


        //Pending Intent
        PendingIntent my_pi = PendingIntent.getActivity(getApplicationContext(),0,bookpg,0);
        SmsManager mysmsmanager = SmsManager.getDefault();
        mysmsmanager.sendTextMessage(s,null,str,my_pi,null);

    }
}