package com.idd.openweatherapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.idd.openweatherapp.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        activity_main_create_button?.setOnClickListener {
            mainViewModel.createCurrentWeather()
        }

        activity_main_see_button?.setOnClickListener {
            mainViewModel.readCurrentWeather()
        }
    }
}