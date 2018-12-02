package com.example.kolys.weatherapp.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kolys.weatherapp.MainActivity;
import com.example.kolys.weatherapp.R;
import com.example.kolys.weatherapp.Weather;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    private List<Weather> weathers;
    private MainActivity activity;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView cityTV;
        public TextView countryTV;
        public TextView temperatureTV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cityTV = itemView.findViewById(R.id.TV_city_item);
            countryTV = itemView.findViewById(R.id.TV_country_item);
            temperatureTV = itemView.findViewById(R.id.TV_temperature_item);
        }
    }

    public RecyclerAdapter(List<Weather> weathers, MainActivity activity) {
        this.weathers = weathers;
        this.activity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.city_item, parent, false);

        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Weather weather = weathers.get(position);
        holder.cityTV.setText(weather.getCity());
        holder.countryTV.setText(weather.getCountry());
        holder.temperatureTV.setText(weather.getTemperature());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return weathers.size();
    }
}
