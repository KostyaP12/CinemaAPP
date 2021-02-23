package com.example.cinemaapp.responses

import com.example.cinemaapp.model.Movie
import java.io.Serializable

data class MovieResponse(
    val page: Int,
    val total_pages: Int,
    val results: ArrayList<Movie>
) : Serializable