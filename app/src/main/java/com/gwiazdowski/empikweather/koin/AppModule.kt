package com.gwiazdowski.empikweather.koin

import com.gwiazdowski.empikweather.R
import com.gwiazdowski.network.WEATHER_API_KEY
import com.gwiazdowski.network.WEATHER_BASE_URL
import com.gwiazdowski.network.LANGUAGE_CODE
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    factory(named(WEATHER_API_KEY)) {
        "1e467674fe246f40af5cce2ad129824b" //TODO put it in BuildConfig
    }
    factory(named(WEATHER_BASE_URL)) {
        "https://api.openweathermap.org/" //TODO put it in BuildConfig
    }
    factory(named(LANGUAGE_CODE)) {
        androidApplication().resources.getString(R.string.weather_api_language_code)
    }
}
