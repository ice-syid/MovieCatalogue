package com.example.moviecatalogue.di

import android.content.Context
import com.example.moviecatalogue.data.VideoRepository
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.room.VideoDatabase
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.api.service.RetrofitClient
import com.example.moviecatalogue.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): VideoRepository {
        val client = RetrofitClient
        val database = VideoDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(client)
        val localDataSource = LocalDataSource.getInstance(database.videoDao())
        val appExecutors = AppExecutors()

        return VideoRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}