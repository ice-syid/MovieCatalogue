package com.example.moviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.VideoRepository
import com.example.moviecatalogue.data.source.remote.api.response.VideoResponse

class MovieViewModel(private val videoRepository: VideoRepository) : ViewModel() {

    fun getMovies() = videoRepository.getMovies()
}