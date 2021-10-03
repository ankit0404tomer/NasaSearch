package com.example.nasaimages.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build


object AppUtils {

    const val BASE_URL = "https://api.nasa.gov/planetary/"
    const val NASA_IMAGE_URL = "apod?api_key=fxZ0g7n9cWntEcQQCV20tsgCRFzA6UcxgKsLHQdB&date="
    const val WIFI = "WIFI"
    const val MOBILE = "MOBILE"


    fun hasInternet(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
                ?: return false
            return when {
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            var haveConnectedWifi = false
            var haveConnectedMobile = false
            val netInfo = connectivityManager.allNetworkInfo
            for (ni in netInfo) {
                if (ni.typeName.equals(WIFI, ignoreCase = true)) if (ni.isConnected) haveConnectedWifi = true
                if (ni.typeName.equals(MOBILE, ignoreCase = true)) if (ni.isConnected) haveConnectedMobile = true
            }
            return haveConnectedWifi || haveConnectedMobile
        }


    }

}
