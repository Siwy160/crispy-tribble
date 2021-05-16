package com.gwiazdowski.empikweather.ui.weather

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val weatherModule = module {
    scope<WeatherFragment> {
        viewModel { WeatherViewModel(get(), get()) }
    }
}