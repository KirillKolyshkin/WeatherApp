package com.example.kolys.weatherapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast

import com.example.kehtolaulu.simpleweather.CitiesArray
import com.example.kolys.weatherapp.RecyclerView.RecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), ItemCallback {

    internal var API_BASE_URL = "https://api.openweathermap.org/"
    private lateinit var retrofit: Retrofit
    //private lateinit var locationManager: LocationManager
    private lateinit var retrofitService: RetrofitService
    private lateinit var recyclerAdapter: RecyclerAdapter

    companion object {
        var cities: List<CitiesArray.City>? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(this, "not granted", Toast.LENGTH_SHORT).show()
        } else {
        }*/

        retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        retrofitService = retrofit.create(RetrofitService::class.java)


        recyclerAdapter = RecyclerAdapter(CitiesDiffCallBack(), this)
        val manager = LinearLayoutManager(this)
        recycler_view.adapter = recyclerAdapter
        recycler_view.layoutManager = manager

        retrofitService.getData().enqueue(object : Callback<CitiesArray> {
            override fun onResponse(call: Call<CitiesArray>?, response: Response<CitiesArray>) {
                cities = response.body()?.list
                recyclerAdapter.submitList(cities)
            }

            override fun onFailure(call: Call<CitiesArray>?, t: Throwable?) {
                Log.i("", t.toString())
                Toast.makeText(this@MainActivity, "An error occurred during networking", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, WeatherDetailsActivity::class.java)
        intent.putExtra("city", cities?.get(position)?.name)
        intent.putExtra("country", cities?.get(position)?.sys!!.country)
        intent.putExtra("temperature", cities?.get(position)?.main?.temp?.let { Math.round(it-273.15).toString() })
        intent.putExtra("pressure", "pressure: " + cities?.get(position)?.main?.pressure)
        intent.putExtra("humidity", "humidity: " + cities?.get(position)?.main?.humidity)
        intent.putExtra("windDirection", "wind direction: " + cities?.get(position)?.wind?.deg)
        startActivity(intent)
    }
}
