package com.example.cinemaapp.ui.home

import androidx.lifecycle.*
import com.example.cinemaapp.model.AppState
import com.example.cinemaapp.model.Repository
import com.example.cinemaapp.model.RepositoryImpl

class HomeViewModel(
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
                repository.getOriginalSourcePreviewFilms()
            ))
        }.start()
    }

    override fun onCleared() {
        super.onCleared()
    }
}