package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity
{
    private static int splashTime = 3000;
    private Handler waitHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        waitHandler.postDelayed(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    Intent i = new Intent( getApplicationContext(), WeatherActivity.class );
                    startActivity( i );
                }
                catch ( Exception e )
                {
                    System.out.print( e );
                }
            }
        }, splashTime);
    }
}
