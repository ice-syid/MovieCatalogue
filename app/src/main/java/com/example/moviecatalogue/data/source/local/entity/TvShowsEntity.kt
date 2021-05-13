package com.example.moviecatalogue.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_tv_shows")
data class TvShowsEntity(
    @PrimaryKey
    val id: Int,
    val results: MutableList<TvShowEntity>
)
