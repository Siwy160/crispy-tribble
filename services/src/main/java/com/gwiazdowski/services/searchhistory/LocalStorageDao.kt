package com.gwiazdowski.services.searchhistory

import androidx.room.*
import com.gwiazdowski.services.searchhistory.model.Bookmark
import com.gwiazdowski.services.searchhistory.model.SearchHistory

@Dao
internal interface LocalStorageDao {

    @Query("SELECT * FROM searchhistory WHERE city_name LIKE '%' || :query || '%' ORDER BY time_saved DESC LIMIT :maxResults ")
    fun findRecentSearches(query: String, maxResults: Int): List<SearchHistory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecentSearch(searchHistory: SearchHistory)

    @Insert
    fun addBookmark(bookmark: Bookmark)

    @Delete
    fun removeBookmark(bookmark: Bookmark)

    @Query("SELECT * FROM bookmark WHERE city_name LIKE :cityName")
    fun getBookmark(cityName: String): Bookmark?

    @Query("SELECT * FROM bookmark")
    fun getAll(): List<Bookmark>
}