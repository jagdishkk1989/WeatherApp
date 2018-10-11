package com.jagdish.weatherapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherItem implements Parcelable {


    @SerializedName("dt")
    @Expose
    private long dtTimeStamp;

    @SerializedName("main")
    @Expose
    private MainDetails main;

    @SerializedName("weather")
    @Expose
    private List<Weather> weatherList;

    @SerializedName("clouds")
    @Expose
    private Clouds clouds;

    @SerializedName("wind")
    @Expose
    private Wind wind;

    @SerializedName("dt_txt")
    @Expose
    private String dt_txt;

    protected WeatherItem(Parcel in) {
        dtTimeStamp = in.readLong();
        main = in.readParcelable(MainDetails.class.getClassLoader());
        weatherList = in.createTypedArrayList(Weather.CREATOR);
        clouds = in.readParcelable(Clouds.class.getClassLoader());
        wind = in.readParcelable(Wind.class.getClassLoader());
        dt_txt = in.readString();
    }

    public long getDtTimeStamp() {
        return dtTimeStamp;
    }

    public void setDtTimeStamp(long dtTimeStamp) {
        this.dtTimeStamp = dtTimeStamp;
    }

    public MainDetails getMain() {
        return main;
    }

    public void setMain(MainDetails main) {
        this.main = main;
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(dtTimeStamp);
        dest.writeParcelable(main, flags);
        dest.writeTypedList(weatherList);
        dest.writeParcelable(clouds, flags);
        dest.writeParcelable(wind, flags);
        dest.writeString(dt_txt);
    }

    public static final Creator<WeatherItem> CREATOR = new Creator<WeatherItem>() {
        @Override
        public WeatherItem createFromParcel(Parcel in) {
            return new WeatherItem(in);
        }

        @Override
        public WeatherItem[] newArray(int size) {
            return new WeatherItem[size];
        }
    };
}
