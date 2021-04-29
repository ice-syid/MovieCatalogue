package com.example.moviecatalogue.data

import com.example.moviecatalogue.data.source.local.entity.VideoEntity

interface VideoDataSource {

    fun getMovies(): ArrayList<VideoEntity>

    fun getTvShows(): ArrayList<VideoEntity>
}