package com.idd.openweatherapp.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.idd.openweatherapp.model.CurrentWeather

object Converter {
    @TypeConverter
    @JvmStatic
    fun listToJson(value: List<CurrentWeather.Weather>?): String = Gson().toJson(value)

    @TypeConverter
    @JvmStatic
    fun jsonToList(value: String) =
        Gson().fromJson(value, Array<CurrentWeather.Weather>::class.java).toList()
}