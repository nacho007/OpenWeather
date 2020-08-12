package com.idd.openweatherapp.repository.implementations

import android.util.Log
import androidx.lifecycle.LiveData
import com.idd.openweatherapp.api.ApiResponse
import com.idd.openweatherapp.api.WeatherApi
import com.idd.openweatherapp.db.CurrentWeatherDao
import com.idd.openweatherapp.model.City
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
class CityRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherApi: WeatherApi
) {
    fun loadWeather(city: City): LiveData<Resource<CurrentWeather>> {
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
                return currentWeatherDao.getCurrentWeatherById(city.id)
            }

            override fun createCall(): LiveData<ApiResponse<CurrentWeather>> {
                return weatherApi.getWeatherByCityId(city.id.toString())
            }
        }.asLiveData()
    }
}