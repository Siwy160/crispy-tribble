package com.gwiazdowski.model.weather

import java.util.*

data class Forecast(
    val time: Date,
    val temperatureKelvin: Float,
    val weatherName: String,
    val weatherIcon: String,
    val hourlyForecast: List<WeatherItem>,
    val humidity: Int,
    val windSpeed: Float,
    val feelsLike: Float,
)

data class WeatherItem(
    val time: Date,
    val sunrise: Date,
    val sunset: Date,
    val weatherName: String,
    val temperatureKelvin: Float,
    val weatherIcon: String,
)