package com.idd.openweatherapp.ui.weatherdetail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.idd.openweatherapp.R

class FragmentCityWeatherDetail : Fragment() {

    companion object {
        fun newInstance() =
            FragmentCityWeatherDetail()
    }

    private lateinit var viewModel: FragmentCityWeatherDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_city_weather_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FragmentCityWeatherDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}