package com.example.cinemaapp.ui.favorites

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
import com.example.cinemaapp.ui.recycler_view.FavoritesFragmentAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.fragment_home.loadingLayout

class FavoritesFragment : Fragment() {

    private lateinit var favoritesViewModel: FavoritesViewModel
    private val adapter = FavoritesFragmentAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view_favorites.adapter = adapter
        recycler_view_favorites.layoutManager = LinearLayoutManager(this.context)

        favoritesViewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)
        favoritesViewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
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
                    .make(favoritesView, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.reload)) { favoritesViewModel.getDataFromLocalSource() }
                    .show()
            }
        }
    }
}