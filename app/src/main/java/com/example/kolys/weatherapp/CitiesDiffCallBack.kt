package com.example.kolys.weatherapp

import android.support.v7.util.DiffUtil
import com.example.kehtolaulu.simpleweather.CitiesArray

class CitiesDiffCallBack : DiffUtil.ItemCallback<CitiesArray.City>(){
    override fun areItemsTheSame(old: CitiesArray.City, aNew: CitiesArray.City): Boolean = old.name.equals(aNew.name)

    override fun areContentsTheSame(old: CitiesArray.City, aNew: CitiesArray.City): Boolean = old.main?.temp==aNew.main?.temp
}