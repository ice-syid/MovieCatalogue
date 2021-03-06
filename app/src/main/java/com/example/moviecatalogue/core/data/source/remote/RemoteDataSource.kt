package com.example.moviecatalogue.core.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.core.data.source.remote.api.network.ApiResponse
import com.example.moviecatalogue.core.data.source.remote.api.network.ApiService
import com.example.moviecatalogue.core.data.source.remote.api.response.MovieResponse
import com.example.moviecatalogue.core.data.source.remote.api.response.MovieResultsItem
import com.example.moviecatalogue.core.data.source.remote.api.response.TvShowResponse
import com.example.moviecatalogue.core.data.source.remote.api.response.TvShowResultsItem
import com.example.moviecatalogue.core.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val retrofit: ApiService) {

    fun getMovies(): LiveData<ApiResponse<MovieResponse>> {
        val movieLiveData = MutableLiveData<ApiResponse<MovieResponse>>()
        val response = retrofit.getMovies()
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
                    movieLiveData.value = ApiResponse.success(MovieResponse(results = movieList))
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
        return movieLiveData
    }

    fun getMovie(movieId: Int): LiveData<ApiResponse<MovieResultsItem>> {
        val movieLiveData = MutableLiveData<ApiResponse<MovieResultsItem>>()
        val response = retrofit.getMovie(movieId)
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
        return movieLiveData
    }

    fun getTvShows(): LiveData<ApiResponse<TvShowResponse>> {
        val tvShowLiveData = MutableLiveData<ApiResponse<TvShowResponse>>()
        val response = retrofit.getTvShows()
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
                    tvShowLiveData.value = ApiResponse.success(TvShowResponse(results = tvShowList))
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
        return tvShowLiveData
    }

    fun getTvShow(tvShowId: Int): LiveData<ApiResponse<TvShowResultsItem>> {
        val tvShowLiveData = MutableLiveData<ApiResponse<TvShowResultsItem>>()
        val response = retrofit.getTvShow(tvShowId)
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
        return tvShowLiveData
    }
}