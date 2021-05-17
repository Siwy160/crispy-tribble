package com.gwiazdowski.network.conventers

import com.gwiazdowski.model.weather.Forecast
import com.gwiazdowski.model.weather.WeatherItem
import com.gwiazdowski.network.dto.ForecastResponseDto
import java.util.*

internal fun ForecastResponseDto.toForecast() = Forecast(
    time = Date(System.currentTimeMillis()),
    temperatureKelvin = current.temp,
    weatherName = current.weather.firstOrNull()?.main ?: "",
    weatherIcon = current.weather.firstOrNull()?.icon ?: "",
    humidity = current.humidity,
    windSpeed = current.wind_speed,
    feelsLike = current.feels_like,
    hourlyForecast = hourly.map {
        WeatherItem(
            time = it.dt.toDate(),
            sunrise = daily.first().sunrise.toDate(), //Hourly weather does not contain sunrise and sunset
            sunset = daily.first().sunset.toDate(),
            temperatureKelvin = it.temp,
            weatherIcon = it.weather.firstOrNull()?.icon ?: "",
            weatherName = it.weather.firstOrNull()?.main ?: ""
        )
    }
)

private fun Long.toDate() = Date(this * 1000)
