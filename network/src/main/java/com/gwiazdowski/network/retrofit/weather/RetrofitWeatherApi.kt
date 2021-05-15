package com.gwiazdowski.network.retrofit.weather

import com.gwiazdowski.network.WeatherApi
import com.gwiazdowski.network.dto.CitiesByNameResponseDto
import com.gwiazdowski.network.dto.ForecastResponseDto
import com.gwiazdowski.network.dto.WeatherResponseDto
import io.reactivex.Single
import retrofit2.Retrofit

internal class RetrofitWeatherApi(retrofit: Retrofit) : WeatherApi {
    private val weatherApiService = retrofit.create(WeatherApiService::class.java)

    override fun getWeather(cityName: String, apiKey: String, languageCode: String): Single<WeatherResponseDto> =
        weatherApiService.getWeather(cityName, apiKey, languageCode)

    override fun getForecast(lat: Double, lan: Double, apiKey: String, languageCode: String): Single<ForecastResponseDto> =
        weatherApiService.getForecast(lat, lan, apiKey, languageCode)

    override fun getCitiesByName(name: String, apiKey: String, limit: Int): Single<List<CitiesByNameResponseDto>> =
        weatherApiService.getCitiesByName(name, apiKey, limit)
}
