package com.idd.openweatherapp.di

import com.idd.openweatherapp.api.WeatherApi
import com.idd.openweatherapp.constants.API_KEY
import com.idd.openweatherapp.utils.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by ignaciodeandreisdenis on 8/10/20.
 */

@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideClient(interceptor: Interceptor): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(interceptor)
            .connectTimeout(20L, TimeUnit.MINUTES)
            .readTimeout(20L, TimeUnit.MINUTES)
            .writeTimeout(20L, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherApi(client: OkHttpClient): WeatherApi {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRequestInterceptor(apiKey: String): Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request()

                val url = request.url.newBuilder()
                    .addQueryParameter("appid", apiKey)
                    .addQueryParameter("units", "metric")
                    .build()

                val newRequest = request.newBuilder()
                    .url(url)
                    .build()

                return chain.proceed(newRequest)
            }
        }
    }

    @Provides
    @Singleton
    fun provideApiKey(): String {
        return API_KEY
    }
}
