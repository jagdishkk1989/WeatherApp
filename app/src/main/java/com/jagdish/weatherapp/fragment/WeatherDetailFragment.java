package com.jagdish.weatherapp.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.jagdish.weatherapp.utility.AppConstants;
import com.jagdish.weatherapp.utility.Utils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherDetailFragment extends Fragment {

    private static final String TAG = WeatherDetailFragment.class.getName();

    private View rootView;
    private Activity mParentActivity;

    WeatherItem mWeatherItem;

    @BindView(R.id.weatherIcon)
    ImageView weatherIcon;


    @BindView(R.id.tvDesc)
    TextView tvDesc;


    @BindView(R.id.tvCurrentTemp)
    TextView tvCurrentTemp;

    @BindView(R.id.tvDay)
    TextView tvDay;

    @BindView(R.id.tvMinTemp)
    TextView tvMinTemp;

    @BindView(R.id.tvMaxTemp)
    TextView tvMaxTemp;

    @BindView(R.id.tvHumidity)
    TextView tvHumidity;

    @BindView(R.id.tvWind)
    TextView tvWind;

    @BindView(R.id.tvSeaLevel)
    TextView tvSeaLevel;

    @BindView(R.id.tvGrndLevel)
    TextView tvGrndLevel;

    @BindView(R.id.tvPressure)
    TextView tvPressure;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() != null) {
            mParentActivity = (Activity) getActivity();
            if (getArguments() != null) {
                Bundle bundle = getArguments();
                mWeatherItem = bundle.getParcelable("weatherDetail");
            }
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_weather_detail, container, false);
        ButterKnife.bind(this, rootView);

        if (mWeatherItem != null) {
            setDetails();
        }

        return rootView;
    }

    private void setDetails() {

        if (mWeatherItem.getDt_txt() != null) {
            tvDay.setText(Utils.getFormattedDate(mWeatherItem.getDt_txt()));
        }

        MainDetails mainDetails = mWeatherItem.getMain();
        Wind wind = mWeatherItem.getWind();

        if (mainDetails != null) {
            tvCurrentTemp.setText(mainDetails.getTemp() + " C");
            tvMinTemp.setText(mainDetails.getTemp_min() + " C");
            tvMaxTemp.setText(mainDetails.getTemp_max() + " C");

            tvHumidity.setText("" + mainDetails.getHumidity());
            tvSeaLevel.setText("" + mainDetails.getSea_level());
            tvGrndLevel.setText("" + mainDetails.getGrnd_level());
            tvPressure.setText("" + mainDetails.getPressure());
        }

        if (wind != null) {
            tvWind.setText(wind.getSpeed() + " KMH");
        }

        if (mWeatherItem.getWeatherList() != null && mWeatherItem.getWeatherList().size() > 0) {
            Weather weather = mWeatherItem.getWeatherList().get(0);

            if (weather.getIcon() != null) {
                String iconPath = AppConstants.ICON_URL + weather.getIcon() + ".png";
                Picasso.with(mParentActivity).load(iconPath).placeholder(mParentActivity.getResources().getDrawable(R.drawable.ic_thumbnails_loading)).error(mParentActivity.getResources().getDrawable(R.drawable.ic_sun)).into(weatherIcon);
            }

            if (weather.getDescription() != null)
                tvDesc.setText(weather.getMain() + " - " + weather.getDescription());

        }
    }
}
