package com.idd.openweatherapp.ui.citylist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.idd.openweatherapp.R
import com.idd.openweatherapp.ui.citylist.adapter.CityAdapter
import com.idd.openweatherapp.ui.citylist.adapter.OnCityPressed
import com.idd.openweatherapp.ui.citylist.adapter.SimpleDividerItemDecoration
import com.idd.openweatherapp.ui.common.FragmentBase
import kotlinx.android.synthetic.main.fragment_city_list.*

class FragmentCityList : FragmentBase(), OnCityPressed {

    val viewModel: FragmentCityListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_city_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fragment_city_list_recycler_view.setHasFixedSize(true)
        fragment_city_list_recycler_view.layoutManager = LinearLayoutManager(context)

        context?.let {
            val dividerDrawable = ContextCompat.getDrawable(it, R.drawable.shape_divider_list_item)
            fragment_city_list_recycler_view.addItemDecoration(
                SimpleDividerItemDecoration(
                    dividerDrawable
                )
            )
        }

        val cityAdapter = CityAdapter(
            arrayListOf("Montevideo", "Londres", "San Pablo", "Buenos Aires", "Munich"),
            this
        )

        fragment_city_list_recycler_view.adapter = cityAdapter
    }

    override fun onCityPressed(city: String?) {
        val action =
            FragmentCityListDirections.actionFragmentCityListToFragmentCityWeatherDetail(
                city ?: "No City"
            )
        findNavController().navigate(action)
    }

}