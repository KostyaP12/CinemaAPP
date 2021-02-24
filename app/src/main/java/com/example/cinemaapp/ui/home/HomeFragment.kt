package com.example.cinemaapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemaapp.R
import com.example.cinemaapp.ui.adapters.VerticalAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private var adapter = VerticalAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = HomeViewModel()
        getNowPlayingMovies()
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view_home.adapter = adapter
        recycler_view_home.layoutManager = LinearLayoutManager(this.context)
    }

    private fun getNowPlayingMovies() {
        homeViewModel.apply {
            getObserverNowPlayingMovies().observe(viewLifecycleOwner) {
                adapter.clearItems()
                adapter.addItems(it.results)
                adapter.notifyDataSetChanged()
            }
            lookNowMovie()
        }
    }
}