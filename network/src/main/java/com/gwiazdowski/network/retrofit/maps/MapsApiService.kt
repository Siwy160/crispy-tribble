package com.gwiazdowski.network.retrofit.maps

import com.gwiazdowski.network.dto.MapsAutocompleteResponseDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MapsApiService {

    @GET("place/autocomplete/json")
    fun getPlacesAutocomplete(
        @Query("input") query: String,
        @Query("key") apiKey: String,
        @Query("language") languageCode: String,
        @Query("types") types: String = "(cities)"
    ): Single<MapsAutocompleteResponseDto>
}