package ait.hu.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ait.hu.weatherapp.Data.Weather;
import ait.hu.weatherapp.Data.WeatherResult;
import ait.hu.weatherapp.Network.WeatherAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherDetailsActivity extends AppCompatActivity {


    private LinearLayout weather_details;

    private TextView tvWDCity;
    private TextView tvWDTemperature;
    private TextView tvWDCoord;
    private TextView tvWDVisibility;
    private TextView tvWDWinds;


    private static final String URL_BASE =
            "http://api.openweathermap.org/";
    private static final String API_KEY =
            "86cf887e523a765ef156c39c05cf5eed";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_details);



        Intent intent = getIntent();
        String cityName = intent.getStringExtra("cityWeather");

        tvWDTemperature = findViewById(R.id.tvWDTemperature);
        tvWDCoord = findViewById(R.id.tvWDCoord);
        tvWDVisibility = findViewById(R.id.tvWDVisibility);
        tvWDWinds = findViewById(R.id.tvWDWinds);


        tvWDCity = findViewById(R.id.tvWDCity);
        tvWDCity.setText(cityName);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final WeatherAPI weatherAPI = retrofit.create(WeatherAPI.class);

        Call<WeatherResult> callCity = weatherAPI.getWeatherDetails(cityName, getString(R.string.metric_units), API_KEY);

        callCity.enqueue(new Callback<WeatherResult>() {
            @Override
            public void onResponse(Call<WeatherResult> call, Response<WeatherResult> response) {


                String temperature = "" + response.body().getMain().getTemp().toString();
                tvWDTemperature.setText(getString(R.string.tvTemperature) + temperature);




                String coords = (String) "" + response.body().getCoord();
                tvWDCoord.setText(getString(R.string.tvCoordinates) + coords);

                String visibility = ""+ response.body().getVisibility().toString();
                tvWDVisibility.setText(getString(R.string.tvVisibility) + visibility);


                String winds = "" + response.body().getWind().toString();
                tvWDWinds.setText(getString(R.string.tvWinds) +winds);


            }

            @Override
            public void onFailure(Call<WeatherResult> call, Throwable t) {
                Log.i("apiError","Error: "+getString(R.string.Error) + t.getMessage());

                Snackbar.make(findViewById(R.id.weatherDetails), getString(R.string.Error) + t.getMessage(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Log.i("apiError","Error: "+getString(R.string.Error) + t.getMessage());
            }

        });


    }
}