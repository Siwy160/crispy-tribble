package com.gwiazdowski.empikweather.ui.weather.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.gwiazdowski.empikweather.R
import com.gwiazdowski.empikweather.databinding.ForecastItemViewBinding
import com.gwiazdowski.model.weather.WeatherItem

class ForecastItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    val binding: ForecastItemViewBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.forecast_item_view, this, true)

    fun render(weatherItem: WeatherItem?) {
        binding.weatherItem = weatherItem
    }
}