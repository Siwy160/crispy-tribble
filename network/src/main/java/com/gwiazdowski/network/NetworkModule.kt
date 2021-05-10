package com.gwiazdowski.network

import com.gwiazdowski.network.retrofit.RetrofitFactory
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
    single { RetrofitFactory(get(named("weatherBaseUrl"))) }

    single<INetworkService> {
        NetworkService(
            get(named("weatherApiKey")),
            get()
        )
    }
}