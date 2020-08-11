package com.idd.openweatherapp.model


import androidx.room.*
import com.google.gson.annotations.SerializedName

/**
 * Created by ignaciodeandreisdenis on 8/5/20.
 */
@Entity(
    tableName = "current_weather",
    indices = [Index("id")]
)
data class CurrentWeather(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @field:SerializedName("id")
    val id: Int,
    @field:Embedded
    @field:SerializedName("coord")
    val coord: Coord,
    @field:SerializedName("weather")
    @ColumnInfo(name = "weather")
    val weather: List<Weather> = arrayListOf(),
    @field:Embedded
    @field:SerializedName("main")
    val main: Main,
    @field:Embedded
    @field:SerializedName("wind")
    val wind: Wind,
    @field:Embedded
    @field:SerializedName("sys")
    val sys: Sys,
    @field:SerializedName("name")
    val name: String
) {

    data class Coord(
        @ColumnInfo(name = "lon")
        @field:SerializedName("lon")
        val lon: Double,
        @field:SerializedName("lat")
        @ColumnInfo(name = "lat") val lat: Double
    )

    data class Weather(
        @field:SerializedName("id")
        val id: Int,
        @ColumnInfo(name = "main")
        @field:SerializedName("main")
        val main: String,
        @ColumnInfo(name = "description")
        @field:SerializedName("description")
        val description: String,
        @ColumnInfo(name = "icon")
        @field:SerializedName("icon")
        val icon: String
    )

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
        @field:SerializedName("temp_min")
        val tempMin: Double,
        @ColumnInfo(name = "tempMax")
        @field:SerializedName("temp_max")
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
