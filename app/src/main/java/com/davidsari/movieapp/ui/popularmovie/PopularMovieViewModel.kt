package com.davidsari.movieapp.ui.popularmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.davidsari.movieapp.data.popularmovies.PopularMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularMovieViewModel @Inject constructor(
    repository: PopularMovieRepository
) : ViewModel() {
    fun searchMovie() {

    }

    val movies = repository.getPopularMovies().asLiveData()

    /*private val restaurantsLiveData = MutableLiveData<List<Restaurant>>()
    val restaurants : LiveData<List<Restaurant>> = restaurantsLiveData

    init {
        viewModelScope.launch {
            val restaurants = api.getRestaurants()
            delay(2000)
            restaurantsLiveData.value = restaurants
        }
    }*/
}