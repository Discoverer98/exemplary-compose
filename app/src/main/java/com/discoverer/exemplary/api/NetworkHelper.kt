package com.discoverer.exemplary.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities


/**
 * Helper class that allows us to find out if the device is currently online or not.
 *
 * @param context The traditional Android Context object, because we need access to the Connectivity service, which is accessed via the Context.
 */
class NetworkHelper(private val context: Context) {

    /**
     * Method that will tell us if the device is online or not.
     *
     * @return True if the device is online, false if not.
     */
    fun isNetworkConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

        return activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    }

} // NetworkHelper class
