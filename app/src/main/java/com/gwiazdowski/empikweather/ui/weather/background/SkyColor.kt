package com.gwiazdowski.empikweather.ui.weather.background


data class SkyColor(
    val top: Int,
    val bottom: Int,
    val isDay: Boolean,
    val dayPercent: Float
)