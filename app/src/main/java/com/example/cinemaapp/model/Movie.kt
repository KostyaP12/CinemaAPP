package com.example.cinemaapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("original_title")
    @Expose
    val title: String,
    @SerializedName("overview")
    @Expose
    val description: String,
    @SerializedName("poster_path")
    @Expose
    val poster: String
)