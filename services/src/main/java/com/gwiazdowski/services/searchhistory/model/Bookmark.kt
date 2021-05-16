package com.gwiazdowski.services.searchhistory.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class Bookmark(
    @PrimaryKey @ColumnInfo(name = "city_name") val cityName: String,
    @ColumnInfo(name = "lat") val lat: Double,
    @ColumnInfo(name = "lon") val lon: Double
)