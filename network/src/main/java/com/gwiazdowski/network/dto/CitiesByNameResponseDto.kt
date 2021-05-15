package com.gwiazdowski.network.dto

data class CitiesByNameResponseDto(
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String,
    val local_names: Map<String, String>
)
