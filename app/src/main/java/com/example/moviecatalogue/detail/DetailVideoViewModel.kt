package com.example.moviecatalogue.detail

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.core.data.VideoRepository
import com.example.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.core.data.source.local.entity.TvShowEntity

class DetailVideoViewModel(private val videoRepository: VideoRepository) : ViewModel() {
    private var videoId: Int = 0
    private var videoType: Int = 0

    fun setSelectedVideo(videoId: Int?, videoType: Int?) {
        if (videoId != null) {
            this.videoId = videoId
        }
        if (videoType != null) {
            this.videoType = videoType
        }
    }

    fun getVideo(id: Int?) =
        when (videoType) {
            1 -> id?.let { videoRepository.getMovie(it) }
            2 -> id?.let { videoRepository.getTvShow(it) }
            else -> null
        }

    fun setFavoriteMovie(movie: MovieEntity) {
        val state = !movie.isFavorite
        videoRepository.setFavoriteMovie(movie, state)
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity) {
        val state = !tvShow.isFavorite
        videoRepository.setFavoriteTvShow(tvShow, state)
    }
}