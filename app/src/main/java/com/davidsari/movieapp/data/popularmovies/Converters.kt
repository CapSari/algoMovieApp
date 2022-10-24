package com.davidsari.movieapp.data.popularmovies

import androidx.room.TypeConverter
import com.davidsari.movieapp.api.PopularResults
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    @TypeConverter
    fun toPopularMovies(list: String): List<PopularResults> {
        return Gson().fromJson(list,Array<PopularResults>::class.java).toList()
    }

    @TypeConverter
    fun fromPopularMovies(list: List<PopularResults>): String {
        return Gson().toJson(list,object : TypeToken<List<PopularResults>>(){}.type)
    }
}