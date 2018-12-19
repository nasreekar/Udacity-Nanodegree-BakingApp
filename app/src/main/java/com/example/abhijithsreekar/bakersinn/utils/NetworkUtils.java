package com.example.abhijithsreekar.bakersinn.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

final public class NetworkUtils {

    private boolean connected;
    private static NetworkUtils instance;

    public static NetworkUtils getInstance() {
        if (null != instance) {
            return instance;
        } else {
            instance = new NetworkUtils();
        }
        return instance;
    }

    private NetworkUtils() {
    }

    public boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            connected = networkInfo != null && networkInfo.isAvailable() &&
                    networkInfo.isConnected();
            return connected;

        } catch (Exception e) {
            System.out.println("CheckConnectivity Exception: " + e.getMessage());
            Log.v("connectivity", e.toString());
        }
        return connected;
    }
}
