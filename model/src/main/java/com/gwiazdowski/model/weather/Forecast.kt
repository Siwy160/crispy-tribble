package com.gwiazdowski.model.weather

import java.util.*

data class Forecast(
    val time: Date,
    val temperatureKelvin: Float,
    val weatherName: String,
    val weatherIcon: String,
    val forecast: List<WeatherItem>,
)

data class WeatherItem(
    val time: Date,
    val sunrise: Date,
    val sunset: Date,
    val temperatureKelvin: Float,
    val weatherIcon: String,
)