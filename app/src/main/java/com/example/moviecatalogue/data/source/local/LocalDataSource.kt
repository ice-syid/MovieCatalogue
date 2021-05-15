package com.example.moviecatalogue.data.source.local

import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowsEntity
import com.example.moviecatalogue.data.source.local.room.VideoDao

class LocalDataSource(private val videoDao: VideoDao) {

    fun insertMovies(movies: MoviesEntity) = videoDao.insertMovies(movies)
    fun getMovies() = videoDao.getMovies()
    fun insertMovie(movie: MovieEntity) = videoDao.insertMovie(movie)
    fun getMovie(id: Int) = videoDao.getMovie(id)

    fun insertTvShows(tvShows: TvShowsEntity) = videoDao.insertTvShows(tvShows)
    fun getTvShows() = videoDao.getTvShows()
    fun insertTvShow(tvShow: TvShowEntity) = videoDao.insertTvShow(tvShow)
    fun getTvShow(id: Int) = videoDao.getTvShow(id)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        videoDao.updateMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        videoDao.updateTvShow(tvShow)
    }

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(videoDao: VideoDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(videoDao)
    }
}