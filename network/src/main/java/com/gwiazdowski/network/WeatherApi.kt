package com.gwiazdowski.network

import com.gwiazdowski.network.dto.ForecastResponseDto
import com.gwiazdowski.network.dto.WeatherResponseDto
import io.reactivex.Single

internal interface WeatherApi {

    fun getWeather(cityName: String, apiKey: String, languageCode: String): Single<WeatherResponseDto>

    fun getForecast(lat: Double, lan: Double, apiKey: String, languageCode: String): Single<ForecastResponseDto>
}