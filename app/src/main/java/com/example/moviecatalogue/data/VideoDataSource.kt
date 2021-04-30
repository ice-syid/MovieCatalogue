package com.example.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.data.source.remote.api.response.MovieResultsItem
import com.example.moviecatalogue.data.source.remote.api.response.TvShowResultsItem

interface VideoDataSource {

    fun getMovies(): LiveData<ArrayList<MovieResultsItem>>

    fun getMovie(movieId: Int): LiveData<MovieResultsItem>

    fun getTvShows(): LiveData<ArrayList<TvShowResultsItem>>

    fun getTvShow(tvShowId: Int): LiveData<TvShowResultsItem>
}