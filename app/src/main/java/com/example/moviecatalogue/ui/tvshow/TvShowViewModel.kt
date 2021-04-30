package com.example.moviecatalogue.ui.tvshow

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.VideoRepository

class TvShowViewModel(private val videoRepository: VideoRepository) : ViewModel() {

    fun getTvShows() = videoRepository.getTvShows()
}