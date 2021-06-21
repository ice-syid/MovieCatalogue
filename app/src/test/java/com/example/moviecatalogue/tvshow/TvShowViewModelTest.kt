package com.example.moviecatalogue.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.core.data.VideoRepository
import com.example.moviecatalogue.core.data.source.local.entity.TvShowsEntity
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
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var videoRepository: VideoRepository

    @Mock
    private lateinit var observer: Observer<Resource<TvShowsEntity>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(videoRepository)
    }

    @Test
    fun getTvShows() {
        val dummyTvShows = Resource.success(TvShowsEntity(1, DataDummy.generateDummyTvShows()))
        val tvShows = MutableLiveData<Resource<TvShowsEntity>>()
        tvShows.value = dummyTvShows

        `when`(videoRepository.getTvShows()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getTvShows().value?.data
        verify(videoRepository).getTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities?.results?.size)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }

    @Test
    fun getTvShowsNull() {
        val tvShowsExpected = viewModel.getTvShows()
        verify(videoRepository).getTvShows()

        assertNull(tvShowsExpected)
    }
}