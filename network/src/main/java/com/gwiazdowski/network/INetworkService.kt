package com.gwiazdowski.network

import com.gwiazdowski.model.weather.Weather
import io.reactivex.Single

interface INetworkService {

    fun getWeather(cityName: String): Single<Weather>
}