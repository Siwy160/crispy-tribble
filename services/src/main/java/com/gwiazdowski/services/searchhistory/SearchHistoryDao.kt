package com.gwiazdowski.services.searchhistory

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
internal interface SearchHistoryDao {

    @Query("SELECT * FROM searchhistory WHERE city_name LIKE '%' || :query || '%' ORDER BY time_saved DESC LIMIT :maxResults ")
    fun findRecentSearches(query: String, maxResults: Int): List<SearchHistory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(searchHistory: SearchHistory)
}