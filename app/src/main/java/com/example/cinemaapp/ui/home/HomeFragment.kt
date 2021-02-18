package com.example.cinemaapp.ui.home

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
import com.example.cinemaapp.model.OnItemPreviewClickListener
import com.example.cinemaapp.model.OriginalSourcePreview
import com.example.cinemaapp.ui.original_source_preview.OriginalSourcePreviewFragment
import com.example.cinemaapp.ui.recycler_view.VerticalAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private val adapter = VerticalAdapter(object : OnItemPreviewClickListener {
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
    })

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

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
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
                    .make(homeView, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.reload)) { homeViewModel.getDataFromLocalSource() }
                    .show()
            }
        }
    }
}