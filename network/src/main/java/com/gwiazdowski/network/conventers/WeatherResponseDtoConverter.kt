package com.gwiazdowski.network.conventers

import com.gwiazdowski.model.weather.CurrentWeather
import com.gwiazdowski.network.dto.WeatherResponseDto
import java.util.*

internal fun WeatherResponseDto.toWeather() =
    CurrentWeather(
        cityName = name,
        time = Date(System.currentTimeMillis()),
        temperatureKelvin = main.temp,
        weatherName = weather.firstOrNull()?.main ?: "?",
        weatherIcon = weather.firstOrNull()?.icon ?: ""
    )