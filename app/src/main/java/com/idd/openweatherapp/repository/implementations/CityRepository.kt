package com.idd.openweatherapp.repository.implementations

import com.idd.openweatherapp.model.City
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ignaciodeandreisdenis on 8/14/20.
 */
@Singleton
class CityRepository @Inject constructor() {

    fun provideCities(): ArrayList<City> {
        return arrayListOf(
            City(-1, "Current Location"), City(3441575, "Montevideo"),
            City(2643743, "Londres"), City(1688830, "San Pablo"),
            City(3435910, "Buenos Aires"), City(2867714, "Munich")
        )
    }
}
