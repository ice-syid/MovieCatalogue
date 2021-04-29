package com.example.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.source.local.entity.VideoEntity
import com.example.moviecatalogue.utils.DataDummy

class DetailVideoViewModel : ViewModel() {
    private var videoId: Int = 0
    private var videoType: Int = 0

    fun setSelectedVideo(videoId: Int, videoType: Int) {
        this.videoId = videoId
        this.videoType = videoType
    }

    fun getVideo(): VideoEntity? {
//        val videoEntities = when (videoType) {
//            1 -> DataDummy.generateDummyMovies()
//            2 -> DataDummy.generateDummyTvShows()
//            else -> null
//        }
//        return videoEntities?.find { it.id == videoId }
        return null
    }
}