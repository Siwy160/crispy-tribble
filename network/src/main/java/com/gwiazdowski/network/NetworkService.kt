package com.gwiazdowski.network

import com.gwiazdowski.model.weather.Weather
import com.gwiazdowski.network.conventers.toWeather
import com.gwiazdowski.network.retrofit.RetrofitFactory
import io.reactivex.Single

internal class NetworkService(
    private val weatherApiKey: String,
    private val retrofitFactory: RetrofitFactory
) : INetworkService {

    override fun getWeather(cityName: String): Single<Weather> {
        return retrofitFactory.weatherApi.getWeather(cityName, weatherApiKey)
            .map { it.toWeather() }
    }
}
