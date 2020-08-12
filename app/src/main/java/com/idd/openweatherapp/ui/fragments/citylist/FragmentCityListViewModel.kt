package com.idd.openweatherapp.ui.fragments.citylist

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idd.openweatherapp.db.CurrentWeatherDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragmentCityListViewModel @ViewModelInject constructor(
    private val currentWeatherDao: CurrentWeatherDao,
    @Assisted private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {


    fun consult() {
        viewModelScope.launch(Dispatchers.IO) {
//            val currentWeather = currentWeatherDao.getCurrentWeatherById(2643743)
//            Log.e("currentWeather", currentWeather.toString())

            val currentWeatherList = currentWeatherDao.getCurrentWeatherList()

            currentWeatherList.forEach {
                Log.e("currentWeather", it.toString())
            }

        }
    }

}