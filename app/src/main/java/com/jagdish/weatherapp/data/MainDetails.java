package com.jagdish.weatherapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainDetails implements Parcelable {

    @SerializedName("temp")
    @Expose
    private double temp;

    @SerializedName("temp_min")
    @Expose
    private double temp_min;

    @SerializedName("temp_max")
    @Expose
    private double temp_max;

    @SerializedName("pressure")
    @Expose
    private double pressure;

    @SerializedName("sea_level")
    @Expose
    private double sea_level;

    @SerializedName("grnd_level")
    @Expose
    private double grnd_level;

    @SerializedName("humidity")
    @Expose
    private int humidity;

    @SerializedName("temp_kf")
    @Expose
    private double temp_kf;

    protected MainDetails(Parcel in) {
        temp = in.readDouble();
        temp_min = in.readDouble();
        temp_max = in.readDouble();
        pressure = in.readDouble();
        sea_level = in.readDouble();
        grnd_level = in.readDouble();
        humidity = in.readInt();
        temp_kf = in.readDouble();
    }


    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getSea_level() {
        return sea_level;
    }

    public void setSea_level(double sea_level) {
        this.sea_level = sea_level;
    }

    public double getGrnd_level() {
        return grnd_level;
    }

    public void setGrnd_level(double grnd_level) {
        this.grnd_level = grnd_level;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getTemp_kf() {
        return temp_kf;
    }

    public void setTemp_kf(double temp_kf) {
        this.temp_kf = temp_kf;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(temp);
        dest.writeDouble(temp_min);
        dest.writeDouble(temp_max);
        dest.writeDouble(pressure);
        dest.writeDouble(sea_level);
        dest.writeDouble(grnd_level);
        dest.writeInt(humidity);
        dest.writeDouble(temp_kf);
    }

    public static final Parcelable.Creator<MainDetails> CREATOR = new Parcelable.Creator<MainDetails>() {
        @Override
        public MainDetails createFromParcel(Parcel in) {
            return new MainDetails(in);
        }

        @Override
        public MainDetails[] newArray(int size) {
            return new MainDetails[size];
        }
    };

}
