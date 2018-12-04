package com.example.kolys.weatherapp

import com.example.kehtolaulu.simpleweather.CitiesArray
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("data/2.5/find")
    fun getData(@Query("lat") lat: Double,@Query("lon") lon: Double,
                @Query("cnt") amount:Int,@Query("appid") appid:String): Call<CitiesArray>
}
