package com.example.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowsEntity
import com.example.moviecatalogue.data.source.remote.ApiResponse
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.api.response.MovieResponse
import com.example.moviecatalogue.data.source.remote.api.response.MovieResultsItem
import com.example.moviecatalogue.data.source.remote.api.response.TvShowResponse
import com.example.moviecatalogue.data.source.remote.api.response.TvShowResultsItem
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.vo.Resource

class VideoRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    VideoDataSource {

    override fun getMovies(): LiveData<Resource<MoviesEntity>> {
        return object : NetworkBoundResource<MoviesEntity, MovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MoviesEntity> = localDataSource.getMovies()


            override fun shouldFetch(data: MoviesEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<MovieResponse>> =
                remoteDataSource.getMovies()

            override fun saveCallResult(data: MovieResponse) {
                val movieList = ArrayList<MovieEntity>()
                for (movie in data.results) {
                    val movie = MovieEntity(
                        movie.id,
                        movie.title,
                        movie.date,
                        movie.rating,
                        movie.overview,
                        movie.poster
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(MoviesEntity(1, movieList))
            }
        }.asLiveData()
    }

    override fun getMovie(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieResultsItem>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> = localDataSource.getMovie(movieId)


            override fun shouldFetch(data: MovieEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<MovieResultsItem>> =
                remoteDataSource.getMovie(movieId)

            override fun saveCallResult(data: MovieResultsItem) {
                val movie = MovieEntity(
                    data.id,
                    data.title,
                    data.date,
                    data.rating,
                    data.overview,
                    data.poster
                )

                localDataSource.insertMovie(movie)
            }
        }.asLiveData()
    }

    override fun getTvShows(): LiveData<Resource<TvShowsEntity>> {
        return object : NetworkBoundResource<TvShowsEntity, TvShowResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowsEntity> = localDataSource.getTvShows()

            override fun shouldFetch(data: TvShowsEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<TvShowResponse>> =
                remoteDataSource.getTvShows()

            override fun saveCallResult(data: TvShowResponse) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (tvShow in data.results) {
                    val tvShow = TvShowEntity(
                        tvShow.id,
                        tvShow.title,
                        tvShow.date,
                        tvShow.rating,
                        tvShow.overview,
                        tvShow.poster
                    )
                    tvShowList.add(tvShow)
                }
                localDataSource.insertTvShows(TvShowsEntity(1, tvShowList))
            }
        }.asLiveData()
    }

    override fun getTvShow(tvShowId: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvShowResultsItem>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> = localDataSource.getTvShow(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<TvShowResultsItem>> =
                remoteDataSource.getTvShow(tvShowId)

            override fun saveCallResult(data: TvShowResultsItem) {
                val tvShow = TvShowEntity(
                    data.id,
                    data.title,
                    data.date,
                    data.rating,
                    data.overview,
                    data.poster
                )

                localDataSource.insertTvShow(tvShow)
            }
        }.asLiveData()
    }

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movie, state) }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setFavoriteTvShow(tvShow, state) }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()
    }
}