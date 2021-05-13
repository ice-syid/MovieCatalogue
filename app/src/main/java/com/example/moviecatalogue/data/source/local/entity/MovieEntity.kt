package com.example.moviecatalogue.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_movie")
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val originalTitle: String?,
    val releaseDate: String?,
    val voteAverage: Double?,
    val overview: String?,
    val posterPath: String?,
    var isFavorite: Boolean = false
)
