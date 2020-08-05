package com.idd.openweatherapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.idd.openweatherapp.model.CurrentWeather
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by ignaciodeandreisdenis on 8/5/20.
 */
@Database(entities = [CurrentWeather::class], version = 1)
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
                        .addCallback(OpenWeatherDataBaseCallback(scope))
                        .build()
                    INSTANCE = instance
                    // return instance
                    instance
                }
        }

        private class OpenWeatherDataBaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.currentWeatherDao())
                    }
                }
            }
        }

        fun populateDatabase(currentWeatherDao: CurrentWeatherDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            currentWeatherDao.deleteAll()
        }
    }
}
