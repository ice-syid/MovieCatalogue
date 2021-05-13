package com.example.moviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.source.remote.api.response.MovieResponse
import com.example.moviecatalogue.data.source.remote.api.response.MovieResultsItem
import com.example.moviecatalogue.data.source.remote.api.response.TvShowResponse
import com.example.moviecatalogue.data.source.remote.api.response.TvShowResultsItem
import com.example.moviecatalogue.data.source.remote.api.service.RetrofitClient
import com.example.moviecatalogue.utils.EspressoIdlingResource
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

    fun getMovies(): LiveData<ApiResponse<MovieResponse>> {
        val movieLiveData = MutableLiveData<ApiResponse<MovieResponse>>()
        val response = retrofit.client.getMovies()
        EspressoIdlingResource.increment()
        response.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
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
                    Log.d("a123", movieList.toString())
                    movieLiveData.value = ApiResponse.success(MovieResponse(results = movieList))
                    Log.d("b123", movieLiveData.value?.body?.results.toString())
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
        Log.d("c123", movieLiveData.value?.body?.results.toString())
        return movieLiveData
    }

    fun getMovie(movieId: Int): LiveData<ApiResponse<MovieResultsItem>> {
        val movieLiveData = MutableLiveData<ApiResponse<MovieResultsItem>>()
        val response = retrofit.client.getMovie(movieId)
        EspressoIdlingResource.increment()
        response.enqueue(object : Callback<MovieResultsItem> {
            override fun onResponse(
                call: Call<MovieResultsItem>,
                response: Response<MovieResultsItem>
            ) {
                val movie = response.body()
                movie?.let {
                    movieLiveData.value = ApiResponse.success(it)
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<MovieResultsItem>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
        Log.d("syid_remote", movieLiveData.value?.body.toString())
        return movieLiveData
    }

    fun getTvShows(): LiveData<ApiResponse<TvShowResponse>> {
        val tvShowLiveData = MutableLiveData<ApiResponse<TvShowResponse>>()
        val response = retrofit.client.getTvShows()
        EspressoIdlingResource.increment()
        response.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
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
                    Log.d("a123", tvShowList.toString())
                    tvShowLiveData.value = ApiResponse.success(TvShowResponse(results = tvShowList))
                    Log.d("b123", tvShowLiveData.value?.body?.results.toString())
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
        Log.d("c123", tvShowLiveData.value?.body?.results.toString())
        return tvShowLiveData
    }

    fun getTvShow(tvShowId: Int): LiveData<ApiResponse<TvShowResultsItem>> {
        val tvShowLiveData = MutableLiveData<ApiResponse<TvShowResultsItem>>()
        val response = retrofit.client.getTvShow(tvShowId)
        EspressoIdlingResource.increment()
        response.enqueue(object : Callback<TvShowResultsItem> {
            override fun onResponse(
                call: Call<TvShowResultsItem>,
                response: Response<TvShowResultsItem>
            ) {
                val tvShow = response.body()
                tvShow?.let {
                    tvShowLiveData.value = ApiResponse.success(it)
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<TvShowResultsItem>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
        Log.d("syid_remote", tvShowLiveData.value?.body.toString())
        return tvShowLiveData
    }
}