package com.example.kolys.weatherapp

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.kehtolaulu.simpleweather.CitiesArray

@Database(entities = arrayOf(
        CitiesArray.City::class,
        CitiesArray.City.Sys::class,
        CitiesArray.City.Wind::class,
        CitiesArray.City.WeatherTemp::class)
        , version = 1)
abstract class Database : RoomDatabase() {
    abstract fun dataDao() : DataDao
}