package com.idd.openweatherapp.ui.fragments.weatherdetail

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.idd.openweatherapp.R
import com.idd.openweatherapp.constants.CURRENT_LOCATION_ID
import com.idd.openweatherapp.databinding.FragmentCityWeatherDetailBinding
import com.idd.openweatherapp.model.City
import com.idd.openweatherapp.model.CurrentWeather
import com.idd.openweatherapp.repository.common.RetryCallback
import com.idd.openweatherapp.utils.BoundLocationManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_city_weather_detail.*
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class FragmentCityWeatherDetail : Fragment(), LocationListener {

    private val viewModel: FragmentCityWeatherDetailViewModel by viewModels()

    private val args: FragmentCityWeatherDetailArgs by navArgs()
    lateinit var city: City

    private lateinit var binding: FragmentCityWeatherDetailBinding

    private lateinit var mGpsListener: LocationListener

    private fun bindLocationListener() {
        BoundLocationManager.bindLocationListenerIn(this, mGpsListener, requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_city_weather_detail,
                container,
                false
            )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        city = args.city

        mGpsListener = this

        if (city.id == CURRENT_LOCATION_ID) {
            bindLocationListener()
        } else {
            viewModel.setCityName(city)
        }

        viewModel.currentWeather.observe(viewLifecycleOwner, Observer { currentWeather ->
            setDetails(currentWeather.data)
        })

        binding.lifecycleOwner = viewLifecycleOwner

        binding.retryCallback = object : RetryCallback {
            override fun retry() {
                viewModel.retry()
            }
        }

        binding.weatherResource = viewModel
    }

    private fun setDetails(currentWeather: CurrentWeather?) {
        Log.e("Updating", currentWeather?.name ?: "null")

        currentWeather?.let {
            Glide
                .with(this)
                .load(getIcon(currentWeather.weather[0].icon))
                .centerCrop()
                .placeholder(R.drawable.svg_splash_icon)
                .into(fragment_city_weather_detail_image_view)

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

    override fun onLocationChanged(location: Location) {
        viewModel.setLocation(location)
        viewModel.setCityName(city)
    }

    private fun getIcon(icon: String): String {
        return "http://openweathermap.org/img/wn/${icon}@2x.png"
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