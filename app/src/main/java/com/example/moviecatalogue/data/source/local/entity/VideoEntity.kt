package com.example.moviecatalogue.data.source.local.entity

data class VideoEntity(
    val id: Int,
    val title: String,
    val release_date: String,
    val rating: Double,
    val overview: String,
    val poster: String
)