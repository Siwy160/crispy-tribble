package com.gwiazdowski.network.retrofit.weather

import com.gwiazdowski.network.dto.WeatherResponseDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

internal interface WeatherApiService {

    @GET("weather")
    fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ): Single<WeatherResponseDto>
}
