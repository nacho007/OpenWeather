package com.idd.openweatherapp.ui.citylist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idd.openweatherapp.R
import kotlinx.android.synthetic.main.row_city.view.*

/**
 * Created by ignaciodeandreisdenis on 8/6/20.
 */

class CityAdapter(
    private val cities: ArrayList<String>?,
    private val onCityPressed: OnCityPressed
) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_city, parent, false)
        return CityViewHolder(v)
    }

    override fun getItemCount(): Int {
        return cities?.size ?: 0
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.setItem(cities?.get(position))
        holder.bind(city = cities?.get(position), onCityPressed = onCityPressed)
    }

    class CityViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {

        fun setItem(city: String?) {
            v.row_city_text_view.text = city
        }

        fun bind(city: String?, onCityPressed: OnCityPressed) {
            itemView.setOnClickListener {
                onCityPressed.onCityPressed(city)
            }
        }
    }
}
