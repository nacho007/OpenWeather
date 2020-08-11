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
import com.idd.openweatherapp.model.CurrentWeather
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_city_weather_detail.*
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


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

        viewModel.currentWeather.observe(viewLifecycleOwner, Observer { currentWeather ->
            setDetails(currentWeather.data)
        })
    }

    private fun setDetails(currentWeather: CurrentWeather?) {
        Log.e("Updating", currentWeather?.name ?: "null")

        currentWeather?.let {
            fragment_city_weather_detail_city_name_text_view.text = currentWeather.name

            fragment_city_weather_detail_weather_description_text_view.text =
                currentWeather.weather[0].description

            fragment_city_weather_detail_temperature_text_view.text =
                currentWeather.main.temp.toString()

            fragment_city_weather_detail_min_temperature_text_view.text =
                getString(
                    R.string.min_temp,
                    decimalFormatOnlyShowDecimalIfNotZero?.format(currentWeather.main.tempMin)
                )

            fragment_city_weather_detail_max_temperature_text_view.text =
                getString(
                    R.string.max_temp,
                    decimalFormatOnlyShowDecimalIfNotZero?.format(currentWeather.main.tempMax)
                )

            fragment_city_weather_detail_wind.setValue(
                getString(
                    R.string.speed_value,
                    currentWeather.wind.speed.toString()
                )
            )

            fragment_city_weather_detail_humidity.setValue(
                getString(
                    R.string.humidity_value,
                    currentWeather.main.humidity
                )
            )

            fragment_city_weather_detail_sunrise.setValue(
                getDateTime(
                    currentWeather.sys.sunrise
                )
            )

            fragment_city_weather_detail_sunset.setValue(
                getDateTime(
                    currentWeather.sys.sunset
                )
            )
        }

    }

    private var nf: DecimalFormat? = null
    private val decimalFormatOnlyShowDecimalIfNotZero: DecimalFormat?
        get() {
            if (nf == null) {
                nf = DecimalFormat()
                nf?.isDecimalSeparatorAlwaysShown = false
            }
            return nf
        }


    private fun getDateTime(date: Long): String {
        val simpleDateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return simpleDateFormat.format(Date(date * 1000))
    }
}