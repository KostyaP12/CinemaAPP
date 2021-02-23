package com.example.cinemaapp.ui.home

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemaapp.R
import com.example.cinemaapp.model.*
import com.example.cinemaapp.ui.adapters.OnItemPreviewClickListener
import com.example.cinemaapp.ui.adapters.VerticalAdapter
import com.example.cinemaapp.ui.original_source_preview.OriginalSourcePreviewFragment
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private var adapter = VerticalAdapter()
    /*private val adapter = VerticalAdapter(object :
        OnItemPreviewClickListener {
        override fun onItemPreviewClickListener(originalSourcePreview: OriginalSourcePreview) {
            val manager = activity?.supportFragmentManager
            if (manager != null) {
                val bundle = Bundle().apply {
                    putParcelable(OriginalSourcePreviewFragment.BUNDLE_EXTRA, originalSourcePreview)
                }
                manager.beginTransaction()
                    .add(
                        R.id.homeView,
                        OriginalSourcePreviewFragment.newInstance(bundle)
                    )
                    .addToBackStack("")
                    .commit()
            }
        }
    })*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view_home.adapter = adapter
        recycler_view_home.layoutManager = LinearLayoutManager(this.context)

        getNowPlayingMovies()
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