package com.gwiazdowski.services.searchhistory

import io.reactivex.Completable
import io.reactivex.Single

interface ISearchHistoryService {

    fun getSavedSearches(query: String): Single<List<String>>

    fun saveSearch(query: String): Completable
}