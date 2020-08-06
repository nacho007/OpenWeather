package com.idd.openweatherapp.model


import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.idd.openweatherapp.db.Converter

/**
 * Created by ignaciodeandreisdenis on 8/5/20.
 */
@Entity(tableName = "current_weather")
data class CurrentWeather(
    @PrimaryKey
    @field:SerializedName("id")
    val id: Int,
    @field:Embedded
    @field:SerializedName("coord")
    val coord: Coord,
//    @field:Embedded
    @field:SerializedName("weather")
    val weather: ArrayList<Int>?,
    @field:Embedded
    @field:SerializedName("main")
    val main: Main,
    @field:Embedded
    @field:SerializedName("wind")
    val wind: Wind,
    @field:Embedded
    @field:SerializedName("sys")
    val sys: Sys
) {
    data class Coord(
        @ColumnInfo(name = "lon")
        @field:SerializedName("lon")
        val lon: Double,
        @field:SerializedName("lat")
        @ColumnInfo(name = "lat") val lat: Double
    )

//    data class Weather(
//        @ColumnInfo(name = "id_weather")
//        @field:SerializedName("id")
//        val id: Int,
//        @ColumnInfo(name = "main")
//        @field:SerializedName("main")
//        val main: String,
//        @ColumnInfo(name = "description")
//        @field:SerializedName("description")
//        val description: String,
//        @ColumnInfo(name = "icon")
//        @field:SerializedName("icon")
//        val icon: String
//    )

    data class Main(
        @ColumnInfo(name = "temp")
        @field:SerializedName("temp")
        val temp: Double,
        @ColumnInfo(name = "pressure")
        @field:SerializedName("pressure")
        val pressure: Int,
        @ColumnInfo(name = "humidity")
        @field:SerializedName("humidity")
        val humidity: Int,
        @ColumnInfo(name = "tempMin")
        @field:SerializedName("tempMin")
        val tempMin: Double,
        @ColumnInfo(name = "tempMax")
        @field:SerializedName("tempMax")
        val tempMax: Double
    )

    data class Wind(
        @ColumnInfo(name = "speed")
        @field:SerializedName("speed")
        val speed: Double,
        @ColumnInfo(name = "deg")
        @field:SerializedName("deg")
        val deg: Int
    )

    data class Sys(
        @ColumnInfo(name = "country")
        @field:SerializedName("country")
        val country: String,
        @ColumnInfo(name = "sunrise")
        @field:SerializedName("sunrise")
        val sunrise: Long,
        @ColumnInfo(name = "sunset")
        @field:SerializedName("sunset")
        val sunset: Long
    )
}
