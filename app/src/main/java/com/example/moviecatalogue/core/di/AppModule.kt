package com.example.moviecatalogue.core.di

import androidx.room.Room
import com.example.moviecatalogue.BuildConfig.BASE_URL
import com.example.moviecatalogue.core.data.VideoRepository
import com.example.moviecatalogue.core.data.source.local.LocalDataSource
import com.example.moviecatalogue.core.data.source.local.room.VideoDatabase
import com.example.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.core.data.source.remote.api.network.ApiService
import com.example.moviecatalogue.detail.DetailVideoViewModel
import com.example.moviecatalogue.favorite.FavoriteViewModel
import com.example.moviecatalogue.movie.MovieViewModel
import com.example.moviecatalogue.tvshow.TvShowViewModel
import com.example.moviecatalogue.core.utils.AppExecutors
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    single { retrofit.create(ApiService::class.java) }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            VideoDatabase::class.java,
            "Videos.db"
        ).fallbackToDestructiveMigration().build()
    }

    single { get<VideoDatabase>().videoDao() }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single { LocalDataSource(get()) }
    single { AppExecutors() }
    single { VideoRepository(get(), get(), get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailVideoViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}