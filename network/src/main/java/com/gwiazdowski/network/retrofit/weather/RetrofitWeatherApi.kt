package com.gwiazdowski.network.retrofit.weather

import com.gwiazdowski.network.WeatherApi
import com.gwiazdowski.network.dto.WeatherResponseDto
import io.reactivex.Single
import retrofit2.Retrofit

internal class RetrofitWeatherApi(retrofit: Retrofit) : WeatherApi {
    private val weatherApiService = retrofit.create(WeatherApiService::class.java)

    override fun getWeather(cityName: String, apiKey: String): Single<WeatherResponseDto> =
        weatherApiService.getWeather(cityName, apiKey)
}
