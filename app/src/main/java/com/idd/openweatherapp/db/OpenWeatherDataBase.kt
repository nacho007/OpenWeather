package com.idd.openweatherapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.idd.openweatherapp.model.CurrentWeather

/**
 * Created by ignaciodeandreisdenis on 8/5/20.
 */
@Database(entities = [CurrentWeather::class], version = 1)
@TypeConverters(Converter::class)
abstract class OpenWeatherDataBase : RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao
}
