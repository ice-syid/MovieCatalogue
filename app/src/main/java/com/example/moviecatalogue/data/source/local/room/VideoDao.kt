package com.example.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowsEntity

@Dao
interface VideoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: ArrayList<MovieEntity>)

    @Query("SELECT * FROM table_movies")
    fun getMovies(): LiveData<MoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieEntity)

    @Query("SELECT * FROM table_movie WHERE id = :id")
    fun getMovie(id: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: ArrayList<TvShowEntity>)

    @Query("SELECT * FROM table_tv_shows")
    fun getTvShows(): LiveData<TvShowsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow: TvShowEntity)

    @Query("SELECT * FROM table_tv_show WHERE id = :id")
    fun getTvShow(id: Int): LiveData<TvShowEntity>
}