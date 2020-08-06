package com.idd.openweatherapp.ui.citylist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.idd.openweatherapp.R
import com.idd.openweatherapp.ui.common.FragmentBase

class FragmentCityList : FragmentBase() {

    private lateinit var viewModel: FragmentCityListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_city_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FragmentCityListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}