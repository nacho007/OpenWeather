package com.idd.openweatherapp.ui.fragments.citylist

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.idd.openweatherapp.model.City
import com.idd.openweatherapp.repository.implementations.CityRepository
import kotlinx.coroutines.launch

class FragmentCityListViewModel @ViewModelInject constructor(
    var cityRepository: CityRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _cities = MutableLiveData<List<City>>()

    val cities: LiveData<List<City>> = _cities

    init {
        loadCities()
    }

    private fun loadCities() {
        viewModelScope.launch {
            try {
                _cities.value = cityRepository.provideCities()
            } catch (e: Exception) {
                Log.v("ERROR", e.toString())
            }
        }
    }
}