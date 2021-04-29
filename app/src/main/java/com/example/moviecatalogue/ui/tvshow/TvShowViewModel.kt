package com.example.moviecatalogue.ui.tvshow

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.VideoRepository
import com.example.moviecatalogue.data.source.local.entity.VideoEntity
import com.example.moviecatalogue.data.source.remote.api.response.VideoResponse
import com.example.moviecatalogue.utils.DataDummy

class TvShowViewModel(private val videoRepository: VideoRepository) : ViewModel() {

    fun getTvShows() = videoRepository.getTvShows()
}