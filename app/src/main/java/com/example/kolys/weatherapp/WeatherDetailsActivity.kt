package com.example.kolys.weatherapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_weather_details.*

class WeatherDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_details)
        val intent = intent
        if (intent != null) {
            TV_city.text = intent.getStringExtra("city")
            TV_country.text = intent.getStringExtra("country")
            TV_temperature.text = intent.getStringExtra("temperature")
            TV_pressure.text = intent.getStringExtra("pressure")
            TV_humidity.text = intent.getStringExtra("humidity")
            TV_windDirection.text = intent.getStringExtra("windDirection")
        }
    }

}
