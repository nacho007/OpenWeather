package com.idd.openweatherapp.ui.activities

import android.app.Application
import androidx.lifecycle.AndroidViewModel

/**
 * Created by ignaciodeandreisdenis on 8/5/20.
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

//    fun createCurrentWeather(currentWeather: CurrentWeather) =
//        viewModelScope.launch(Dispatchers.IO) {
//            val currentWeatherDao =
//                OpenWeatherDataBase.getDatabase(context = getApplication(), scope = viewModelScope)
//                    .currentWeatherDao()
//            currentWeatherDao.insert(currentWeather)
//            Log.d("TAG1", "success")
//        }
//
//    fun readCurrentWeather() = viewModelScope.launch(Dispatchers.IO) {
//        val currentWeatherDao =
//            OpenWeatherDataBase.getDatabase(context = getApplication(), scope = viewModelScope)
//                .currentWeatherDao()
//        val currentWeather = currentWeatherDao.getCurrentWeather()
//        Log.d("TAG1", currentWeather?.toString()!!)
//    }
}
