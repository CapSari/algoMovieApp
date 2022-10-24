package com.davidsari.movieapp.data.popularmovies

import androidx.room.withTransaction
import com.davidsari.movieapp.api.MovieAppApi
import com.davidsari.movieapp.utils.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class PopularMovieRepository @Inject constructor(
    private val api: MovieAppApi,
    private val db: PopularMovieDatabase
) {
    private val popularMovieDao = db.popularMovieDao()

    fun getPopularMovies() = networkBoundResource(
        query = {
            popularMovieDao.getAllPopularMovies()
        },
        fetch = {
            delay(2000)
            api.getPopularMovies()
        },
        saveFetchResult = { popular_movies ->
            db.withTransaction {
                //popularMovieDao.deleteAllPopularMovies()
                popularMovieDao.insertPopularMovies(popular_movies)
            }

        }
    )
}