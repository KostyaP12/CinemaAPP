package com.example.cinemaapp.model

class RepositoryImpl : Repository {
    override fun getOriginalSourcePreviewFilms(): List<OriginalSourcePreview> {
        return getAllFilms()
    }

    override fun getOriginalSourcePreviewFavoriteFilms(): List<OriginalSourcePreview> {
        return getFavoritesFilms()
    }

    override fun getOriginalSourcePreviewTopRatingFilms(): List<OriginalSourcePreview> {
       return getTopRatingFilms()
    }

}