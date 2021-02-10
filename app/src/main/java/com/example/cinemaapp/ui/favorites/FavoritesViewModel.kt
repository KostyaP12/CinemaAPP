package com.example.cinemaapp.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinemaapp.model.AppState
import com.example.cinemaapp.model.Repository
import com.example.cinemaapp.model.RepositoryImpl

class FavoritesViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: Repository = RepositoryImpl()
) :
    ViewModel() {
    init {
        generateDataFromLocalSource()
    }
    fun getDataFromLocalSource() = generateDataFromLocalSource()

    fun getLiveData() = liveDataToObserve

    private fun generateDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            Thread.sleep(1000)
            liveDataToObserve.postValue(AppState.Success(
                repository.getOriginalSourcePreviewFavoriteFilms()
            ))
        }.start()
    }

    override fun onCleared() {
        super.onCleared()
    }
}