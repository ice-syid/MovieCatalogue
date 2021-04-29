package com.example.moviecatalogue.data

import com.example.moviecatalogue.data.source.remote.RemoteDataSource

class VideoRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    VideoDataSource {

    companion object {
        @Volatile
        private var instance: VideoRepository? = null
        fun getInstance(remoteData: RemoteDataSource): VideoRepository =
            instance ?: synchronized(this) {
                instance ?: VideoRepository(remoteData).apply { instance = this }
            }
    }

    override fun getMovies() = remoteDataSource.getMovies()

    override fun getTvShows() = remoteDataSource.getTvShows()
}