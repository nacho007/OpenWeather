package com.idd.openweatherapp.utils

import android.content.Context
import com.idd.openweatherapp.R
import com.idd.openweatherapp.model.City
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ignaciodeandreisdenis on 8/14/20.
 */
@Singleton
class CityProvider @Inject constructor() {

    fun provideCities(context: Context): ArrayList<City> {
        return arrayListOf(
            City(-1, context.getString(R.string.current_location)), City(3441575, "Montevideo"),
            City(2643743, "Londres"), City(1688830, "San Pablo"),
            City(3435910, "Buenos Aires"), City(2867714, "Munich")
        )
    }
}
