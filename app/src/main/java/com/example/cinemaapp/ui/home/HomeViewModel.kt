package com.example.cinemaapp.ui.home

import androidx.lifecycle.*
import com.example.cinemaapp.repostitory.CardViewFilms

class HomeViewModel(
    private val liveDataToObserve: MutableLiveData<Any> =
        MutableLiveData()
) : ViewModel(), LifecycleObserver {
   var cardViewFilms : CardViewFilms = CardViewFilms()
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStart() {
        liveDataToObserve.value = cardViewFilms
    }
    fun getData(): LiveData<Any> {
        return liveDataToObserve
    }
}