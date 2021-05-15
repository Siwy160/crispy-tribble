package com.gwiazdowski.network.dto

internal data class ForecastResponseDto(
    val lat: Double,
    val lan: Double,
    val timezone: String,
    val timezone_offset: Int,
    val current: WeatherItem,
    val hourly: Array<WeatherItem>,
)

internal data class WeatherItem(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: Float,
    val feels_like: Float,
    val pressure: Int,
    val humidity: Int,
    val dew_point: Float,
    val uvi: Float,
    val clouds: Int,
    val visibility: Int,
    val wind_speed: Float,
    val wind_deg: Int,
    val weather: Array<Weather>,
    val rain: Rain,
)

internal data class Rain(
    val dt: Long,
    val precipitation: Float
)