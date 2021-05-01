package com.example.moviecatalogue.data

import com.example.moviecatalogue.data.source.remote.RemoteDataSource

class FakeVideoRepository(private val remoteDataSource: RemoteDataSource) :
    VideoDataSource {

    companion object {
        @Volatile
        private var instance: FakeVideoRepository? = null
        fun getInstance(remoteData: RemoteDataSource): FakeVideoRepository =
            instance ?: synchronized(this) {
                instance ?: FakeVideoRepository(remoteData).apply { instance = this }
            }
    }

    override fun getMovies() = remoteDataSource.getMovies()

    override fun getMovie(movieId: Int) = remoteDataSource.getMovie(movieId)

    override fun getTvShows() = remoteDataSource.getTvShows()

    override fun getTvShow(tvShowId: Int) = remoteDataSource.getTvShow(tvShowId)
}