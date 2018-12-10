package com.example.kolys.weatherapp

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.kehtolaulu.simpleweather.CitiesArray

@Dao
interface DataDao {
    @Query("Select * From city Order by city.name")
    fun getData(): List <CitiesArray.City>

    @Query("delete from city")
    fun dropData()

    @Insert
    fun insertData(city: List<CitiesArray.City>)

}