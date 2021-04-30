package com.example.moviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.source.remote.api.response.MovieResponse
import com.example.moviecatalogue.data.source.remote.api.response.MovieResultsItem
import com.example.moviecatalogue.data.source.remote.api.response.TvShowResponse
import com.example.moviecatalogue.data.source.remote.api.response.TvShowResultsItem
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

    fun getMovies(): LiveData<ArrayList<MovieResultsItem>> {
        val movieLiveData = MutableLiveData<ArrayList<MovieResultsItem>>()
        val response = retrofit.client.getMovies()
        response.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                Log.d("syid", "sukses_movie")
                val movieList = ArrayList<MovieResultsItem>()
                val movies = response.body()?.results
                if (movies != null) {
                    for (item in movies) {
                        val movie = MovieResultsItem(
                            item.id,
                            item.title,
                            item.date,
                            item.rating,
                            item.overview,
                            item.poster
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

    fun getMovie(movieId: Int): LiveData<MovieResultsItem> {
        val movieLiveData = MutableLiveData<MovieResultsItem>()
        val response = retrofit.client.getMovie(movieId)
        response.enqueue(object : Callback<MovieResultsItem> {
            override fun onResponse(
                call: Call<MovieResultsItem>,
                response: Response<MovieResultsItem>
            ) {
                movieLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<MovieResultsItem>, t: Throwable) {
            }
        })
        return movieLiveData
    }

    fun getTvShows(): LiveData<ArrayList<TvShowResultsItem>> {
        val tvShowLiveData = MutableLiveData<ArrayList<TvShowResultsItem>>()
        val response = retrofit.client.getTvShows()
        response.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                Log.d("syid", "sukses_tv")
                val tvShowList = ArrayList<TvShowResultsItem>()
                val tvShows = response.body()?.results
                if (tvShows != null) {
                    for (item in tvShows) {
                        val tvShow = TvShowResultsItem(
                            item.id,
                            item.title,
                            item.date,
                            item.rating,
                            item.overview,
                            item.poster
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

    fun getTvShow(tvShowId: Int): LiveData<TvShowResultsItem> {
        val tvShowLiveData = MutableLiveData<TvShowResultsItem>()
        val response = retrofit.client.getTvShow(tvShowId)
        response.enqueue(object : Callback<TvShowResultsItem> {
            override fun onResponse(
                call: Call<TvShowResultsItem>,
                response: Response<TvShowResultsItem>
            ) {
                tvShowLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<TvShowResultsItem>, t: Throwable) {
            }

        })
        return tvShowLiveData
    }
}