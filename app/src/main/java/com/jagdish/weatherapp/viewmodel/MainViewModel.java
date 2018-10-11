package com.jagdish.weatherapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.jagdish.weatherapp.data.WeatherData;
import com.jagdish.weatherapp.data.WeatherItem;
import com.jagdish.weatherapp.webservices.RestApiClient;
import com.jagdish.weatherapp.webservices.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainViewModel extends AndroidViewModel {

    private static final String TAG = MainViewModel.class.getName();

    private MutableLiveData<List<WeatherItem>> weatherItemList;


    private RestApiClient apiClient;

    public MainViewModel(@NonNull Application application) {
        super(application);
        apiClient = ServiceGenerator.createService(RestApiClient.class);
    }

    public LiveData<List<WeatherItem>> getWeatherDataByCityId(int cityId, String units, String appId) {
        if (weatherItemList == null) {
            weatherItemList = new MutableLiveData<>();
            loadWeatherDataByCityId(cityId, units, appId);
        }
        return weatherItemList;
    }

    public LiveData<List<WeatherItem>> getWeatherDataByLatLng(double lat, double lng, String units, String appId) {
        if (weatherItemList == null) {
            weatherItemList = new MutableLiveData<>();
            loadWeatherDataByLatLng(lat, lng, units,  appId);
        }
        return weatherItemList;
    }


    public void loadWeatherDataByCityId(int cityId, String units, String appId) {


        Call<WeatherData> call = apiClient.getWeatherDataByCityId(cityId, units, appId);
        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {

                if (response.isSuccessful()) {

                    WeatherData result = response.body();
                    if (result != null) {

                        List<WeatherItem> value = weatherItemList.getValue();
                        if (value == null || value.isEmpty()) {
                            weatherItemList.setValue(result.getWeatherItemList());
                        } else {
                            value.addAll(result.getWeatherItemList());
                            weatherItemList.setValue(value);
                        }

                       // Log.d(TAG, "jk load weather data response:" + weatherItemList.getValue().size());
                    }
                }
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                weatherItemList = null;
                Log.d(TAG, "jk load weather data failure:" + t.getMessage());
            }
        });
    }

    public void loadWeatherDataByLatLng(double lat, double lng, String units, String appId) {

        Call<WeatherData> call = apiClient.getWeatherDataByLatLng(lat, lng, units, appId);
        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {

                if (response.isSuccessful()) {

                    WeatherData result = response.body();
                    if (result != null) {

                        List<WeatherItem> value = weatherItemList.getValue();
                        if (value == null || value.isEmpty()) {
                            weatherItemList.setValue(result.getWeatherItemList());
                        } else {
                            value.addAll(result.getWeatherItemList());
                            weatherItemList.setValue(value);
                        }

                        //Log.d(TAG, "jk load weather data response:" + weatherItemList.getValue().size());
                    }
                }
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                weatherItemList = null;
                Log.d(TAG, "jk load weather data failure:" + t.getMessage());
            }
        });
    }

}
