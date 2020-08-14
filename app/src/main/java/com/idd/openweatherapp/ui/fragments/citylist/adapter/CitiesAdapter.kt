package com.idd.openweatherapp.ui.fragments.citylist.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.idd.openweatherapp.model.City

/**
 * Created by ignaciodeandreisdenis on 8/14/20.
 */
class CitiesAdapter(
    private val cityClickListener: (city: City) -> Unit
) : ListAdapter<City, CityViewHolder>(
    DiffCityCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder =
        CityViewHolder.create(
            parent,
            cityClickListener
        )

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) =
        holder.bind(getItem(position))
}

class DiffCityCallback : DiffUtil.ItemCallback<City>() {
    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem.id == newItem.id
    }
}
