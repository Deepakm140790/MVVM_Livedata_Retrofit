package com.app.testapp.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Helper class
 */
public final class Helper {

    private Helper() {
    }

    /**
     * This method show the toast message to the user.
     * Purpose of this method is in the feature we need to convert showing toast message to Dialog message.
     * In this case we need to change the one place. It saves a lot of time.
     *
     * @param context
     * @param message
     */
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Check if this device has a camera
     *
     * @param context
     * @return
     */
    public static boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    /**
     * Check whether user have been granted a particular permission or not.
     *
     * @param context
     * @param str
     * @return
     */
    public static boolean checkSelfPermisson(Context context, String str) {
        if (ContextCompat.checkSelfPermission(context, str)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    /**
     * Check whether network is connected or not.
     *
     * @param context
     * @return
     */
    private static boolean isInternetAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * Used to change the Recycler view on change in System Configuration.
     *
     * @param newConfig
     * @param context
     * @param recyclerView
     */
    public static void recyclerViewConfiguration(Configuration newConfig, Context context, RecyclerView recyclerView) {
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        }
    }
}
