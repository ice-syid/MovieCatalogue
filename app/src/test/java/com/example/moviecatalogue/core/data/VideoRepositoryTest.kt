package com.example.moviecatalogue.core.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.core.data.source.local.LocalDataSource
import com.example.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.core.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.core.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.core.data.source.local.entity.TvShowsEntity
import com.example.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.core.utils.AppExecutors
import com.example.moviecatalogue.core.utils.DataDummy
import com.example.moviecatalogue.core.utils.LiveDataTestUtil
import com.example.moviecatalogue.core.utils.PagedListUtil
import com.example.moviecatalogue.core.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class VideoRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val videoRepository = FakeVideoRepository(remote, local, appExecutors)

    private val movieResponses = DataDummy.generateDummyMovies()
    private val movieId = movieResponses[0].id
    private val tvShowResponses = DataDummy.generateDummyTvShows()
    private val tvShowId = tvShowResponses[0].id

    @Test
    fun getMovies() {
        val dummyMovies = MutableLiveData<MoviesEntity>()
        dummyMovies.value = MoviesEntity(1, DataDummy.generateDummyMovies())
        `when`(local.getMovies()).thenReturn(dummyMovies)

        val movieEntities = LiveDataTestUtil.getValue(videoRepository.getMovies())
        verify(local).getMovies()
        assertNotNull(movieEntities.data)
        assertEquals(
            movieResponses.size.toLong(),
            movieEntities.data?.results?.size?.toLong()
        )
    }

    @Test
    fun getMovie() {
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = movieResponses[0]
        `when`(local.getMovie(movieId)).thenReturn(dummyMovie)
        val movieEntity = LiveDataTestUtil.getValue(videoRepository.getMovie(movieId))
        verify(local).getMovie(movieId)
        assertNotNull(movieEntity.data)
        assertEquals(dummyMovie.value, movieEntity.data)
    }

    @Test
    fun getTvShows() {
        val dummyTvShows = MutableLiveData<TvShowsEntity>()
        dummyTvShows.value = TvShowsEntity(1, DataDummy.generateDummyTvShows())
        `when`(local.getTvShows()).thenReturn(dummyTvShows)
        val tvShowEntities = LiveDataTestUtil.getValue(videoRepository.getTvShows())
        verify(local).getTvShows()
        assertNotNull(tvShowEntities.data)
        assertEquals(
            tvShowResponses.size.toLong(),
            tvShowEntities.data?.results?.size?.toLong()
        )
    }

    @Test
    fun getTvShow() {
        val dummyTvShow = MutableLiveData<TvShowEntity>()
        dummyTvShow.value = tvShowResponses[0]
        `when`(local.getTvShow(tvShowId)).thenReturn(dummyTvShow)
        val tvShowEntity = LiveDataTestUtil.getValue(videoRepository.getTvShow(tvShowId))
        verify(local).getTvShow(tvShowId)
        assertNotNull(tvShowEntity.data)
        assertEquals(dummyTvShow.value, tvShowEntity.data)
    }

    @Test
    fun getFavoriteMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        videoRepository.getFavoriteMovies()

        val favoriteMovies =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getFavoriteMovies()
        assertNotNull(favoriteMovies)
    }

    @Test
    fun getFavoriteTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavoriteTvShows()).thenReturn(dataSourceFactory)
        videoRepository.getFavoriteTvShows()

        val favoriteTvShows =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
        verify(local).getFavoriteTvShows()
        assertNotNull(favoriteTvShows)
    }
}