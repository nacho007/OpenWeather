package com.idd.openweatherapp.ui.fragments.weatherdetail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.idd.openweatherapp.model.City
import com.idd.openweatherapp.model.CurrentWeather
import com.idd.openweatherapp.repository.common.Resource
import com.idd.openweatherapp.repository.implementations.WeatherRepository
import com.idd.openweatherapp.utils.AbsentLiveData

class FragmentCityWeatherDetailViewModel @ViewModelInject constructor(
    private val weatherRepository: WeatherRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _city = MutableLiveData<City>()

    fun setCityName(city: City) {
        if (_city.value != city) {
            _city.value = city
        }
    }

    fun retry() {
        _city.value?.let {
            _city.value = it
        }
    }

    val currentWeather: LiveData<Resource<CurrentWeather>> = Transformations
        .switchMap(_city) { city ->
            if (city == null) {
                AbsentLiveData.create()
            } else {
                weatherRepository.loadWeather(city)
            }
        }

}