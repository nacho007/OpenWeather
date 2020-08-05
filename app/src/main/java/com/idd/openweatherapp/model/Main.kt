package com.idd.openweatherapp.model

/**
 * Created by ignaciodeandreisdenis on 8/5/20.
 */
data class Main(
    val temp: Double,
    val pressure: Int,
    val humidity: Int,
    val tempMin: Double,
    val tempMax: Double
)