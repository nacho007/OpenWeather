package com.idd.openweatherapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.idd.openweatherapp.model.CurrentWeather
import kotlinx.coroutines.CoroutineScope

/**
 * Created by ignaciodeandreisdenis on 8/5/20.
 */
@Database(entities = [CurrentWeather::class], version = 1)
@TypeConverters(Converter::class)
abstract class OpenWeatherDataBase : RoomDatabase() {

    abstract fun currentWeatherDao(): CurrentWeatherDao

    companion object {
        @Volatile
        private var INSTANCE: OpenWeatherDataBase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): OpenWeatherDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE
                ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        OpenWeatherDataBase::class.java,
                        "open_weather_database"
                    )
                        // Wipes and rebuilds instead of migrating if no Migration object.
                        // Migration is not part of this codelab.
                        .fallbackToDestructiveMigration()
                        .addCallback(OpenWeatherDataBaseCallback())
                        .build()
                    INSTANCE = instance
                    // return instance
                    instance
                }
        }

        private class OpenWeatherDataBaseCallback : RoomDatabase.Callback()

    }
}
