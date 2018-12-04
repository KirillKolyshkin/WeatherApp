package com.example.kolys.weatherapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_weather_details.*

class WeatherDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_details)
        val intent = intent
        if (intent != null) {
            tv_city.text = intent.getStringExtra("city")
            tv_country.text = intent.getStringExtra("country")
            tv_temperature.text = "temperature: ${intent.getStringExtra("temperature")}"
            tv_pressure.text = "pressure: ${intent.getStringExtra("pressure")}"
            tv_humidity.text = "humidity: ${intent.getStringExtra("humidity")}"
            tv_windDirection.text = "windDirection: ${intent.getStringExtra("windDirection")}"
        }
    }
}
