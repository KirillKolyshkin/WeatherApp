package com.example.kolys.weatherapp.RecyclerView

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.kehtolaulu.simpleweather.CitiesArray
import com.example.kolys.weatherapp.ItemCallback
import com.example.kolys.weatherapp.R
import kotlinx.android.synthetic.main.city_item.view.*

open class RecyclerAdapter(diffCallback: DiffUtil.ItemCallback<CitiesArray.City>, private var callbackItem: ItemCallback) : ListAdapter<CitiesArray.City, RecyclerAdapter.MyViewHolder>(diffCallback){

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.MyViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.city_item, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.TV_city_item.text = getItem(position).name
        holder.itemView.TV_country_item.text = getItem(position).sys?.country
        holder.itemView.TV_temperature_item.text = getItem(position).main?.temp?.let { Math.round(it-273.15).toString()}
        holder.itemView.setOnClickListener { callbackItem.onItemClick(position) }
    }

}
