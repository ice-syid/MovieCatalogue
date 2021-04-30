package com.example.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.VideoRepository

class DetailVideoViewModel(private val videoRepository: VideoRepository) : ViewModel() {
    private var videoId: Int = 0
    private var videoType: Int = 0

    fun setSelectedVideo(videoId: Int, videoType: Int) {
        this.videoId = videoId
        this.videoType = videoType
    }

    fun getVideo(id: Int) =
        when (videoType) {
            1 -> videoRepository.getMovie(id)
            2 -> videoRepository.getTvShow(id)
            else -> null
        }
}