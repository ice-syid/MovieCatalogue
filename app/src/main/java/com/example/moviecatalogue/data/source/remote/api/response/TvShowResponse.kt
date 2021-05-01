package com.example.moviecatalogue.data.source.remote.api.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(
    @field:SerializedName("results")
    val results: List<TvShowResultsItem>
)

data class TvShowResultsItem(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("original_name")
    val title: String,

    @field:SerializedName("first_air_date")
    val date: String,

    @field:SerializedName("vote_average")
    val rating: Double,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("poster_path")
    val poster: String,
)