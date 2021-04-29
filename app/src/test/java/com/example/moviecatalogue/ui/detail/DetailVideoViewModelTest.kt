package com.example.moviecatalogue.ui.detail

import com.example.moviecatalogue.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailVideoViewModelTest {
    private lateinit var viewModel: DetailVideoViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]
    private val movieId = dummyMovie.id
    private val tvShowId = dummyTvShow.id

    @Before
    fun setUp() {
        viewModel = DetailVideoViewModel()
        viewModel.setSelectedVideo(movieId, 1)
        viewModel.setSelectedVideo(tvShowId, 2)
    }

    @Test
    fun getMovie() {
        viewModel.setSelectedVideo(dummyMovie.id, 1)
        val movieEntity = viewModel.getVideo()
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity?.id)
        assertEquals(dummyMovie.title, movieEntity?.title)
        assertEquals(dummyMovie.release_year, movieEntity?.release_year)
        assertEquals(dummyMovie.genre, movieEntity?.genre)
        assertEquals(dummyMovie.rating, movieEntity?.rating)
        assertEquals(dummyMovie.overview, movieEntity?.overview)
        assertEquals(dummyMovie.poster, movieEntity?.poster)
    }

    @Test
    fun getTvShow() {
        viewModel.setSelectedVideo(dummyTvShow.id, 2)
        val tvShowEntity = viewModel.getVideo()
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.id, tvShowEntity?.id)
        assertEquals(dummyTvShow.title, tvShowEntity?.title)
        assertEquals(dummyTvShow.release_year, tvShowEntity?.release_year)
        assertEquals(dummyTvShow.genre, tvShowEntity?.genre)
        assertEquals(dummyTvShow.rating, tvShowEntity?.rating)
        assertEquals(dummyTvShow.overview, tvShowEntity?.overview)
        assertEquals(dummyTvShow.poster, tvShowEntity?.poster)
    }
}