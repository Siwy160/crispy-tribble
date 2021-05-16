package com.gwiazdowski.services.searchhistory

import com.gwiazdowski.model.search.City
import io.reactivex.Completable
import io.reactivex.Single

interface ILocalStorage {

    fun getSavedSearches(query: String): Single<List<String>>

    fun saveSearch(query: String): Completable

    fun addBookmark(city: City): Completable

    fun removeBookmark(city: City): Completable

    fun isBookmark(city: City) : Single<Boolean>

    fun getAllBookmarks(): Single<List<City>>
}