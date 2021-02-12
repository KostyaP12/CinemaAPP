package com.example.cinemaapp.ui.top_rating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemaapp.R
import com.example.cinemaapp.model.AppState
import com.example.cinemaapp.ui.home.HomeViewModel
import com.example.cinemaapp.ui.recycler_view.HomeFragmentAdapter
import com.example.cinemaapp.ui.recycler_view.TopRatingFragmentAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.loadingLayout
import kotlinx.android.synthetic.main.fragment_top_rating.*


class TopRatingFragment  : Fragment() {
    private lateinit var topRatingViewModel: TopRatingViewModel
    private val adapter = TopRatingFragmentAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_rating, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view_top_rating.adapter = adapter
        recycler_view_top_rating.layoutManager = LinearLayoutManager(this.context)

        topRatingViewModel = ViewModelProvider(this).get(TopRatingViewModel::class.java)
        topRatingViewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
    }


    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                loadingLayout.visibility = View.GONE
                adapter.setOriginalSourcePreview(appState.previewData)
            }
            is AppState.Loading -> {
                loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                loadingLayout.visibility = View.GONE
                Snackbar
                    .make(topRatingView, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.reload)) { topRatingViewModel.getDataFromLocalSource() }
                    .show()
            }
        }
    }


}