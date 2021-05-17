package com.gwiazdowski.empikweather.ui.weather.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.gwiazdowski.empikweather.R
import com.gwiazdowski.empikweather.databinding.WeatherDetailBinding

class WeatherDetailView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    val binding: WeatherDetailBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.weather_detail, this, true)

}