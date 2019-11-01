package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends AppCompatActivity
{
    private List<Weather> forecast;
    private ImageView sun;
    private ImageView snow;
    private ImageView rain;
    private TextView low;
    private TextView high;
    private int forecastNum = 0;
    private Button next;
    private Button previous;
    private Button reset;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        forecast = new ArrayList<>();
        sun = findViewById( R.id.sun );
        snow = findViewById( R.id.snow );
        rain = findViewById( R.id.rain );
        low = findViewById( R.id.lowTempView );
        high = findViewById( R.id.highTempView );
        next = findViewById( R.id.next );
        previous = findViewById( R.id.previous );
        reset = findViewById( R.id.reset );
        previous.setVisibility( View.GONE );
        reset.setVisibility( View.GONE );
        hideImages();
        Weather w = new Weather( (int)(Math.random()*100), (int)(Math.random()*100) );
        makeValidWeather( w );
        forecast.add( w );
        displayImage( forecast.get( forecastNum ) );
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNextClick( view );
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPreviousClick( view );
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onResetClick( view );
            }
        });
    }

    public void makeValidWeather( Weather w )
    {
        while( w.getHighTemp() < w.getLowTemp() )
        {
            int high = (int)(Math.random()*100);
            int low = (int)(Math.random()*100);
            w.setHighTemp( high );
            w.setLowTemp( low );
        }
    }

    public void displayImage( Weather w )
    {
        int lowCelcius = (w.getLowTemp() - 32) * 5/9;
        int highCelcius = (w.getHighTemp() - 32) * 5/9;
        low.setText( "Lowest Temperature: " + w.getLowTemp() + " °F / " + lowCelcius + " °C" );
        high.setText( "Highest Temperature: " + w.getHighTemp() + " °F / " + highCelcius + " °C" );
        if ( w.getLowTemp() < 32 )
        {
            snow.setVisibility( View.VISIBLE );
        }
        else if ( w.getHighTemp() > 70 )
        {
            sun.setVisibility( View.VISIBLE );
        }
        else if ( w.getLowTemp() > 32 && w.getHighTemp() < 60 )
        {
            rain.setVisibility( View.VISIBLE );
        }
        else
        {
            sun.setVisibility( View.VISIBLE );
        }
        if ( forecastNum == 0 )
        {
            reset.setVisibility( View.GONE );
        }
    }

    public void hideImages()
    {
        sun.setVisibility( View.GONE );
        snow.setVisibility( View.GONE );
        rain.setVisibility( View.GONE );
    }

    public void onNextClick( View v )
    {
        hideImages();
        Weather newW = new Weather( (int)(Math.random()*100), (int)(Math.random()*100) );
        makeValidWeather( newW );
        forecast.add( newW );
        forecastNum++;
        displayImage( forecast.get( forecastNum ) );
        Toast.makeText( this, "Day " + ( forecastNum + 1 ) + "'s Weather", Toast.LENGTH_SHORT ).show();
        previous.setVisibility( View.VISIBLE );
        reset.setVisibility( View.VISIBLE );
    }

    public void onPreviousClick( View v )
    {
        if ( forecastNum > 0 )
        {
            forecastNum--;
            hideImages();
            displayImage( forecast.get( forecastNum ) );
            Toast.makeText( this, "Day " + ( forecastNum + 1 ) + "'s Weather", Toast.LENGTH_SHORT ).show();
            reset.setVisibility( View.VISIBLE );
            if( forecastNum == 0 )
            {
                reset.setVisibility( View.GONE );
                previous.setVisibility( View.GONE );
            }
        }
    }

    public void onResetClick( View v )
    {
        hideImages();
        forecastNum = 0;
        displayImage( forecast.get( forecastNum ) );
        reset.setVisibility( View.GONE );
        previous.setVisibility( View.GONE );
        Toast.makeText( this, "Today's Weather", Toast.LENGTH_SHORT ).show();
    }
}
