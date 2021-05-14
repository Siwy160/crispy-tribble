package com.gwiazdowski.network

import com.gwiazdowski.model.weather.CurrentWeather
import com.gwiazdowski.model.weather.Forecast
import io.reactivex.Single

interface INetworkService {

    fun getCurrentWeather(cityName: String): Single<CurrentWeather>

    fun getForecast(cityName: String) : Single<Forecast>
}