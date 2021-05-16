package com.gwiazdowski.services.searchhistory

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class SearchHistory(
    @PrimaryKey @ColumnInfo(name = "city_name") val cityName: String,
    @ColumnInfo(name = "time_saved") val timeSaved: Long
)
