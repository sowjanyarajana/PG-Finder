package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class splashscreen extends AppCompatActivity {

    /*ImageView pgroom;
    LottieAnimationView lottieAnimationView;*/
    Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        /*pgroom = findViewById(R.id.pgroom);
        btn_login = findViewById(R.id.button);
        lottieAnimationView = findViewById(com.airbnb.lottie.R.id.);
        lottieAnimationView.setAnimation(R.raw.splashscreen);

        pgroom.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(1400).setDuration(1000).setStartDelay(4000);*/

    }

    public void showLogin(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}