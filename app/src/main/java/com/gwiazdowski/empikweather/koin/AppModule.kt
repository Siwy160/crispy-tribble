package com.gwiazdowski.empikweather.koin

import com.gwiazdowski.services.navigation.INavigationService
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    factory(named("weatherApiKey")) {
        "1e467674fe246f40af5cce2ad129824b" //TODO put it in BuildConfig
    }
    factory(named("weatherBaseUrl")) {
        "https://api.openweathermap.org/data/2.5/" //TODO put it in BuildConfig
    }


}