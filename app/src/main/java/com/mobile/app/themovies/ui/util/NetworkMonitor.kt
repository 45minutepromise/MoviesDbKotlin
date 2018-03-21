package com.mobile.app.themovies.ui.util

import android.net.ConnectivityManager
import javax.inject.Inject

class NetworkMonitor @Inject constructor(private val connectivityManager: ConnectivityManager){
    fun isConnected(): Boolean {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo!= null && networkInfo.isConnectedOrConnecting
    }
}