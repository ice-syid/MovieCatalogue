package com.example.moviecatalogue.data.source.local.room

import androidx.room.TypeConverter
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.google.gson.Gson

class Converters {
    companion object {
        // --------------------------------------------------------------------- MOVIES CONVERTER
        @TypeConverter
        @JvmStatic
        fun fromMovies(value: MutableList<MovieEntity>?): String {
            return Gson().toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun toMovies(value: String): MutableList<MovieEntity> {
            val objects =
                Gson().fromJson(value, Array<MovieEntity>::class.java) as Array<MovieEntity>
            return objects.toMutableList()
        }

        // --------------------------------------------------------------------- TV SHOWS CONVERTER
        @TypeConverter
        @JvmStatic
        fun fromTvShows(value: MutableList<TvShowEntity>?): String {
            return Gson().toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun toTvShows(value: String): MutableList<TvShowEntity> {
            val objects =
                Gson().fromJson(value, Array<TvShowEntity>::class.java) as Array<TvShowEntity>
            return objects.toMutableList()
        }
    }
}