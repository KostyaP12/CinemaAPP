package com.example.cinemaapp.api

import com.example.cinemaapp.model.API_LINK
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val retrofit by lazy {
        Retrofit.Builder().baseUrl(API_LINK).addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api:ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}