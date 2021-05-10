package com.gwiazdowski.empikweather

import android.app.Application
import com.gwiazdowski.empikweather.koin.appModule
import com.gwiazdowski.empikweather.ui.home.homeModule
import com.gwiazdowski.empikweather.ui.weather.weatherModule
import com.gwiazdowski.network.networkModule
import com.gwiazdowski.services.microservicesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@WeatherApp)
            modules(
                listOf(
                    appModule,
                    networkModule,
                    microservicesModule(R.id.container),
                    homeModule,
                    weatherModule,
                )
            )
        }
    }
}