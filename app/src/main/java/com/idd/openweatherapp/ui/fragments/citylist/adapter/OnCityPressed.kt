package com.idd.openweatherapp.ui.fragments.citylist.adapter

import com.idd.openweatherapp.model.City

interface OnCityPressed {
    fun onCityPressed(city: City?)
}