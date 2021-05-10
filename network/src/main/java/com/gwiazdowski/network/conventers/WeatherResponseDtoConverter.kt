package com.gwiazdowski.network.conventers

import com.gwiazdowski.model.weather.Weather
import com.gwiazdowski.network.dto.WeatherResponseDto

fun WeatherResponseDto.toWeather() =
    Weather(
        id = id,
        name = name,
    )