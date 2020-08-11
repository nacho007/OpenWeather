package com.idd.openweatherapp.ui.weatherdetail

import android.util.Log
import androidx.annotation.Nullable
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.idd.openweatherapp.repository.CityRepository
import javax.inject.Inject

class FragmentCityWeatherDetailViewModel @ViewModelInject constructor(
    private val cityRepository: CityRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {

//    val user: LiveData<Resource<User>> = Transformations
//        .switchMap(_login){login->
//            if(login == null){
//                AbsentLiveData.create()
//            }else{
//                userRepository.loadUser(login)
//            }
//        }

    fun invokeService(cityName: String) {
        Log.e("asdf","sdf")
//        cityRepository.loadWeather(cityName)
    }
}