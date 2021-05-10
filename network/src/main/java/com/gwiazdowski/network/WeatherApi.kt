package com.gwiazdowski.network

import com.gwiazdowski.network.dto.WeatherResponseDto
import io.reactivex.Single

interface WeatherApi {

    fun getWeather(cityName: String, apiKey: String): Single<WeatherResponseDto>
}