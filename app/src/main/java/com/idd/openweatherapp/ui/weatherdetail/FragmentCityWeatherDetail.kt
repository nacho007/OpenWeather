package com.idd.openweatherapp.ui.weatherdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.idd.openweatherapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_city_weather_detail.*


@AndroidEntryPoint
class FragmentCityWeatherDetail : Fragment() {

    private val viewModel: FragmentCityWeatherDetailViewModel by viewModels()

    private val args: FragmentCityWeatherDetailArgs by navArgs()
    lateinit var cityName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_city_weather_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        cityName = args.cityName
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.setCityName(cityName)

        viewModel.currentWeather.observe(viewLifecycleOwner, Observer { userResource ->
            Log.e("sdf", "sdf")
        })

    }
}