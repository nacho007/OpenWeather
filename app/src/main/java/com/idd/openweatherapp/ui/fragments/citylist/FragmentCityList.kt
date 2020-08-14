package com.idd.openweatherapp.ui.fragments.citylist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.idd.openweatherapp.R
import com.idd.openweatherapp.model.City
import com.idd.openweatherapp.repository.implementations.CityRepository
import com.idd.openweatherapp.ui.common.FragmentBase
import com.idd.openweatherapp.ui.fragments.citylist.adapter.CitiesAdapter
import com.idd.openweatherapp.ui.fragments.citylist.adapter.SimpleDividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_city_list.*
import javax.inject.Inject

@AndroidEntryPoint
class FragmentCityList : FragmentBase() {

    @Inject
    lateinit var cityRepository: CityRepository

    private val viewModel: FragmentCityListViewModel by viewModels()

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var citiesAdapter: CitiesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_city_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
//
//        fusedLocationClient.lastLocation
//            .addOnSuccessListener { location : Location? ->
//                 Got last known location. In some rare situations this can be null.
//            }

        fragment_city_list_recycler_view.apply {
            citiesAdapter = CitiesAdapter { city -> onCitySelected(city) }
            layoutManager = LinearLayoutManager(context)
            adapter = citiesAdapter
            setHasFixedSize(true)
            addItemDecoration(
                SimpleDividerItemDecoration(
                    ContextCompat.getDrawable(context, R.drawable.shape_divider_list_item)
                )
            )
        }

        viewModel.cities.observe(viewLifecycleOwner, Observer {
            citiesAdapter.submitList(it)
        })
    }

    private fun onCitySelected(city: City?) {
        val action =
            FragmentCityListDirections.actionFragmentCityListToFragmentCityWeatherDetail(
                city ?: City(1, "no_city")
            )
        findNavController().navigate(action)
    }

}