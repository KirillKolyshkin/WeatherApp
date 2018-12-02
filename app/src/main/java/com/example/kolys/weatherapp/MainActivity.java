package com.example.kolys.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kolys.weatherapp.RecyclerView.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public List<Weather> weathers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillTestData();
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(weathers, this);
        recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(manager);
    }

    private void fillTestData() {
        weathers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Weather planet = new Weather("Town" + i,
                    "Russia", "-30", "69", "199", "180");
            weathers.add(planet);
        }
    }

    public void onItemClick(int position) {
        Intent intent = new Intent(this, WeatherDetailsActivity.class);
        intent.putExtra("city", weathers.get(position).getCity());
        intent.putExtra("country", weathers.get(position).getCountry());
        intent.putExtra("temperature", weathers.get(position).getTemperature());
        intent.putExtra("pressure", "pressure: " + weathers.get(position).getPressure());
        intent.putExtra("humidity", "humidity: " + weathers.get(position).getHumidity());
        intent.putExtra("windDirection", "wind direction: " + weathers.get(position).getWindDirection());
        startActivity(intent);
    }
}
