package com.example.moviecatalogue.data.source.remote.api.service

import com.example.moviecatalogue.BuildConfig.API_KEY
import com.example.moviecatalogue.data.source.remote.api.response.MovieResponse
import com.example.moviecatalogue.data.source.remote.api.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    // GET MOVIE POPULAR
    @GET("movie/popular?api_key=$API_KEY")
    fun getMovies(): Call<MovieResponse>

    // GET MOVIE DETAIL


    // GET TV POPULAR
    @GET("tv/popular?api_key=$API_KEY")
    fun getTvShows(): Call<TvShowResponse>

    // GET TV DETAIL

}