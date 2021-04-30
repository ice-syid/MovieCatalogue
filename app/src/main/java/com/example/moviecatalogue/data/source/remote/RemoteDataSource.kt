package com.example.moviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.source.local.entity.VideoEntity
import com.example.moviecatalogue.data.source.remote.api.response.MovieResponse
import com.example.moviecatalogue.data.source.remote.api.response.TvShowResponse
import com.example.moviecatalogue.data.source.remote.api.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val retrofit: RetrofitClient) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(client: RetrofitClient): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(client)
            }
    }

    fun getMovies(): LiveData<ArrayList<VideoEntity>> {
        val movieLiveData = MutableLiveData<ArrayList<VideoEntity>>()
        val response = retrofit.client.getMovies()
        response.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                Log.d("syid", "sukses_movie")
                val movieList = ArrayList<VideoEntity>()
                val movies = response.body()?.results
                if (movies != null) {
                    for (item in movies) {
                        val movie = VideoEntity(
                            item.id,
                            item.originalTitle,
                            item.releaseDate,
                            item.voteAverage,
                            item.overview,
                            item.posterPath
                        )
                        movieList.add(movie)
                    }
                    Log.d("syid", movieList.toString())
                    movieLiveData.postValue(movieList)
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("syid", "gagal_movie")
            }

        })
        return movieLiveData
    }

    fun getTvShows(): LiveData<ArrayList<VideoEntity>> {
        val tvShowLiveData = MutableLiveData<ArrayList<VideoEntity>>()
        val response = retrofit.client.getTvShows()
        response.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                Log.d("syid", "sukses_tv")
                val tvShowList = ArrayList<VideoEntity>()
                val tvShows = response.body()?.results
                if (tvShows != null) {
                    for (item in tvShows) {
                        val tvShow = VideoEntity(
                            item.id,
                            item.originalName,
                            item.firstAirDate,
                            item.voteAverage,
                            item.overview,
                            item.posterPath
                        )
                        tvShowList.add(tvShow)
                    }
                    Log.d("syid", tvShowList.toString())
                    tvShowLiveData.postValue(tvShowList)
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.d("syid", "gagal_tv")
            }

        })
        return tvShowLiveData
    }
}