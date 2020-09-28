package com.idd.openweatherapp.ui.fragments.citylist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idd.openweatherapp.R
import com.idd.openweatherapp.model.City
import kotlinx.android.synthetic.main.row_city.view.*


class CityViewHolder(
    view: View,
    cityClickListener: (item: City) -> Unit
) : RecyclerView.ViewHolder(view) {
    private lateinit var city: City

    init {
        view.setOnClickListener { cityClickListener.invoke(city) }
    }

    fun bind(city: City) {
        this.city = city

        if (city.name == itemView.context.getString(R.string.current_location)) {
            itemView.row_city_image_view.visibility = View.VISIBLE
        } else {
            itemView.row_city_image_view.visibility = View.GONE
        }

        itemView.row_city_text_view.text = city.name
    }

    companion object {
        fun create(
            parent: ViewGroup,
            itemClickListener: (item: City) -> Unit
        ): CityViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.row_city, parent, false)

            return CityViewHolder(
                view = view,
                cityClickListener = itemClickListener
            )
        }
    }
}