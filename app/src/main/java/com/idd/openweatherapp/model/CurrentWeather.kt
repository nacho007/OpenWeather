package com.idd.openweatherapp.model


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by ignaciodeandreisdenis on 8/5/20.
 */
@Entity(tableName = "current_weather")
data class CurrentWeather(
    @PrimaryKey
    val id: Int,
    @field:Embedded()
    val coord: Coord
//    val weather: Weather,
//    val main: Main,
//    val wind: Wind,
//    val sys: Sys
)
