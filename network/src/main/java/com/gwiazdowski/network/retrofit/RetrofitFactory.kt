package com.gwiazdowski.network.retrofit

import android.content.Context
import com.gwiazdowski.network.retrofit.maps.MapsApi
import com.gwiazdowski.network.retrofit.maps.RetrofitMapsApi
import com.gwiazdowski.network.retrofit.weather.RetrofitWeatherApi
import com.gwiazdowski.network.retrofit.weather.WeatherApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File

internal class RetrofitFactory(
    context: Context,
    weatherApiBaseUrl: String,
    mapsBaseUrl: String
) {

    val weatherApi: WeatherApi
    val mapsApi: MapsApi

    init {
        weatherApi = RetrofitWeatherApi(
            createRetrofit(
                weatherApiBaseUrl,
                createDefaultOkHttp(context, false)
            )
        )
        mapsApi = RetrofitMapsApi(
            createRetrofit(
                mapsBaseUrl,
                createDefaultOkHttp(context, true)
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

    private fun createDefaultOkHttp(context: Context, withCache: Boolean): OkHttpClient {
        val httpCacheDirectory = File(context.cacheDir, "http-cache")
        val cacheSize = 10L * 1024 * 1024 // 10 MiB
        val cache = Cache(httpCacheDirectory, cacheSize)
        return OkHttpClient.Builder()
            .also {
                if (withCache) {
                    it.cache(cache)
                }
            }
            .build()
    }

}