package com.davidsari.movieapp.data.popularmovies

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PopularMovieDao {

    @Query( "SELECT * FROM `popular movies`")
    fun getAllPopularMovies() : Flow<PopularMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovies(popularMovie: PopularMovie)

    @Query( "DELETE FROM `popular movies`")
    suspend fun deleteAllPopularMovies()
}