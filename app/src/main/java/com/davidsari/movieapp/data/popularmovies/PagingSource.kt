/*
package com.davidsari.movieapp.data.popularmovies

import androidx.paging.PagingSource
import com.codinginflow.imagesearchapp.api.UnsplashApi
import com.davidsari.movieapp.api.MovieAppApi
import retrofit2.HttpException
import java.io.IOException

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class UnsplashPagingSource (
    private val movieAppApi: MovieAppApi,
    private val query: String
        ): PagingSource<Int,PopularMovie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMovie> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
        val response = "unsplashApi.searchPhotos(query,position,params.loadSize)"
        val photos = "response.results"

            LoadResult.Page(
                data = photos,
                prevKey =  if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position -1,
                nextKey = if (photos.isEmpty()) null else position +1,
            )
        } catch (exception : IOException ){
            LoadResult.Error(exception)
        }
         catch (exception : HttpException ){
             LoadResult.Error(exception)
         }
    }
}*/
