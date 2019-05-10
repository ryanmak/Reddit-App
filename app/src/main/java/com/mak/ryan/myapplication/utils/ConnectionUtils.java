package com.mak.ryan.myapplication.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionUtils {

    public static final int READ_TIMEOUT = 10000; // time is in milliseconds

    public static final int CONNECT_TIMEOUT = 15000; // time is in milliseconds

    public static String BASE_URL = "https://www.reddit.com/r/all/.json";

    // Check to see if user is connected to the internet
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (cm != null) {
            networkInfo = cm.getActiveNetworkInfo();
        }

        // if no network is available networkInfo will be null
        // otherwise check if we are connected
        return networkInfo != null && networkInfo.isConnected();
    }
}