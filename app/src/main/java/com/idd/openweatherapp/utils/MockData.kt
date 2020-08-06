package com.idd.openweatherapp.utils

import android.content.Context
import com.google.gson.Gson
import com.idd.openweatherapp.model.CurrentWeather
import java.io.IOException
import java.nio.charset.StandardCharsets

object MockData {

    fun mockCurrentWeather(context: Context): CurrentWeather {
        val currentWeather = loadJSONFromAsset(context, "current_weather2.json")
        return Gson().fromJson(currentWeather, CurrentWeather::class.java)
    }

    private fun loadJSONFromAsset(context: Context, service: String?): String? {
        val json: String
        json = try {
            val `is` = context.assets.open(service!!)
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, StandardCharsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

}