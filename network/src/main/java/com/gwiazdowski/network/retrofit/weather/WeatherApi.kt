package com.gwiazdowski.network.retrofit.weather

import com.gwiazdowski.network.dto.CitiesByNameResponseDto
import com.gwiazdowski.network.dto.ForecastResponseDto
import com.gwiazdowski.network.dto.WeatherResponseDto
import io.reactivex.Single

internal interface WeatherApi {

    fun getWeather(cityName: String, apiKey: String, languageCode: String): Single<WeatherResponseDto>

    fun getForecast(lat: Double, lan: Double, apiKey: String, languageCode: String): Single<ForecastResponseDto>

    fun getCitiesByName(name: String, apiKey: String, limit: Int): Single<List<CitiesByNameResponseDto>>
}