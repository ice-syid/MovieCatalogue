package com.example.moviecatalogue.favorite

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.core.data.VideoRepository

class FavoriteViewModel(private val videoRepository: VideoRepository) : ViewModel() {
    fun getFavoriteMovies() = videoRepository.getFavoriteMovies()
    fun getFavoriteTvShows() = videoRepository.getFavoriteTvShows()
}