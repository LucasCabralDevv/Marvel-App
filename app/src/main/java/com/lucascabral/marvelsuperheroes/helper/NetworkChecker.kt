package com.lucascabral.marvelsuperheroes.helper

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class NetworkChecker(private val connectivityManager: ConnectivityManager?) {

    fun performActionIfConnected(action: () -> Unit) {
        if (hasInternet()) action()
    }

    private fun hasInternet(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager?.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
        } else {
            val activeNetworkInfo = connectivityManager?.activeNetworkInfo
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI
                        || activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE
            }
            false
        }
    }
}