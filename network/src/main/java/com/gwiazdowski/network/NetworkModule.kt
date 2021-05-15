package com.gwiazdowski.network

import com.gwiazdowski.network.retrofit.RetrofitFactory
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
    single { RetrofitFactory(get(named(WEATHER_BASE_URL))) }

    single<INetworkService> {
        NetworkService(
            weatherApiKey = get(named(WEATHER_API_KEY)),
            languageCode = get(named(LANGUAGE_CODE)),
            retrofitFactory = get(),
        )
    }
}