package com.example.kehtolaulu.simpleweather

import com.google.gson.annotations.SerializedName

data class CitiesArray(var list: List<City>? = null) {
    data class City (
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("sys")
        var sys: Sys? = null,
        @SerializedName("main")
        var main: City.WeatherTemp? = null,
        @SerializedName("wind")
        var wind: City.Wind? = null) {

        inner class Sys {
            var country: String? = null
        }

        inner class WeatherTemp {
            var temp: Double? = null
            var pressure: Double? = null
            var humidity: Double? = null
        }

        inner class Wind {
            var speed: Double? = null
            var deg: Double? = null
            var gust: Double? = null
        }
    }
}
