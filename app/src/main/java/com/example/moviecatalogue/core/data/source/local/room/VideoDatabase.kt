package com.example.moviecatalogue.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.core.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.core.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.core.data.source.local.entity.TvShowsEntity

@Database(
    entities = [MoviesEntity::class, MovieEntity::class, TvShowsEntity::class, TvShowEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class VideoDatabase : RoomDatabase() {
    abstract fun videoDao(): VideoDao
}