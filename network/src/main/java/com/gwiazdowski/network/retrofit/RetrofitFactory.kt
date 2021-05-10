package com.gwiazdowski.network.retrofit

import com.gwiazdowski.network.WeatherApi
import com.gwiazdowski.network.retrofit.weather.RetrofitWeatherApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

internal class RetrofitFactory(
    weatherApiBaseUrl: String
) {

    val weatherApi: WeatherApi

    init {
        weatherApi = RetrofitWeatherApi(
            createRetrofit(
                weatherApiBaseUrl,
                createDefaultOkHttp()
            )
        )
    }

    private fun createRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient
    ) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private fun createDefaultOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

}