package com.davidsari.movieapp.api

data class PopularResults (
        val backdrop_path:String,
        val id:Int,
        val original_language:String,
        val original_title:String,
        val overview:String,
        val poster_path:String,
        val release_date:String,
        val title:String,
        val vote_count:Int,
    )