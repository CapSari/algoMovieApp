package com.davidsari.movieapp.data.popularmovies

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [PopularMovie::class], version = 6)
@TypeConverters(Converters::class)
abstract  class PopularMovieDatabase : RoomDatabase() {

    abstract fun popularMovieDao() : PopularMovieDao

}