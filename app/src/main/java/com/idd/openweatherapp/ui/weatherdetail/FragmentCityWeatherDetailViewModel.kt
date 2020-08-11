package com.idd.openweatherapp.ui.weatherdetail

import android.util.Log
import androidx.annotation.Nullable
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.idd.openweatherapp.model.CurrentWeather
import com.idd.openweatherapp.repository.CityRepository
import com.idd.openweatherapp.repository.Resource
import com.idd.openweatherapp.utils.AbsentLiveData
import javax.inject.Inject

class FragmentCityWeatherDetailViewModel @ViewModelInject constructor(
    private val cityRepository: CityRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _cityName = MutableLiveData<String>()

    fun setCityName(cityName: String) {
        if (_cityName.value != cityName) {
            _cityName.value = cityName
        }
    }

    val currentWeather: LiveData<Resource<CurrentWeather>> = Transformations
        .switchMap(_cityName) { cityName ->
            if (cityName == null) {
                AbsentLiveData.create()
            } else {
                cityRepository.loadWeather(cityName)
            }
        }

//    fun invokeService(cityName: String) {
//        Log.e("1", "1")
//        cityRepository.loadWeather(cityName)
//        Log.e("2", "2")
//    }
}