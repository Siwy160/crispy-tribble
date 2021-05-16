package com.gwiazdowski.services.searchhistory

import android.content.Context
import androidx.room.Room
import com.gwiazdowski.model.search.City
import com.gwiazdowski.services.searchhistory.model.Bookmark
import com.gwiazdowski.services.searchhistory.model.SearchHistory
import io.reactivex.Completable
import io.reactivex.Single

internal class LocalStorageService(
    applicationContext: Context
) : ILocalStorage {
    private val database = Room.databaseBuilder(
        applicationContext,
        LocalStorageDatabase::class.java,
        "preferences-database"
    ).build().searchHistoryDao()

    override fun getSavedSearches(query: String): Single<List<String>> = Single.create {
        val results = database.findRecentSearches(query, MAX_RECENT_SEARCHES).take(MAX_RECENT_SEARCHES).map { it.cityName }
        it.onSuccess(results)
    }

    override fun saveSearch(query: String): Completable = Completable.fromAction {
        database.insertRecentSearch(SearchHistory(query, System.currentTimeMillis()))
    }

    override fun addBookmark(city: City): Completable = Completable.fromAction {
        database.addBookmark(city.toBookmark())
    }

    override fun removeBookmark(city: City): Completable = Completable.fromAction {
        database.removeBookmark(city.toBookmark())
    }

    override fun isBookmark(city: City): Single<Boolean> = Single.fromCallable {
        database.getBookmark(city.name) != null
    }

    override fun getAllBookmarks(): Single<List<City>> = Single
        .fromCallable {
            database.getAll()
        }
        .map { it.map { bookmark -> City(name = bookmark.cityName, lat = bookmark.lat, lon = bookmark.lon) } }

    private fun City.toBookmark() = Bookmark(cityName = name, lat = lat, lon = lon)

    private companion object {
        const val MAX_RECENT_SEARCHES = 5
    }
}