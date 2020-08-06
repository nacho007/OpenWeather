package com.idd.openweatherapp.db

import androidx.room.TypeConverter
import com.google.gson.Gson

object Converter {

    @TypeConverter
    @JvmStatic
    fun fromString(value: String?): ArrayList<Int>? {
        val list: ArrayList<Int> = ArrayList()
        val array = value?.split(",")

        array?.forEach {
            list.add(it.toInt())
        }

        return list
    }

    @TypeConverter
    @JvmStatic
    fun fromArrayList(list: ArrayList<Int>?): String? {
        return list?.joinToString ( "," )
    }
}