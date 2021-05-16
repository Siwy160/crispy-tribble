package com.gwiazdowski.model.bookmark

import com.gwiazdowski.model.search.City
import com.gwiazdowski.model.weather.Forecast

data class Bookmark(
    val forecast: Forecast?,
    val city: City
)
