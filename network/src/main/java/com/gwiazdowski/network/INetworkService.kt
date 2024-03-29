package com.gwiazdowski.network

import com.gwiazdowski.model.search.City
import com.gwiazdowski.model.weather.CurrentWeather
import com.gwiazdowski.model.weather.Forecast
import io.reactivex.Single

interface INetworkService {

    fun getCurrentWeather(cityName: String): Single<CurrentWeather>

    fun getForecast(lat: Double, lon: Double): Single<Forecast>

    fun getCityByName(name: String): Single<List<City>>

    fun getCityNameAutocomplete(query: String): Single<List<String>>
}