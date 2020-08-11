package com.idd.openweatherapp.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.idd.openweatherapp.R
import com.idd.openweatherapp.constants.DELAY
import com.idd.openweatherapp.ui.common.FragmentBase
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentSplash : FragmentBase() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onResume() {
        super.onResume()
        mainThreadHandler.postDelayed(runnable, DELAY)
    }

    override fun onPause() {
        super.onPause()
        mainThreadHandler.removeCallbacks(runnable)
    }

    private val mainThreadHandler = Handler(Looper.getMainLooper())
    private val runnable = Runnable {
        findNavController().navigate(R.id.action_fragmentSplash_to_fragmentCityList)
    }
}