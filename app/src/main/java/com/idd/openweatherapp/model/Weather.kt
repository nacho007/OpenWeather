package com.idd.openweatherapp.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

/**
 * Created by ignaciodeandreisdenis on 8/6/20.
 */
data class Weather(
    @ColumnInfo(name = "id_weather")
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
