package com.example.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowsEntity
import com.example.moviecatalogue.vo.Resource

interface VideoDataSource {

    fun getMovies(): LiveData<Resource<MoviesEntity>>

    fun getMovie(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getTvShows(): LiveData<Resource<TvShowsEntity>>

    fun getTvShow(tvShowId: Int): LiveData<Resource<TvShowEntity>>

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)

    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean)

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>>
}