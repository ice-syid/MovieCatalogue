package com.example.moviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.VideoRepository

class MovieViewModel(private val videoRepository: VideoRepository) : ViewModel() {

    fun getMovies() = videoRepository.getMovies()
}