package com.gwiazdowski.services.searchhistory

import android.content.Context
import androidx.room.Room
import io.reactivex.Completable
import io.reactivex.Single

internal class SearchHistoryService(
    applicationContext: Context
) : ISearchHistoryService {
    private val database = Room.databaseBuilder(
        applicationContext,
        SearchHistoryDatabase::class.java,
        "search-history-database"
    ).build().searchHistoryDao()

    override fun getSavedSearches(query: String): Single<List<String>> = Single.create {
        val results = database.findRecentSearches(query, MAX_RESULTS).take(MAX_RESULTS).map { it.cityName }
        it.onSuccess(results)
    }

    override fun saveSearch(query: String): Completable = Completable.fromAction {
        database.insert(SearchHistory(query, System.currentTimeMillis()))
    }

    private companion object {
        const val MAX_RESULTS = 5
    }
}