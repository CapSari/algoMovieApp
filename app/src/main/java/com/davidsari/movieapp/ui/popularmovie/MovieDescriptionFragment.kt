package com.davidsari.movieapp.ui.popularmovie

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.davidsari.movieapp.R
import com.davidsari.movieapp.databinding.FragmentMovieDescriptionBinding
import kotlinx.android.synthetic.main.fragment_movie_description.*

class MovieDescriptionFragment : Fragment(R.layout.fragment_movie_description) {

    private val args by navArgs<MovieDescriptionFragmentArgs>()

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMovieDescriptionBinding.bind(view)

        binding.apply {
            Glide.with( this@MovieDescriptionFragment)
                .load("https://image.tmdb.org/t/p/w500"+args.posterPath)
                .error(R.drawable.ic_error)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        movieName.isVisible = true
                        movieDescription.isVisible = true
                        movieId.isVisible = true
                        movieOriginalLanguage.isVisible = true
                        movieOriginalTitle.isVisible = true
                        movieReleaseDate.isVisible = true
                        movieVoteCount.isVisible = true
                        return false
                    }

                })
                .into(movie_image_view)

            movieName.text = args.title
            movieDescription.text = args.overview
            movieId.text = "Movie ID:"+" "+args.id
            movieOriginalLanguage.text = "Movie Original Language:"+" "+args.originalLanguage
            movieOriginalTitle.text = "Movie Original Title:"+" "+args.originalTitle
            movieReleaseDate.text = "Movie Release Date:"+" "+args.releaseDate
            movieVoteCount.text = "Movie Vote Count:"+" "+args.voteCount
        }
    }


}