package com.davidsari.movieapp.api

import com.davidsari.movieapp.data.popularmovies.PopularMovie
import retrofit2.http.GET

interface MovieAppApi {

    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/"
        //API_KEY format should start with "?api_key={add your api key here}"
        //Example: const val API_KEY = "?api_key=1234567890"
        const val API_KEY = "?api_key=1234567890"
        const val API_READ_ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkN2QyMDU4MDIwN2FlMjRlZTUzYTJhMzBhN2RiYjhjNyIsInN1YiI6IjYyYWI1YjA2ODc1ZDFhNTI3OWVmN2I3NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.GUcgt83iLl1GwxGBostTEZrSH1BjwZ82he_1nv6r_z0"
    }


    @GET("movie/popular$API_KEY&language=en-US&page=10")
    suspend fun getPopularMovies() : PopularMovie
}