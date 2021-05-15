package com.gwiazdowski.network

import com.gwiazdowski.model.search.City
import com.gwiazdowski.model.weather.CurrentWeather
import com.gwiazdowski.model.weather.Forecast
import com.gwiazdowski.network.conventers.toCities
import com.gwiazdowski.network.conventers.toForecast
import com.gwiazdowski.network.conventers.toWeather
import com.gwiazdowski.network.retrofit.RetrofitFactory
import io.reactivex.Single

internal class NetworkService(
    private val weatherApiKey: String,
    private val languageCode: String,
    private val retrofitFactory: RetrofitFactory
) : INetworkService {

    override fun getCurrentWeather(cityName: String): Single<CurrentWeather> =
        retrofitFactory.weatherApi.getWeather(cityName, weatherApiKey, languageCode)
            .map {
                it.toWeather()
            }

    override fun getForecast(lat: Double, lan: Double): Single<Forecast> =
        retrofitFactory.weatherApi.getForecast(lat, lan, weatherApiKey, languageCode)
            .map {
                it.toForecast()
            }

    override fun getCityByName(name: String): Single<List<City>> =
        retrofitFactory.weatherApi.getCitiesByName(name, weatherApiKey, 10)
            .map {
                it.toCities(languageCode)
            }
}
