package com.example.moviecatalogue.core.data.source.remote.api.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @field:SerializedName("results")
    val results: List<MovieResultsItem>
)

data class MovieResultsItem(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("original_title")
    val title: String,

    @field:SerializedName("release_date")
    val date: String,

    @field:SerializedName("vote_average")
    val rating: Double,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("poster_path")
    val poster: String
)