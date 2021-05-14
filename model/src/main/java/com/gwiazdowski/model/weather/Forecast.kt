package com.gwiazdowski.model.weather

import java.util.*

data class Forecast(
    val time: Date,
    val temperatureKelvin: Float,
    val weatherName: String,
    val weatherIcon: String,
    val forecast: List<WeatherItem>,
) {
    companion object {
        val EMPTY = Forecast(
            time = Date(0L),
            temperatureKelvin = 0f,
            weatherIcon = "",
            weatherName = "",
            forecast = emptyList()
        )
    }
}

data class WeatherItem(
    val time: Date,
    val temperatureKelvin: Float,
    val weatherIcon: String,
)