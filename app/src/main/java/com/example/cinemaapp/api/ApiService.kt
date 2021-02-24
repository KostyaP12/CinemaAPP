package com.example.cinemaapp.api

import com.example.cinemaapp.responses.ActorsResponse
import com.example.cinemaapp.responses.MovieResponse
import com.example.cinemaapp.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(API_POPULAR_MOVIES)
    fun getPopularMovies(
        @Query("api_key") key: String,
        @Query("language") lang: String
    ):Call<MovieResponse>

    @GET(API_NOW_PLAYING_MOVIES)
    fun getNowPlayingMovies(
        @Query("api_key") key: String,
        @Query("language") lang: String
    ): Call<MovieResponse>

    @GET(API_UPCOMING_MOVIES)
    fun getUpcomingMovies(
        @Query("api_key") key: String,
        @Query("language") lang: String
    ): Call<MovieResponse>

    @GET(API_TOP_MOVIES)
    fun getTopMovies(
        @Query("api_key") key: String,
        @Query("language") lang: String
    ): Call<MovieResponse>

    @GET(API_SEARCH_MOVIES)
    fun getSearchMovie(
        @Query("api_key") key: String,
        @Query("language") lang: String,
        @Query("query") query: String
    ): Call<MovieResponse>

    @GET(API_DETAILS_MOVIES)
    fun getDetailsMovie(@Path("id") id: Int,
                    @Query("api_key") key: String,
                    @Query("language") lang: String
    ): Call<MovieAllDetails>

    @GET(API_ACTORS)
    fun showDetailsActors(@Path("id") id: Int,
                          @Query("api_key") key: String,
                          @Query("language") lang: String
    ): Call<ActorsResponse>
}