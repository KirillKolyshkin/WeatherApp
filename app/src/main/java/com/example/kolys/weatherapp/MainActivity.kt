package com.example.kolys.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.arch.persistence.room.Room
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.example.kehtolaulu.simpleweather.CitiesArray
import com.example.kolys.weatherapp.RecyclerView.RecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), ItemCallback {

    private lateinit var retrofit: Retrofit
    private lateinit var retrofitService: RetrofitService
    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var longtitude: Double = baseLongtitude
    private var lattitude: Double = baseLattitude
    private lateinit var dataBase : Database

    companion object {
        var cities: List<CitiesArray.City>? = null
        const val API_BASE_URL = "https://api.openweathermap.org/"
        const val apiKey: String = "841c58e8f74b39ca6228f4b81ab58ed1"
        const val baseLongtitude: Double = 49.0
        const val baseLattitude: Double = 55.8
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataBase = Room
                .databaseBuilder(this.applicationContext,Database::class.java, "sometext")
                .allowMainThreadQueries()
                .build()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(this, getString(R.string.notGranted), Toast.LENGTH_SHORT).show()
            requestPermissionWithRationale()
        } else {
            getCitiesWithGeo()
        }

        retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        retrofitService = retrofit.create(RetrofitService::class.java)

        recyclerAdapter = RecyclerAdapter(CitiesDiffCallBack(), this)
        val manager = LinearLayoutManager(this)
        recycler_view.adapter = recyclerAdapter
        recycler_view.layoutManager = manager
        getData()
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, WeatherDetailsActivity::class.java)
        intent.putExtra("city", cities?.get(position)?.name)
        intent.putExtra("country", cities?.get(position)?.sys?.country)
        intent.putExtra("temperature", cities?.get(position)?.main?.temp?.let { Math.round(it - 273.15) })
        intent.putExtra("pressure",  cities?.get(position)?.main?.pressure)
        intent.putExtra("humidity",  cities?.get(position)?.main?.humidity)
        intent.putExtra("windDirection",  cities?.get(position)?.wind?.deg)
        startActivity(intent)
    }

    private fun getData() {
        retrofitService.getData(lattitude, longtitude, 20, apiKey).enqueue(object : Callback<CitiesArray> {
            override fun onResponse(call: Call<CitiesArray>?, response: Response<CitiesArray>) {
                dataBase.dataDao().dropData()
                response.body()?.list?.let { dataBase.dataDao().insertData(it) }
                cities = response.body()?.list
                recyclerAdapter.submitList(cities)
            }

            override fun onFailure(call: Call<CitiesArray>?, t: Throwable?) {
                Log.i("", t.toString())
                Toast.makeText(this@MainActivity, getString(R.string.smthWrong), Toast.LENGTH_SHORT).show()
                cities = dataBase.dataDao().getData()
                recyclerAdapter.submitList(cities)
            }
        })
    }

    fun requestPermissionWithRationale() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(this@MainActivity, getString(R.string.allow), Toast.LENGTH_SHORT).show()
        } else {
            requestPerms()
        }
    }

    private fun requestPerms() {
        val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.requestPermissions(permissions, 123)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        var allowed = false
        when (requestCode) {
            123 -> allowed = grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
        }
        if (allowed) getCitiesWithGeo()
    }

    @SuppressLint("MissingPermission")
    fun getCitiesWithGeo() {
        fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        lattitude = location.latitude
                        longtitude = location.longitude
                        getData()
                        Toast.makeText(this, getString(R.string.success), Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, getString(R.string.exeption), Toast.LENGTH_LONG).show()
                    }
                }
    }
}
