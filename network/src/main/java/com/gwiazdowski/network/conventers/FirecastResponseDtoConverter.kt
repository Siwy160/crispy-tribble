package com.gwiazdowski.network.conventers

import com.gwiazdowski.model.weather.Forecast
import com.gwiazdowski.model.weather.WeatherItem
import com.gwiazdowski.network.dto.ForecastResponseDto
import java.util.*

internal fun ForecastResponseDto.toForecast() = Forecast(
    time = Date(System.currentTimeMillis()),
    temperatureKelvin = current.temp,
    weatherName = current.weather.main,
    weatherIcon = current.weather.icon,
    forecast = hourly.map {
        WeatherItem(
            time = Date(it.dt),
            temperatureKelvin = it.temp,
            weatherIcon = it.weather.icon
        )
    }
)
