package com.gwiazdowski.empikweather.ui.weather

import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.os.ConfigurationCompat
import androidx.databinding.BindingAdapter
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.gwiazdowski.empikweather.R
import com.gwiazdowski.empikweather.ui.common.getTemperatureString
import com.gwiazdowski.empikweather.ui.common.kelvinToCelsius
import com.gwiazdowski.empikweather.ui.weather.background.SkyColorProvider
import com.gwiazdowski.model.weather.WeatherItem
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*


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
    val format = SimpleDateFormat("EEEE HH:mm", currentLocale)
    text = format.format(time)
}


@BindingAdapter("renderData")
fun LineChart.renderData(forecast: List<WeatherItem>?) {
    if (forecast == null) return
    val hoursAhead = 6
    var maxTemp = 0f
    val dataSet = LineDataSet(
        forecast
            .take(hoursAhead)
            .map { weather ->
                if (maxTemp < weather.temperatureKelvin) {
                    maxTemp = weather.temperatureKelvin
                }
                Entry(
                    weather.time.time.toFloat(),
                    weather.temperatureKelvin,
                    weather.time
                )
            },
        ""
    ).apply {
        val defaultColor = context.getColor(R.color.black)
        val plotColor = context.getColor(R.color.dark_grey)

        description.isEnabled = false
        legend.isEnabled = false
        setDrawValues(true)
        setDrawGridBackground(false)
        setDrawHighlightIndicators(false)
        xAxis.setLabelCount(hoursAhead, true)
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        xAxis.valueFormatter = object : ValueFormatter() {
            val timeFormat = SimpleDateFormat("k a")

            override fun getFormattedValue(value: Float): String = timeFormat.format(Date(value.toLong()))
        }
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)

        valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String = value.getTemperatureString()
        }
        listOf(axisLeft, axisRight).forEach {
            it.setDrawLabels(false)
            it.setDrawZeroLine(false)
            it.setDrawTopYLabelEntry(false)
            it.setDrawAxisLine(false)
            it.setDrawGridLines(false)
        }
        valueTextSize = 16f
        valueTextColor = defaultColor
        xAxis.textColor = defaultColor
        mode = LineDataSet.Mode.CUBIC_BEZIER
        setPinchZoom(false)
        color = plotColor

        lineWidth = 3f
        circleRadius = 6f
        setCircleColor(plotColor)
        circleHoleColor = plotColor
    }
    data = LineData(dataSet)
    invalidate()
}

@BindingAdapter("renderBackgroundGradient")
fun View.renderBackgroundGradient(weather: WeatherItem?) {
    if (weather == null) return
    val skyColor = SkyColorProvider(context).getColorForTimeOfDay(weather.time, weather.sunrise, weather.sunset)
    val gradient = GradientDrawable(
        GradientDrawable.Orientation.TOP_BOTTOM,
        intArrayOf(skyColor.top, skyColor.bottom)
    )
    background = gradient
}