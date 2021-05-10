package com.gwiazdowski.empikweather.ui.weather

import com.gwiazdowski.model.weather.Weather
import com.gwiazdowski.services.navigation.IArguments

data class WeatherArguments(
    val weather: Weather
) : IArguments