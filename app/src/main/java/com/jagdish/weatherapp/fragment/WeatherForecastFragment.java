package com.jagdish.weatherapp.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.jagdish.weatherapp.BuildConfig;
import com.jagdish.weatherapp.R;
import com.jagdish.weatherapp.adapter.SimpleDividerItemDecoration;
import com.jagdish.weatherapp.adapter.WeatherForecastAdapter;
import com.jagdish.weatherapp.data.WeatherItem;
import com.jagdish.weatherapp.utility.ConnectionDetector;
import com.jagdish.weatherapp.viewmodel.MainViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherForecastFragment extends BaseFragment {

    private static final String TAG = WeatherForecastFragment.class.getName();

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    private View rootView;
    private MainViewModel mViewModel;

    private Activity mActivity;
    private ConnectionDetector mConnectionDetector;

    List<WeatherItem> mWeatherItemList;
    WeatherForecastAdapter mWeatherForecastAdapter;

    private double currentLat;
    private double currentLng;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_weather_forecast, container, false);
        ButterKnife.bind(this, rootView);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mActivity = getActivity();
        mConnectionDetector = new ConnectionDetector(mActivity);

        setupRecyclerView();
        bindData();

        return rootView;
    }

    private void setupRecyclerView() {
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void bindData() {
        if (mConnectionDetector.isConnectedToInternet()) {

            double lat = 19.033049;
            double lng = 73.029663;
            String units = "metric";
            String appId = BuildConfig.OPEN_WEATHER_MAP_API_KEY;

            progressBar.setVisibility(View.VISIBLE);
            mViewModel.getWeatherDataByLatLng(lat, lng, units, appId).observe(this,
                    new Observer<List<WeatherItem>>() {
                        @Override
                        public void onChanged(@Nullable List<WeatherItem> weatherItemList) {

                            progressBar.setVisibility(View.GONE);

                            if (weatherItemList != null) {
                                mWeatherItemList = weatherItemList;
                                setAdapter();
                            } else {
                                showRetryDialog();
                            }
                        }
                    });
        } else {
            showNoInternetDialog(mActivity);
        }
    }

    private void setAdapter() {
        mWeatherForecastAdapter = new WeatherForecastAdapter(mActivity, this.mWeatherItemList);
        mRecyclerView.setAdapter(mWeatherForecastAdapter);
    }

    private void showRetryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle(mActivity.getResources().getString(R.string.err_weather_info));
        builder.setMessage(mActivity.getResources().getString(R.string.unable_to_fetch_weather_info));
        ;

        AlertDialog dialog = builder.create();
        builder.setPositiveButton((mActivity.getResources().getString(R.string.btn_retry)), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                bindData();
            }
        }).setNegativeButton((mActivity.getResources().getString(R.string.btn_cancel)), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

}
