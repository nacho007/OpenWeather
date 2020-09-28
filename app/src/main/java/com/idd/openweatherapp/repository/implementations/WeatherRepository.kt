package com.idd.openweatherapp.repository.implementations

import android.util.Log
import androidx.lifecycle.LiveData
import com.idd.openweatherapp.api.ApiResponse
import com.idd.openweatherapp.api.WeatherApi
import com.idd.openweatherapp.db.CurrentWeatherDao
import com.idd.openweatherapp.model.CurrentWeather
import com.idd.openweatherapp.repository.common.AppExecutors
import com.idd.openweatherapp.repository.common.NetworkBoundResource
import com.idd.openweatherapp.repository.common.Resource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ignaciodeandreisdenis on 8/10/20.
 */
@Singleton
class WeatherRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherApi: WeatherApi
) {
    fun loadWeather(cityId: Int): LiveData<Resource<CurrentWeather>> {
        return object : NetworkBoundResource<CurrentWeather, CurrentWeather>(appExecutors) {
            override fun saveCallResult(item: CurrentWeather) {
                currentWeatherDao.insert(item)
            }

            override fun shouldFetch(data: CurrentWeather?): Boolean {
                Log.e("shouldFetch", data.toString())
//                return data == null
                return true
            }

            override fun loadFromDb(): LiveData<CurrentWeather> {
                return currentWeatherDao.getCurrentWeatherById(cityId)
            }

            override fun createCall(): LiveData<ApiResponse<CurrentWeather>> {
                return weatherApi.getWeatherByCityId(cityId.toString())
            }
        }.asLiveData()
    }

    fun loadWeatherByCoordinates(
        cityId: Int,
        lat: Double,
        lon: Double
    ): LiveData<Resource<CurrentWeather>> {
        return object : NetworkBoundResource<CurrentWeather, CurrentWeather>(appExecutors) {
            override fun saveCallResult(item: CurrentWeather) {
                item.id = cityId
                currentWeatherDao.insert(item)
            }

            override fun shouldFetch(data: CurrentWeather?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<CurrentWeather> {
                return currentWeatherDao.getCurrentWeatherById(cityId)
            }

            override fun createCall(): LiveData<ApiResponse<CurrentWeather>> {
                return weatherApi.getWeatherByCoordinates(lat, lon)
            }
        }.asLiveData()
    }
}