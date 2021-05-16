package com.example.moviecatalogue.di

import androidx.room.Room
import com.example.moviecatalogue.BuildConfig.BASE_URL
import com.example.moviecatalogue.data.VideoRepository
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.room.VideoDatabase
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.api.service.ApiService
import com.example.moviecatalogue.ui.detail.DetailVideoViewModel
import com.example.moviecatalogue.ui.favorite.FavoriteViewModel
import com.example.moviecatalogue.ui.movie.MovieViewModel
import com.example.moviecatalogue.ui.tvshow.TvShowViewModel
import com.example.moviecatalogue.utils.AppExecutors
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