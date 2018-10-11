package com.jagdish.weatherapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.jagdish.weatherapp.fragment.WeatherForecastFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add main fragment
        addWeatherForecaseFragment();
    }

    public void addWeatherForecaseFragment() {

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        WeatherForecastFragment weatherForecastFragment = new WeatherForecastFragment();
        fragmentTransaction.replace(R.id.container, weatherForecastFragment, "WeatherForecastFragment");

        fragmentTransaction.commit();
    }
}
