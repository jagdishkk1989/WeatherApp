package com.jagdish.weatherapp.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jagdish.weatherapp.R;


public class BaseFragment extends Fragment {

    private static final String TAG = BaseFragment.class.getName();

    public static void addFragment(Context context, int containerid, Fragment fragment, String tag, String stackName) {
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(containerid, fragment, tag);
        fragmentTransaction.addToBackStack(stackName);
        fragmentTransaction.commit();
    }

    public static void replaceFragment(Context context, int containerid, Fragment fragment, String tag) {
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerid, fragment, tag);
        fragmentTransaction.commit();
    }


    public String getActiveFragment(Context context) {
        if (((AppCompatActivity) context).getSupportFragmentManager().getBackStackEntryCount() == 0) {
            return null;
        }
        String tag = ((AppCompatActivity) context).getSupportFragmentManager()
                .getBackStackEntryAt(getFragmentManager()
                        .getBackStackEntryCount() - 1)
                .getName();
        return tag;
    }


    protected void showNoInternetDialog(Activity activity) {
        try {
            AlertDialog alertDialog = new AlertDialog.Builder(activity).create();

            alertDialog.setTitle(activity.getResources().getString(R.string.title_no_internet));
            alertDialog.setMessage(activity.getResources().getString(R.string.no_internet_message));
            alertDialog.setButton(activity.getResources().getString(R.string.btn_ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            alertDialog.show();
        } catch (Exception e) {
            Log.e(TAG, "Exception show dialog: " + e.getMessage());
        }
    }


//    protected DeviceInfo getDeviceInfo(Activity mParentActivity) {
//        DeviceInfo deviceInfo = new DeviceInfo(mParentActivity);
//        return deviceInfo;
//    }

}

