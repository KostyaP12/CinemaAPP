package com.example.cinemaapp.model

import com.example.cinemaapp.model.OriginalSourcePreview

sealed class AppState {
    data class Success(val previewData: List<OriginalSourcePreview>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
