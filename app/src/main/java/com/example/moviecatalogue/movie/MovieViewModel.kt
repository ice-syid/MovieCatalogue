package com.example.moviecatalogue.movie

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.core.data.VideoRepository

class MovieViewModel(private val videoRepository: VideoRepository) : ViewModel() {

    fun getMovies() = videoRepository.getMovies()
}