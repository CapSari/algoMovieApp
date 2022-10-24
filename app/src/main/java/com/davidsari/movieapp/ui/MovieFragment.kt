package com.davidsari.movieapp.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.davidsari.movieapp.R
import com.davidsari.movieapp.api.PopularResults
import com.davidsari.movieapp.databinding.FragmentMovieBinding
import com.davidsari.movieapp.ui.popularmovie.PopularMovieAdapter
import com.davidsari.movieapp.ui.popularmovie.PopularMovieViewModel
import com.davidsari.movieapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment(
    R.layout.fragment_movie
), PopularMovieAdapter.OnItemClickListener {

    private val viewModel by viewModels<PopularMovieViewModel>()

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        _binding = FragmentMovieBinding.bind(view)

        val popularMovieAdapter = PopularMovieAdapter(this)

        binding.apply {
            recyclerView.apply {
                adapter = popularMovieAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }

        viewModel.movies.observe(viewLifecycleOwner) { result ->
            popularMovieAdapter.submitList(result.data?.results)

            binding.apply {

                progressBar.isVisible =
                    result is Resource.Loading && result.data?.results?.isNotEmpty() ?: true
                textViewError.isVisible =
                    result is Resource.Error && result.data?.results?.isNotEmpty() ?: true
                textViewError.text = result.error?.localizedMessage
            }


        }
        setHasOptionsMenu(true)


    }

    override fun onItemClicked(photo: PopularResults) {
        val backDropPath = photo.backdrop_path
        val posterPath = photo.poster_path
        val id = photo.id
        val originalLanguage = photo.original_language
        val originalTitle = photo.original_title
        val overview = photo.overview
        val releaseDate = photo.release_date
        val title = photo.title
        val voteCount = photo.vote_count
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDescriptionFragment(
            backdropPath = backDropPath,
            posterPath = posterPath,
            id = id.toString(),
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            releaseDate = releaseDate,
            title = title,
            voteCount = voteCount.toString()
        )
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_gallery, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    binding.recyclerView.scrollToPosition(0)
                    viewModel.searchMovie()
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }
}