package com.gwiazdowski.services.searchhistory

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gwiazdowski.services.searchhistory.model.Bookmark
import com.gwiazdowski.services.searchhistory.model.SearchHistory

@Database(entities = [SearchHistory::class, Bookmark::class], version = 1)
internal abstract class LocalStorageDatabase : RoomDatabase() {

    abstract fun searchHistoryDao(): LocalStorageDao
}
