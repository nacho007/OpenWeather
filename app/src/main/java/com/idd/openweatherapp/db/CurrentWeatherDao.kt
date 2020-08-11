package com.idd.openweatherapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.idd.openweatherapp.model.CurrentWeather

@Dao
interface CurrentWeatherDao {

    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    @Query("SELECT * from current_weather WHERE id = :id")
    fun getCurrentWeatherById(id: Int): LiveData<CurrentWeather>

    @Query("SELECT * from current_weather ORDER BY id ASC")
    fun getCurrentWeatherList(): List<CurrentWeather>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currentWeather: CurrentWeather)

    @Query("DELETE FROM current_weather")
    fun deleteAll()
}