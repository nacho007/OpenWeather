package com.idd.openweatherapp.api

import androidx.lifecycle.LiveData
import com.idd.openweatherapp.model.CurrentWeather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/data/2.5/weather")
    fun getWeatherByCityId(
        @Query("id") query: String
    ): LiveData<ApiResponse<CurrentWeather>>

    @GET("/data/2.5/weather")
    fun getWeatherByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): LiveData<ApiResponse<CurrentWeather>>
}