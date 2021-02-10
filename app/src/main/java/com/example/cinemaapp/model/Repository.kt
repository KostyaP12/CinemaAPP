package com.example.cinemaapp.model

interface Repository {
    fun getOriginalSourcePreviewFilms(): List<OriginalSourcePreview>
    fun getOriginalSourcePreviewFavoriteFilms(): List<OriginalSourcePreview>
    fun getOriginalSourcePreviewTopRatingFilms(): List<OriginalSourcePreview>
}