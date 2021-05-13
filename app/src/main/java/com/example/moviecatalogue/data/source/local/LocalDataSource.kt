package com.example.moviecatalogue.data.source.local

import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowsEntity
import com.example.moviecatalogue.data.source.local.room.VideoDao

class LocalDataSource(private val videoDao: VideoDao) {

    fun insertMovies(movies: ArrayList<MovieEntity>) = videoDao.insertMovies(movies)
    fun getMovies() = videoDao.getMovies()
    fun insertMovie(movie: MovieEntity) = videoDao.insertMovie(movie)
    fun getMovie(id: Int) = videoDao.getMovie(id)

    fun insertTvShows(tvShows: ArrayList<TvShowEntity>) = videoDao.insertTvShows(tvShows)
    fun getTvShows() = videoDao.getTvShows()
    fun insertTvShow(tvShow: TvShowEntity) = videoDao.insertTvShow(tvShow)
    fun getTvShow(id: Int) = videoDao.getTvShow(id)

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(videoDao: VideoDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(videoDao)
    }
}