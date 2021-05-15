package com.gwiazdowski.empikweather.ui.weather

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.os.ConfigurationCompat
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


@BindingAdapter("weatherIcon")
fun AppCompatImageView.loadIcon(weatherIcon: String?) {
    if (weatherIcon == null) return
    val weatherIconUrl = "https://openweathermap.org/img/wn/$weatherIcon@2x.png"
    Picasso.get()
        .load(weatherIconUrl)
        .into(this)
}

@BindingAdapter("temperature")
fun AppCompatTextView.setTemperature(temperatureKelvin: Float?) {
    text = temperatureKelvin?.let { "${(temperatureKelvin - 273.15f).roundToInt()}°" } ?: ""
}

@BindingAdapter("currentTime")
fun AppCompatTextView.setCurrentTime(time: Date?) {
    if (time == null) return
    val currentLocale = ConfigurationCompat.getLocales(resources.configuration).get(0)
    val format = SimpleDateFormat("EEEE HH:mm", currentLocale)
    text = format.format(time)
}
