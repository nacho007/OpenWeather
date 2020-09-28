package com.idd.openweatherapp.di

import android.app.Application
import androidx.room.Room
import com.idd.openweatherapp.db.CurrentWeatherDao
import com.idd.openweatherapp.db.OpenWeatherDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DataBaseModule {

    @Provides
    @Singleton
    fun provideOpenWeatherDataBase(application: Application?): OpenWeatherDataBase? {
        return Room.databaseBuilder(
            application?.applicationContext!!,
            OpenWeatherDataBase::class.java,
            "open_weather_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCurrentWeatherDao(openWeatherDataBase: OpenWeatherDataBase?): CurrentWeatherDao {
        return openWeatherDataBase?.currentWeatherDao()!!
    }
}