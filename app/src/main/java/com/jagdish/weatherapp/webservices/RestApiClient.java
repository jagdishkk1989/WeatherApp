package com.jagdish.weatherapp.webservices;

import com.jagdish.weatherapp.data.WeatherData;
import com.jagdish.weatherapp.utility.AppConstants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApiClient {

    @GET(AppConstants.GET_WEATHER_DATA)
    Call<WeatherData> getWeatherDataByCityId(@Query("id") int cityId,
                                             @Query("units") String units,
                                             @Query("APPID") String appId);

    @GET(AppConstants.GET_WEATHER_DATA)
    Call<WeatherData> getWeatherDataByLatLng(@Query("lat") double lat,
                                             @Query("lon") double lng,
                                             @Query("units") String units,
                                             @Query("appid") String appId);

}