package com.idd.openweatherapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

/**
 * Created by ignaciodeandreisdenis on 8/17/20.
 */

object BoundLocationManager {

    fun bindLocationListenerIn(
        lifecycleOwner: LifecycleOwner,
        listener: LocationListener, context: Context
    ) {
        BoundLocationListener(lifecycleOwner, listener, context)
    }

    internal class BoundLocationListener(
        lifecycleOwner: LifecycleOwner,
        private val mListener: LocationListener, private val mContext: Context
    ) : LifecycleObserver {

        private lateinit var fusedLocationClient: FusedLocationProviderClient

        init {
            lifecycleOwner.lifecycle.addObserver(this)
        }

        @SuppressLint("MissingPermission")
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun addLocationListener() {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext)

            Log.d("BoundLocationMgr", "Listener added")

            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location ->
                    mListener.onLocationChanged(location)
                }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun removeLocationListener() {
            Log.d("BoundLocationMgr", "Listener removed")
        }
    }
}

