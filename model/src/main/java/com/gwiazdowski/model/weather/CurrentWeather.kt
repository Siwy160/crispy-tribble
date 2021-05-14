package com.gwiazdowski.model.weather

import java.util.*

data class CurrentWeather(
    val cityName: String,
    val time: Date,
    val temperatureKelvin: Float,
    val weatherName: String,
    val weatherIcon: String,
)