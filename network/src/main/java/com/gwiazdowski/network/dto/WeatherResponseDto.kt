package com.gwiazdowski.network.dto


internal data class WeatherResponseDto(
    val coord: Coordinates,
    val weather: Array<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Int,
    val id: Long,
    val name: String,
    val cod: Int,
)

internal data class Coordinates(
    val lon: Float,
    val lat: Float,
)

internal data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String,
)

internal data class Main(
    val temp: Float,
    val feels_like: Float,
    val pressure: Int,
    val humidity: Int,
)

internal data class Wind(
    val speed: Float,
    val deg: Int,
)

internal data class Clouds(
    val all: Int
)

internal data class Sys(
    val type: Int,
    val id: Int,
    val message: Float,
    val country: String,
    val sunrise: Long,
    val sunset: Long,
)