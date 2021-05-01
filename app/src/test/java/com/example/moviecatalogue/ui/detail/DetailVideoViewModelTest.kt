package com.example.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.VideoRepository
import com.example.moviecatalogue.data.source.remote.api.response.MovieResultsItem
import com.example.moviecatalogue.data.source.remote.api.response.TvShowResultsItem
import com.example.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailVideoViewModelTest {
    private lateinit var viewModel: DetailVideoViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]
    private val movieId = dummyMovie.id
    private val tvShowId = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var videoRepository: VideoRepository

    @Mock
    private lateinit var movieObserver: Observer<Any>

    @Mock
    private lateinit var tvShowObserver: Observer<Any>

    @Before
    fun setUp() {
        viewModel = DetailVideoViewModel(videoRepository)
        viewModel.setSelectedVideo(movieId, 1)
        viewModel.setSelectedVideo(tvShowId, 2)
    }

    @Test
    fun getMovie() {
        val movies = MutableLiveData<MovieResultsItem>()
        movies.value = dummyMovie

        `when`(videoRepository.getMovie(movieId)).thenReturn(movies)
        viewModel.setSelectedVideo(dummyMovie.id, 1)
        val movieEntity = viewModel.getVideo(movieId)?.value as MovieResultsItem
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity.id)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.date, movieEntity.date)
        assertEquals(dummyMovie.rating, movieEntity.rating, 0.0)
        assertEquals(dummyMovie.overview, movieEntity.overview)
        assertEquals(dummyMovie.poster, movieEntity.poster)

        viewModel.getVideo(movieId)?.observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getTvShow() {
        val tvShows = MutableLiveData<TvShowResultsItem>()
        tvShows.value = dummyTvShow

        `when`(videoRepository.getTvShow(tvShowId)).thenReturn(tvShows)
        viewModel.setSelectedVideo(dummyTvShow.id, 2)
        val tvShowEntity = viewModel.getVideo(tvShowId)?.value as TvShowResultsItem
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.id, tvShowEntity.id)
        assertEquals(dummyTvShow.title, tvShowEntity.title)
        assertEquals(dummyTvShow.date, tvShowEntity.date)
        assertEquals(dummyTvShow.rating, tvShowEntity.rating, 0.0)
        assertEquals(dummyTvShow.overview, tvShowEntity.overview)
        assertEquals(dummyTvShow.poster, tvShowEntity.poster)

        viewModel.getVideo(tvShowId)?.observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShow)
    }
}