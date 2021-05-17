package com.example.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.VideoRepository
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.vo.Resource
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
class DetailVideoViewModelTest {
    private lateinit var viewModelMovie: DetailVideoViewModel
    private lateinit var viewModelTvShow: DetailVideoViewModel
    private val dummyMovie = Resource.success(DataDummy.generateDummyMovies()[0])
    private val dummyTvShow = Resource.success(DataDummy.generateDummyTvShows()[0])
    private val movieId = dummyMovie.data?.id
    private val tvShowId = dummyTvShow.data?.id

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
        viewModelMovie = DetailVideoViewModel(videoRepository)
        viewModelMovie.setSelectedVideo(movieId, 1)
        viewModelTvShow = DetailVideoViewModel(videoRepository)
        viewModelTvShow.setSelectedVideo(tvShowId, 2)
    }

    @Test
    fun getMovie() {
        val movies = MutableLiveData<Resource<MovieEntity>>()
        movies.value = dummyMovie

        `when`(movieId?.let { videoRepository.getMovie(it) }).thenReturn(movies)
        viewModelMovie.setSelectedVideo(dummyMovie.data?.id, 1)
        val movieEntity = viewModelMovie.getVideo(movieId)?.value?.data as MovieEntity
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.data?.id, movieEntity.id)
        assertEquals(dummyMovie.data?.originalTitle, movieEntity.originalTitle)
        assertEquals(dummyMovie.data?.releaseDate, movieEntity.releaseDate)
        dummyMovie.data?.voteAverage?.let {
            movieEntity.voteAverage?.let { it1 ->
                assertEquals(
                    it,
                    it1, 0.0
                )
            }
        }
        assertEquals(dummyMovie.data?.overview, movieEntity.overview)
        assertEquals(dummyMovie.data?.posterPath, movieEntity.posterPath)

        viewModelMovie.getVideo(movieId)?.observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getMovieNull() {
        val movieExpected = viewModelMovie.getVideo(movieId)
        movieId?.let { verify(videoRepository).getMovie(it) }

        assertNull(movieExpected)
    }

    @Test
    fun getTvShow() {
        val tvShows = MutableLiveData<Resource<TvShowEntity>>()
        tvShows.value = dummyTvShow

        `when`(tvShowId?.let { videoRepository.getTvShow(it) }).thenReturn(tvShows)
        viewModelTvShow.setSelectedVideo(dummyTvShow.data?.id, 2)
        val tvShowEntity = viewModelTvShow.getVideo(tvShowId)?.value?.data as TvShowEntity
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.data?.id, tvShowEntity.id)
        assertEquals(dummyTvShow.data?.originalName, tvShowEntity.originalName)
        assertEquals(dummyTvShow.data?.firstAirDate, tvShowEntity.firstAirDate)
        dummyTvShow.data?.voteAverage?.let {
            tvShowEntity.voteAverage?.let { it1 ->
                assertEquals(
                    it,
                    it1, 0.0
                )
            }
        }
        assertEquals(dummyTvShow.data?.overview, tvShowEntity.overview)
        assertEquals(dummyTvShow.data?.posterPath, tvShowEntity.posterPath)

        viewModelTvShow.getVideo(tvShowId)?.observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShow)
    }

    @Test
    fun getTvShowNull() {
        val tvShowExpected = viewModelTvShow.getVideo(tvShowId)
        tvShowId?.let { verify(videoRepository).getTvShow(it) }

        assertNull(tvShowExpected)
    }
}