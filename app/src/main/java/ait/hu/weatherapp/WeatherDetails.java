package ait.hu.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ait.hu.weatherapp.Data.Weather;
import ait.hu.weatherapp.Network.WeatherAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherDetails extends AppCompatActivity {


    private LinearLayout weather_details;

    private TextView tvWDCity;
    private TextView tvWDTemperature;
    private TextView tvWDCoord;
    private TextView tvWDVisibility;
    private TextView tvWDWinds;


    private static final String URL_BASE =
            "http://api.openweathermap.org";
    private static final String API_KEY =
            "524901&APPID=86cf887e523a765ef156c39c05cf5eed";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_details);


        Intent intent = getIntent();
        String cityName = intent.getStringExtra("cityWeather");


        Snackbar.make(weather_details, "HIIII!!!!!!!!!!!!" + cityName, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        tvWDCity.setText(cityName);
/*
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final WeatherAPI weatherAPI = retrofit.create(WeatherAPI.class);

        Call<Weather> callCity = weatherAPI.getWeatherDetails(cityName, getString(R.string.metric_units), API_KEY);

        callCity.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                tvWDCity.setText(response.body().getMain());
//                tvWDTemperature.setText(response.body.);
//                tvWdCoord.setText(response.body.);
//                tvWDVisibility.setText(response.body.);
//                tvWDWinds.setText();
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Snackbar.make(weather_details, getString(R.string.Error) + t.getMessage(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }

        });
        */

}
















        }
