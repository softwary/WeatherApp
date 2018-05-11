package ait.hu.weatherapp.Network;
import ait.hu.weatherapp.Data.Weather;
import ait.hu.weatherapp.Data.WeatherResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {
    @GET("data/2.5/weather")
    Call<WeatherResult> getWeatherDetails(
            @Query("q") String name,
            @Query("units") String units,
            @Query("appid") String appId);
}