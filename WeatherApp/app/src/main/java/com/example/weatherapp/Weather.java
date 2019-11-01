package com.example.weatherapp;

public class Weather
{
    private int lowTemp;
    private int highTemp;

    public Weather( int lowTemp, int highTemp )
    {
        this.lowTemp = lowTemp;
        this.highTemp = highTemp;
    }

    public int getHighTemp() {
        return highTemp;
    }

    public int getLowTemp() {
        return lowTemp;
    }

    public void setHighTemp(int highTemp)
    {
        this.highTemp = highTemp;
    }

    public void setLowTemp(int lowTemp)
    {
        this.lowTemp = lowTemp;
    }
}
