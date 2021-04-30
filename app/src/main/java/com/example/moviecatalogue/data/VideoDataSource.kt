package com.example.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.data.source.local.entity.VideoEntity

interface VideoDataSource {

    fun getMovies(): LiveData<ArrayList<VideoEntity>>

    fun getTvShows(): LiveData<ArrayList<VideoEntity>>
}