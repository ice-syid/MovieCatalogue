package com.example.moviecatalogue.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.core.data.VideoRepository
import com.example.moviecatalogue.core.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.core.utils.DataDummy
import com.example.moviecatalogue.core.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var videoRepository: VideoRepository

    @Mock
    private lateinit var observer: Observer<Resource<MoviesEntity>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(videoRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = Resource.success(MoviesEntity(1, DataDummy.generateDummyMovies()))
        val movies = MutableLiveData<Resource<MoviesEntity>>()
        movies.value = dummyMovies

        `when`(videoRepository.getMovies()).thenReturn(movies)
        val movieEntities = viewModel.getMovies().value?.data
        verify(videoRepository).getMovies()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.results?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getMoviesNull() {
        val moviesExpected = viewModel.getMovies()
        verify(videoRepository).getMovies()

        assertNull(moviesExpected)
    }
}