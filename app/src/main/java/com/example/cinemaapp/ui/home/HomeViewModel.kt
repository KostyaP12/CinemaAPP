package com.example.cinemaapp.ui.home

import androidx.lifecycle.*
import com.example.cinemaapp.repostitory.CardViewFilms

class HomeViewModel(
    private val liveDataToObserve: MutableLiveData<ArrayList<CardViewFilms>> =
        MutableLiveData(),
    var cardViewList: ArrayList<CardViewFilms> = ArrayList()
) : ViewModel(), LifecycleObserver {
    init {
        generateDataFromLocalSource()
    }
   fun getData (): LiveData<ArrayList<CardViewFilms>>{
       return liveDataToObserve
   }
    private fun generateDataFromLocalSource() {
        Thread {
            generateDataFromLocalSource()
            val cardViewFilms = CardViewFilms();
            cardViewList.add(cardViewFilms)
            cardViewList.add(cardViewFilms)
            cardViewList.add(cardViewFilms)
            cardViewList.add(cardViewFilms)
            liveDataToObserve.postValue(cardViewList)
        }.start()
    }
}