package com.example.moviecatalogue.di

import android.content.Context
import com.example.moviecatalogue.data.VideoRepository
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.api.service.RetrofitClient

object Injection {

    fun provideRepository(context: Context): VideoRepository {
        val client = RetrofitClient

        val remoteDataSource = RemoteDataSource.getInstance(client)

        return VideoRepository.getInstance(remoteDataSource)
    }
}