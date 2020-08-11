package com.idd.openweatherapp.ui.citylist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.idd.openweatherapp.R
import com.idd.openweatherapp.model.City
import com.idd.openweatherapp.ui.citylist.adapter.CityAdapter
import com.idd.openweatherapp.ui.citylist.adapter.OnCityPressed
import com.idd.openweatherapp.ui.citylist.adapter.SimpleDividerItemDecoration
import com.idd.openweatherapp.ui.common.FragmentBase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_city_list.*

@AndroidEntryPoint
class FragmentCityList : FragmentBase(), OnCityPressed {

    private val viewModel: FragmentCityListViewModel by viewModels()

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
            arrayListOf(
                City(3441575, "Montevideo"), City(2643743, "Londres"),
                City(1688830, "San Pablo"), City(3435910, "Buenos Aires"),
                City(2867714, "Munich")
            ),
            this
        )

        fragment_city_list_recycler_view.adapter = cityAdapter

        button_consult?.setOnClickListener {
            viewModel.consult()
        }
    }

    override fun onCityPressed(city: City?) {
        val action =
            FragmentCityListDirections.actionFragmentCityListToFragmentCityWeatherDetail(
                city ?: City(1, "no_city")
            )
        findNavController().navigate(action)
    }

}