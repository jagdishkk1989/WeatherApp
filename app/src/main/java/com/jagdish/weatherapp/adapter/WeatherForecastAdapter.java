package com.jagdish.weatherapp.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jagdish.weatherapp.R;
import com.jagdish.weatherapp.data.MainDetails;
import com.jagdish.weatherapp.data.Weather;
import com.jagdish.weatherapp.data.WeatherItem;
import com.jagdish.weatherapp.data.Wind;
import com.jagdish.weatherapp.fragment.BaseFragment;
import com.jagdish.weatherapp.fragment.WeatherDetailFragment;
import com.jagdish.weatherapp.utility.AppConstants;
import com.jagdish.weatherapp.utility.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.WeatherAdapterViewHolder> {

    private static final String TAG = WeatherForecastAdapter.class.getName();
    Context mContext;
    List<WeatherItem> weatherItemList;

    public class WeatherAdapterViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mWeatherImage;
        public final TextView currentTemp;
        public final TextView minTemp;
        public final TextView maxTemp;
        public final TextView mDay;
        public final TextView mDesc;
        public final TextView mOtherInfo;


        public WeatherAdapterViewHolder(View view) {
            super(view);
            mWeatherImage = (ImageView) view.findViewById(R.id.weatherImage);
            currentTemp = (TextView) view.findViewById(R.id.currentTemp);
            minTemp = (TextView) view.findViewById(R.id.minTemp);
            maxTemp = (TextView) view.findViewById(R.id.maxTemp);
            mDay = (TextView) view.findViewById(R.id.day);
            mDesc = (TextView) view.findViewById(R.id.desc);
            mOtherInfo = (TextView) view.findViewById(R.id.otherInfo);

        }
    }

    public WeatherForecastAdapter(Context context, List<WeatherItem> recipeList) {
        this.mContext = context;
        this.weatherItemList = recipeList;
    }

    @Override
    public WeatherAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.weather_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new WeatherAdapterViewHolder(view);
    }


    @Override
    public void onBindViewHolder(WeatherAdapterViewHolder weatherAdapterViewHolder, final int position) {

        final WeatherItem weatherItem = weatherItemList.get(position);

        if (weatherItem != null) {

            String otherInfo = "";
            if (weatherItem.getDt_txt() != null) {
                weatherAdapterViewHolder.mDay.setText(Utils.getFormattedDate(weatherItem.getDt_txt()));
            }

            MainDetails mainDetails = weatherItem.getMain();
            Wind wind = weatherItem.getWind();

            if (mainDetails != null) {
                weatherAdapterViewHolder.currentTemp.setText("Temp: " + mainDetails.getTemp() + " C");
                weatherAdapterViewHolder.minTemp.setText(mainDetails.getTemp_min() + " C");
                weatherAdapterViewHolder.maxTemp.setText(mainDetails.getTemp_max() + " C");

                otherInfo = "Humidity: " + mainDetails.getHumidity();
            }

            if (wind != null) {
                otherInfo = otherInfo.concat("\t \t Wind: " + wind.getSpeed());
            }

            if (weatherItem.getWeatherList() != null && weatherItem.getWeatherList().size() > 0) {
                Weather weather = weatherItem.getWeatherList().get(0);

                if (weather.getIcon() != null) {
                    String iconPath = AppConstants.ICON_URL + weather.getIcon() + ".png";
                    Picasso.with(mContext).load(iconPath).placeholder(mContext.getResources().getDrawable(R.drawable.ic_thumbnails_loading)).error(mContext.getResources().getDrawable(R.drawable.ic_sun)).into(weatherAdapterViewHolder.mWeatherImage);
                }

                if (weather.getDescription() != null)
                    weatherAdapterViewHolder.mDesc.setText(weather.getMain() + "\n" + weather.getDescription());
            }

            weatherAdapterViewHolder.mOtherInfo.setText(otherInfo);
        }


        weatherAdapterViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WeatherDetailFragment weatherDetailFragment = new WeatherDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("weatherDetail", weatherItem);
                weatherDetailFragment.setArguments(bundle);
                BaseFragment.addFragment(mContext, R.id.container, weatherDetailFragment, "WeatherDetailFragment", "WeatherDetailFragment");
            }
        });


    }

    @Override
    public int getItemCount() {
        if (null == weatherItemList) return 0;
        return weatherItemList.size();
    }

}
