package com.example.kolys.weatherapp

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_weather_details.*

class WeatherDetailsActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_details)
        val intent = intent
        if (intent != null) {
            tv_city.text = intent.getStringExtra("city")
            tv_country.text = intent.getStringExtra("country")
            tv_temperature.text = resources.getString(R.string.temperature) + ": ${intent.getStringExtra("temperature")}"
            tv_pressure.text = resources.getString(R.string.pressure) + ": ${intent.getStringExtra("pressure")}"
            tv_humidity.text = resources.getString(R.string.humidity) + ": ${intent.getStringExtra("humidity")}"
            tv_windDirection.text = resources.getString(R.string.windDirection) + ": ${intent.getStringExtra("windDirection")}"
        }
    }
}
