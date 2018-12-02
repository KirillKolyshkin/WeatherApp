package com.example.kolys.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherDetailsActivity extends AppCompatActivity {

    TextView tv_city;
    TextView tv_country;
    TextView tv_temperature;
    TextView tv_pressure;
    TextView tv_humidity;
    TextView tv_windDirection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);
        tv_city = findViewById(R.id.TV_city);
        tv_country = findViewById(R.id.TV_country);
        tv_temperature= findViewById(R.id.TV_temperature);
        tv_pressure = findViewById(R.id.TV_pressure);
        tv_humidity = findViewById(R.id.TV_humidity);
        tv_windDirection = findViewById(R.id.TV_windDirection);

        Intent intent = getIntent();
        if (intent!=null){
            tv_city.setText(intent.getStringExtra("city"));
            tv_country.setText(intent.getStringExtra("country"));
            tv_temperature.setText(intent.getStringExtra("temperature"));
            tv_pressure.setText(intent.getStringExtra("pressure"));
            tv_humidity.setText(intent.getStringExtra("humidity"));
            tv_windDirection.setText(intent.getStringExtra("windDirection"));
        }

    }

}
