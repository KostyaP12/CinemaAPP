package com.example.cinemaapp.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.cinemaapp.repositories.MovieRepository
import com.example.cinemaapp.responses.MovieResponse


class HomeViewModel {
    private val movieRepository = MovieRepository()

    private val observerNowPlayingMovies = MutableLiveData<MovieResponse>()
    fun getObserverNowPlayingMovies() = observerNowPlayingMovies

    fun lookNowMovie() {
        movieRepository.getNowPlayingMovies(observerNowPlayingMovies)
    }
    val dataNowPlayingMovies = MutableLiveData<String>()
}