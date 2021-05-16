package com.example.moviecatalogue.ui.favorite

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.VideoRepository

class FavoriteViewModel(private val videoRepository: VideoRepository) : ViewModel() {
    fun getFavoriteMovies() = videoRepository.getFavoriteMovies()
    fun getFavoriteTvShows() = videoRepository.getFavoriteTvShows()
}