package com.davidsari.movieapp.data.popularmovies

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.davidsari.movieapp.api.PopularResults



@Entity( tableName = "popular movies")
data class PopularMovie (
    @PrimaryKey val page:Int,
    val id:Int,
    val total_pages:Int,
    val total_results:Int,
    val results:List<PopularResults>
)
