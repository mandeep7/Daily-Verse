package com.mandy.mic.dailyverse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.start_screen);
                Intent intent1=new Intent(this,mainScreen.class);

                Thread.sleep(2000);
                startActivity(intent1);
            }catch(InterruptedException e){}
    }
}
