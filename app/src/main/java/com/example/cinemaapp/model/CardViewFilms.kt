package com.example.cinemaapp.model

import android.os.Parcelable
import com.example.cinemaapp.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CardViewFilms(
    var title: String,
    var description: String,
    var poster: Int
):Parcelable