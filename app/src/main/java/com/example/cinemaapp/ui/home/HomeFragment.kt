package com.example.cinemaapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemaapp.R
import com.example.cinemaapp.repostitory.CardViewFilms
import com.example.cinemaapp.ui.recycler_view.RecyclerAdapter
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {


    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)


        val observer = Observer<ArrayList<CardViewFilms>>{renderData(it)}
        homeViewModel.getData().observe(viewLifecycleOwner, observer)


        lifecycle.addObserver(homeViewModel)
        return root
    }

    private fun renderData(data: ArrayList<CardViewFilms>) {
        recycler_view.layoutManager = LinearLayoutManager(this.context)
        recycler_view.adapter = RecyclerAdapter(data)
    }


}
