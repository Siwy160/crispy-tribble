package com.gwiazdowski.network.retrofit.maps

import io.reactivex.Single

interface MapsApi {

    /**
     * @return list of suggested city names
     */
    fun getCitiesAutocomplete(query: String, apiKey: String, languageCode: String): Single<List<String>>
}
