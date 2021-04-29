package com.example.moviecatalogue.data.source.remote.api.service

import com.example.moviecatalogue.data.source.remote.api.response.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // GET MOVIE POPULAR
    @GET("movie/popular?")
    fun getMovies(
        @Query("api_key") API_KEY: String
    ): VideoResponse

    // GET MOVIE DETAIL


    // GET TV POPULAR
    @GET("tv/popular?")
    fun getTvShows(
        @Query("api_key") API_KEY: String
    ): VideoResponse

    // GET TV DETAIL

}