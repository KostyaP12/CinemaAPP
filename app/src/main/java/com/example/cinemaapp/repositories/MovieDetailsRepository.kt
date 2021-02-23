package com.example.cinemaapp.repositories

import androidx.lifecycle.MutableLiveData
import com.example.cinemaapp.api.ApiClient
import com.example.cinemaapp.api.ApiService
import com.example.cinemaapp.model.API_KEY
import com.example.cinemaapp.model.MovieAllDetails
import retrofit2.Call
import retrofit2.Response

class MovieDetailsRepository {
    private var apiService: ApiService = ApiClient.api

    fun getMovieInfo(observingMovie: MutableLiveData<MovieAllDetails>, id: Int) {
        apiService.getDetailsMovie(id, API_KEY, "ru" ).enqueue(object :
            retrofit2.Callback<MovieAllDetails> {
            override fun onResponse(
                call: Call<MovieAllDetails>,
                response: Response<MovieAllDetails>
            ) {
                observingMovie.value = response.body()
            }

            override fun onFailure(call: Call<MovieAllDetails>, t: Throwable) = t.printStackTrace()
        })
    }
}