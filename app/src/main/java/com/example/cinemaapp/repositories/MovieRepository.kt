package com.example.cinemaapp.repositories
import androidx.lifecycle.MutableLiveData
import com.example.cinemaapp.api.ApiClient
import com.example.cinemaapp.api.ApiService
import com.example.cinemaapp.model.API_KEY
import com.example.cinemaapp.responses.MovieResponse
import retrofit2.Call
import retrofit2.Response

class MovieRepository {
    private var apiService: ApiService = ApiClient.api

    fun getPopularMovies(_observingMovies: MutableLiveData<MovieResponse>) {
        apiService.getPopularMovies(API_KEY, "ru").enqueue(object :
            retrofit2.Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                _observingMovies.value = response.body()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _observingMovies.value = null
            }
        })
    }

    fun getNowPlayingMovies(observer: MutableLiveData<MovieResponse>) {
        apiService.getNowPlayingMovies(API_KEY, "ru").enqueue(object :
            retrofit2.Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                observer.value = response.body()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                observer.value = null
            }
        })
    }

    fun getUpComingMovies(_observingMovies: MutableLiveData<MovieResponse>) {
        apiService.getUpcomingMovies(API_KEY, "ru").enqueue(object :
            retrofit2.Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                _observingMovies.value = response.body()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _observingMovies.value = null
            }
        })

    }


    fun getTopMovies(_observingMovies: MutableLiveData<MovieResponse>) {
        apiService.getTopMovies(API_KEY, "ru").enqueue(object :
            retrofit2.Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                _observingMovies.value = response.body()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _observingMovies.value = null
            }
        })
    }
}