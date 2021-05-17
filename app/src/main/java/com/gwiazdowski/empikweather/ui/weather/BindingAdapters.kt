package com.gwiazdowski.empikweather.ui.weather

import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.os.ConfigurationCompat
import androidx.databinding.BindingAdapter
import com.gwiazdowski.empikweather.R
import com.gwiazdowski.empikweather.ui.common.getTemperatureString
import com.gwiazdowski.empikweather.ui.common.kelvinToCelsius
import com.gwiazdowski.empikweather.ui.weather.view.ForecastItemView
import com.gwiazdowski.empikweather.ui.weather.view.WeatherDetailView
import com.gwiazdowski.model.weather.WeatherItem
import com.squareup.picasso.Picasso
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


@BindingAdapter("weatherIcon")
fun AppCompatImageView.loadIcon(weatherIcon: String?) {
    if (weatherIcon == null) return
    val weatherIconUrl = "https://openweathermap.org/img/wn/$weatherIcon@4x.png"
    Picasso.get()
        .load(weatherIconUrl)
        .into(this)
}

@BindingAdapter("temperature")
fun AppCompatTextView.setTemperature(temperatureKelvin: Float?) {
    if (temperatureKelvin == null || temperatureKelvin == 0f) return
    val temperatureCelsius = temperatureKelvin.kelvinToCelsius()
    val fontColor = when {
        temperatureCelsius > 20 -> ContextCompat.getColor(context, R.color.hot_temperature_color)
        temperatureCelsius < 10 -> ContextCompat.getColor(context, R.color.cold_temperature_color)
        else -> ContextCompat.getColor(context, R.color.black)
    }
    setTextColor(fontColor)
    text = temperatureKelvin.getTemperatureString()
}

@BindingAdapter("currentTime")
fun AppCompatTextView.setCurrentTime(time: Date?) {
    if (time == null) return
    val currentLocale = ConfigurationCompat.getLocales(resources.configuration).get(0)
    val format = SimpleDateFormat("HH:mm", currentLocale)
    text = format.format(time)
}

@BindingAdapter("value", "title", "icon", "unitType", requireAll = true)
fun WeatherDetailView.render(value: Float, title: String, icon: Drawable, unitType: UnitType) {
    val text = when (unitType) {
        UnitType.CELSIUS -> value.getTemperatureString()
        UnitType.SPEED -> {
            val df = DecimalFormat("#.#")
            "${df.format(value)} m/s"
        }
        UnitType.PERCENT -> "${value.roundToInt()}%"
    }
    with(binding) {
        detailTitle = title
        detailValue = text
        detailIcon = icon
    }
}

enum class UnitType {
    CELSIUS,
    SPEED,
    PERCENT;
}

@BindingAdapter("render")
fun ForecastItemView.render(weatherItem: WeatherItem?) {
    render(weatherItem)
}