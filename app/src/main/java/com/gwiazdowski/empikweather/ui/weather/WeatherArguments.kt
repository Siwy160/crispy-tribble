package com.gwiazdowski.empikweather.ui.weather

import com.gwiazdowski.model.search.City
import com.gwiazdowski.services.navigation.IArguments

data class WeatherArguments(
    val city: City
) : IArguments