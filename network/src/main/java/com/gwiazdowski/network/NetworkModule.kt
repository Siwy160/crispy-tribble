package com.gwiazdowski.network

import com.gwiazdowski.network.retrofit.RetrofitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
    single { RetrofitFactory(androidContext(), get(named(WEATHER_BASE_URL)), get(named(MAPS_BASE_URL))) }

    single<INetworkService> {
        NetworkService(
            weatherApiKey = get(named(WEATHER_API_KEY)),
            mapsApiKey = get(named(MAPS_API_KEY)),
            languageCode = get(named(LANGUAGE_CODE)),
            retrofitFactory = get(),
        )
    }
}