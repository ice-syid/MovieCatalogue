package com.example.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.api.response.MovieResultsItem
import com.example.moviecatalogue.data.source.remote.api.response.TvShowResultsItem
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.utils.LiveDataTestUtil
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
    private val videoRepository = FakeVideoRepository(remote)

    private val movies = MutableLiveData<ArrayList<MovieResultsItem>>()
    private val movie = MutableLiveData<MovieResultsItem>()
    private val tvShows = MutableLiveData<ArrayList<TvShowResultsItem>>()
    private val tvShow = MutableLiveData<TvShowResultsItem>()

    private val movieResponses = DataDummy.generateDummyMovies()
    private val movieId = movieResponses[0].id
    private val tvShowResponses = DataDummy.generateDummyTvShows()
    private val tvShowId = tvShowResponses[0].id

    @Test
    fun getMovies() {
        movies.value = movieResponses
        `when`(remote.getMovies()).thenReturn(movies)
        val movieEntities = LiveDataTestUtil.getValue(videoRepository.getMovies())
        verify(remote).getMovies()
        assertNotNull(movieEntities)
        assertEquals(movieEntities.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getMovie() {
        movie.value = movieResponses[0]
        `when`(remote.getMovie(movieId)).thenReturn(movie)
        val movieEntity = LiveDataTestUtil.getValue(videoRepository.getMovie(movieId))
        verify(remote).getMovie(movieId)
        assertNotNull(movieEntity)
        assertEquals(movie.value, movieEntity)
    }

    @Test
    fun getTvShows() {
        tvShows.value = tvShowResponses
        `when`(remote.getTvShows()).thenReturn(tvShows)
        val tvShowEntities = LiveDataTestUtil.getValue(videoRepository.getTvShows())
        verify(remote).getTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowEntities.size.toLong(), tvShowEntities.size.toLong())
    }

    @Test
    fun getTvShow() {
        tvShow.value = tvShowResponses[0]
        `when`(remote.getTvShow(tvShowId)).thenReturn(tvShow)
        val tvShowEntity = LiveDataTestUtil.getValue(videoRepository.getTvShow(tvShowId))
        verify(remote).getTvShow(tvShowId)
        assertNotNull(tvShowEntity)
        assertEquals(tvShow.value, tvShowEntity)
    }
}