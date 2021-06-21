package com.example.moviecatalogue.tvshow

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.core.data.VideoRepository

class TvShowViewModel(private val videoRepository: VideoRepository) : ViewModel() {

    fun getTvShows() = videoRepository.getTvShows()
}