package com.example.kolys.weatherapp

import com.example.kehtolaulu.simpleweather.CitiesArray
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("data/2.5/find?lat=55.5&lon=49.0&cnt=20&appid=841c58e8f74b39ca6228f4b81ab58ed1")
    fun getData() : Call<CitiesArray>
}
