package com.davidsari.movieapp.ui.popularmovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.davidsari.movieapp.R
import com.davidsari.movieapp.api.PopularResults
import com.davidsari.movieapp.databinding.PopularMovieItemBinding

class PopularMovieAdapter(private val listener: OnItemClickListener) :
    ListAdapter<PopularResults, PopularMovieAdapter.PopularMovieViewHolder>(PopularMovieComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        val binding =
            PopularMovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PopularMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null){
            holder.bind(currentItem)
        }
    }

    inner class PopularMovieViewHolder( private val binding: PopularMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION){
                    val item = getItem(position)
                    if (item != null){
                        listener.onItemClicked(item)
                    }
                }
            }
        }

        fun bind(popularMovie: PopularResults){
            binding.apply {
                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/w500"+popularMovie.backdrop_path)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imageViewLogo)

                textViewName.text = popularMovie.title
                //textViewType.text = popularMovie.original_title
                //textViewAddress.text = popularMovie.release_date
            }
        }
    }
    interface OnItemClickListener{
        fun onItemClicked(photo: PopularResults)
    }
    class PopularMovieComparator : DiffUtil.ItemCallback<PopularResults>(){
        override fun areItemsTheSame(oldItem: PopularResults, newItem: PopularResults) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: PopularResults, newItem: PopularResults) =
            oldItem == newItem

    }
}