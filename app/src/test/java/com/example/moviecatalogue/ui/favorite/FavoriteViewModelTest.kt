package com.example.moviecatalogue.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalogue.data.VideoRepository
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {
    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var tvShowPagedList: PagedList<TvShowEntity>

    @Mock
    private lateinit var repository: VideoRepository

    @Mock
    private lateinit var observerFavoriteMovies: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var observerFavoriteTvShows: Observer<PagedList<TvShowEntity>>

    @Before
    fun setup() {
        viewModel = FavoriteViewModel(repository)
    }

    @Test
    fun getFavoriteMovies() {
        val dummyFavorites = MutableLiveData<PagedList<MovieEntity>>()
        dummyFavorites.value = moviePagedList
        `when`(repository.getFavoriteMovies()).thenReturn(dummyFavorites)

        val expectedFavorites = viewModel.getFavoriteMovies()
        verify(repository).getFavoriteMovies()
        assertNotNull(expectedFavorites)

        viewModel.getFavoriteMovies().observeForever(observerFavoriteMovies)
        verify(observerFavoriteMovies).onChanged(expectedFavorites.value)
    }

    @Test
    fun getFavoriteMoviesNull() {
        val expectedFavorites = viewModel.getFavoriteMovies()
        verify(repository).getFavoriteMovies()

        assertNull(expectedFavorites)
    }

    @Test
    fun getFavoriteTvShows() {
        val dummyFavorites = MutableLiveData<PagedList<TvShowEntity>>()
        dummyFavorites.value = tvShowPagedList
        `when`(repository.getFavoriteTvShows()).thenReturn(dummyFavorites)

        val expectedFavorites = viewModel.getFavoriteTvShows()
        verify(repository).getFavoriteTvShows()
        assertNotNull(expectedFavorites)

        viewModel.getFavoriteTvShows().observeForever(observerFavoriteTvShows)
        verify(observerFavoriteTvShows).onChanged(expectedFavorites.value)
    }

    @Test
    fun getFavoriteTvShowsNull() {
        val expectedFavorites = viewModel.getFavoriteTvShows()
        verify(repository).getFavoriteTvShows()

        assertNull(expectedFavorites)
    }
}