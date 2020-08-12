package com.idd.openweatherapp.ui.fragments.weatherdetail

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.idd.openweatherapp.R
import kotlinx.android.synthetic.main.custom_weather_item.view.*

/**
 * Created by ignaciodeandreisdenis on 8/11/20.
 */
class CustomWeatherItemView : LinearLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        inflate(context, R.layout.custom_weather_item, this)
        val title: TextView = findViewById(R.id.custom_weather_title)
        val value: TextView = findViewById(R.id.custom_weather_value)

        attrs?.let {
            val attributes = context.obtainStyledAttributes(it, R.styleable.CustomWeatherItemView)
            title.text = attributes.getString(R.styleable.CustomWeatherItemView_title)
            value.text = attributes.getString(R.styleable.CustomWeatherItemView_value)

            attributes.recycle()
        }
    }

    fun setValue(text: String) {
        custom_weather_value.text = text
    }
}
