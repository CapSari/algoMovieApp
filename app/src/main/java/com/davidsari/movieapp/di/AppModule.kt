package com.davidsari.movieapp.di

import android.app.Application
import androidx.room.Room
import com.davidsari.movieapp.api.MovieAppApi
import com.davidsari.movieapp.data.popularmovies.PopularMovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit =
        Retrofit.Builder()
            .baseUrl(MovieAppApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit) : MovieAppApi =
        retrofit.create(MovieAppApi::class.java)



    @Provides
    @Singleton
    fun providePopularDatabase(app: Application) : PopularMovieDatabase =
        Room.databaseBuilder(app,PopularMovieDatabase::class.java, "popular movies database")
            .build()
}