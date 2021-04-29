package com.example.moviecatalogue.data.source.remote

import com.example.moviecatalogue.BuildConfig.API_KEY
import com.example.moviecatalogue.data.source.local.entity.VideoEntity
import com.example.moviecatalogue.data.source.remote.api.service.RetrofitClient

class RemoteDataSource private constructor(private val retrofit: RetrofitClient) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(client: RetrofitClient): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(client)
            }
    }

    fun getMovies(): ArrayList<VideoEntity> {
        val movieResponses = retrofit.client.getMovies(API_KEY).results
        val movieList = ArrayList<VideoEntity>()
        for (response in movieResponses) {
            val movie = VideoEntity(
                response.id,
                response.title,
                response.releaseDate,
                response.voteAverage.toString(),
                response.overview,
                response.posterPath
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun getTvShows(): ArrayList<VideoEntity> {
        val tvShowResponses = retrofit.client.getTvShows(API_KEY).results
        val tvShowList = ArrayList<VideoEntity>()
        for (response in tvShowResponses) {
            val tvShow = VideoEntity(
                response.id,
                response.title,
                response.releaseDate,
                response.voteAverage.toString(),
                response.overview,
                response.posterPath
            )
            tvShowList.add(tvShow)
        }
        return tvShowList
    }
}