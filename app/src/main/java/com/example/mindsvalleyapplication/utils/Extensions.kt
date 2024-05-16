package com.example.mindsvalleyapplication.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object Extensions {

    fun Context.isInternetConnected(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }

    }
}