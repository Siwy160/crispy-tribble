package com.gwiazdowski.network.retrofit.maps

import io.reactivex.Single
import retrofit2.Retrofit

class RetrofitMapsApi(retrofit: Retrofit) : MapsApi {
    val mapsApiService = retrofit.create(MapsApiService::class.java)

    override fun getCitiesAutocomplete(query: String, apiKey: String, languageCode: String): Single<List<String>> =
        mapsApiService.getPlacesAutocomplete(query, apiKey, languageCode)
            .map {
                it.predictions.map { it.description }
            }
}
