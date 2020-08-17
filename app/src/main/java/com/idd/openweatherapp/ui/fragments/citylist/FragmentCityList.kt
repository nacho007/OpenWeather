package com.idd.openweatherapp.ui.fragments.citylist


import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.idd.openweatherapp.R
import com.idd.openweatherapp.model.City
import com.idd.openweatherapp.repository.implementations.CURRENT_LOCATION_ID
import com.idd.openweatherapp.repository.implementations.CityRepository
import com.idd.openweatherapp.ui.common.FragmentBase
import com.idd.openweatherapp.ui.fragments.citylist.adapter.CitiesAdapter
import com.idd.openweatherapp.ui.fragments.citylist.adapter.SimpleDividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_city_list.*
import javax.inject.Inject

@AndroidEntryPoint
class FragmentCityList : FragmentBase() {

    companion object {
        private const val CURRENT_LOCATION = -1
        private const val MY_PERMISSIONS_REQUEST_LOCATION = 3000
    }

    @Inject
    lateinit var cityRepository: CityRepository

    private val viewModel: FragmentCityListViewModel by viewModels()

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

    private fun navigateToWeatherDetails(city: City?) {
        val action =
            FragmentCityListDirections.actionFragmentCityListToFragmentCityWeatherDetail(
                city ?: City(CURRENT_LOCATION, "Current Location")
            )
        findNavController().navigate(action)
    }

    private fun onCitySelected(city: City?) {
        if (city?.id == CURRENT_LOCATION_ID) {
            checkPermissions()
        } else {
            navigateToWeatherDetails(city)
        }
    }

    private fun checkPermissions() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                navigateToWeatherDetails(null)
            }
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION) -> {
                val snackBar = Snackbar.make(
                    requireView(),
                    getString(R.string.permissions),
                    Snackbar.LENGTH_LONG
                )
                val snackBarView = snackBar.view
                val snackBarTextView =
                    snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
                snackBarTextView.maxLines = 4
                snackBarTextView.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.color_0
                    )
                )

                val snackBarActionTextView =
                    snackBarView.findViewById(com.google.android.material.R.id.snackbar_action) as TextView
                snackBarActionTextView.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.color_0
                    )
                )

                snackBar.setAction(getString(R.string.accept)) {
                    requestLocationPermission()
                }

                snackBarView.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.colorPrimary
                    )
                )

                snackBar.show()
            }
            else -> {
                requestLocationPermission()
            }
        }
    }

    private fun requestLocationPermission() {
        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
            MY_PERMISSIONS_REQUEST_LOCATION
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    navigateToWeatherDetails(null)
                }
                return
            }
        }
    }


    private class MyLocationListener : LocationListener {
        override fun onLocationChanged(location: Location) {
//            val textView: TextView = findViewById<TextView>(R.id.location)
//            textView.text = location.latitude.toString() + ", " + location.longitude
        }

        override fun onStatusChanged(
            provider: String,
            status: Int,
            extras: Bundle
        ) {
        }

        override fun onProviderEnabled(provider: String) {
//            Toast.makeText(
//                this@LocationActivity,
//                "Provider enabled: $provider", Toast.LENGTH_SHORT
//            ).show()
        }

        override fun onProviderDisabled(provider: String) {}
    }

}