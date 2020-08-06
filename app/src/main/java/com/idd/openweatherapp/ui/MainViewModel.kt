package com.idd.openweatherapp.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.idd.openweatherapp.db.OpenWeatherDataBase
import com.idd.openweatherapp.model.CurrentWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by ignaciodeandreisdenis on 8/5/20.
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    fun createCurrentWeather(currentWeather: CurrentWeather) =
        viewModelScope.launch(Dispatchers.IO) {
            val currentWeatherDao =
                OpenWeatherDataBase.getDatabase(context = getApplication(), scope = viewModelScope)
                    .currentWeatherDao()
            currentWeatherDao.insert(currentWeather)
            Log.d("TAG1", "success")
        }

    fun readCurrentWeather() = viewModelScope.launch(Dispatchers.IO) {
        val currentWeatherDao =
            OpenWeatherDataBase.getDatabase(context = getApplication(), scope = viewModelScope)
                .currentWeatherDao()
        val currentWeather = currentWeatherDao.getCurrentWeather()
        Log.d("TAG1", currentWeather?.toString()!!)
    }
}