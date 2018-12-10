package com.example.kehtolaulu.simpleweather

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class CitiesArray(var list: List<City>? = null) {
    @Entity(tableName = "city")
    data class City(
            @PrimaryKey var id: Int,
            @SerializedName("name")
            @ColumnInfo(name = "name")
            var name: String? = null,
            @SerializedName("sys")
            @Embedded(prefix = "sys")
            var sys: Sys? = null,
            @SerializedName("main")
            @Embedded(prefix = "main")
            var main: City.WeatherTemp? = null,
            @SerializedName("wind")
            @Embedded(prefix = "wind")
            var wind: City.Wind? = null) {

        @Entity(tableName = "sys")
        data class Sys(
                @PrimaryKey var id: Int,
                @ColumnInfo(name = "country")
                var country: String? = null
        )

        @Entity(tableName = "weatherTemp")
        data class WeatherTemp(
                @PrimaryKey var id: Int,
                @ColumnInfo(name = "temp")
                var temp: Double? = null,
                @ColumnInfo(name = "pressure")
                var pressure: Double? = null,
                @ColumnInfo(name = "humidity")
                var humidity: Double? = null
        )

        @Entity(tableName = "windClass")
        data class Wind(
                @PrimaryKey var id: Int,
                @ColumnInfo(name = "speed")
                var speed: Double? = null,
                @ColumnInfo(name = "deg")
                var deg: Double? = null,
                @ColumnInfo(name = "gust")
                var gust: Double? = null
        )
    }
}
