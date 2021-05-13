package com.example.moviecatalogue.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_tv_show")
data class TvShowEntity(
    @PrimaryKey
    val id: Int,
    val originalName: String?,
    val firstAirDate: String?,
    val voteAverage: Double?,
    val overview: String?,
    val posterPath: String?,
    var isFavorite: Boolean = false
)
