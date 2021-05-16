package com.gwiazdowski.network.conventers

import com.gwiazdowski.model.search.City
import com.gwiazdowski.network.dto.CitiesByNameResponseDto

internal fun List<CitiesByNameResponseDto>.toCities(languageCode: String): List<City> = map {
    City(
        name = it.local_names?.get(languageCode) ?: it.name,
        lat = it.lat,
        lon = it.lon
    )
}
