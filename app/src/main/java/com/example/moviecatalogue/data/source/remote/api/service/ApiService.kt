package com.example.moviecatalogue.data.source.remote.api.service

import com.example.moviecatalogue.BuildConfig.API_KEY
import com.example.moviecatalogue.data.source.remote.api.response.MovieResponse
import com.example.moviecatalogue.data.source.remote.api.response.MovieResultsItem
import com.example.moviecatalogue.data.source.remote.api.response.TvShowResponse
import com.example.moviecatalogue.data.source.remote.api.response.TvShowResultsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    // GET MOVIE POPULAR
    @GET("movie/popular?api_key=$API_KEY")
    fun getMovies(): Call<MovieResponse>

    // GET MOVIE DETAIL
    @GET("movie/{movie_id}?api_key=$API_KEY")
    fun getMovie(@Path("movie_id") movieId: Int): Call<MovieResultsItem>

    // GET TV POPULAR
    @GET("tv/popular?api_key=$API_KEY")
    fun getTvShows(): Call<TvShowResponse>

    // GET TV DETAIL
    @GET("tv/{tv_id}?api_key=$API_KEY")
    fun getTvShow(@Path("tv_id") tvId: Int): Call<TvShowResultsItem>
}