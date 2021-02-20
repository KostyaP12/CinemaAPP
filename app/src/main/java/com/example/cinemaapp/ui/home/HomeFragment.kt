package com.example.cinemaapp.ui.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemaapp.R
import com.example.cinemaapp.model.*
import com.example.cinemaapp.ui.adapters.OnItemPreviewClickListener
import com.example.cinemaapp.ui.original_source_preview.OriginalSourcePreviewFragment
import com.example.cinemaapp.ui.adapters.VerticalAdapter
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private var snackBar: Snackbar? = null
    private lateinit var movieBundle: Movie
    private lateinit var homeViewModel: HomeViewModel
    private val loadResultsReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            renderData(
                Movie(
                    intent.getStringExtra(TITLE_EXTRA)!!,
                    intent.getStringExtra(OVERVIEW)!!,
                    intent.getStringExtra(POSTER_PATH)!!
                )
            )
        }
    }
    private val adapter = VerticalAdapter(object :
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
        getMovies()
    }

    private fun getMovies() {
        loadingLayout.visibility = View.VISIBLE
        println()
        context?.let {nonNullContext ->
            nonNullContext.startService(Intent(nonNullContext, MovieService::class.java))
        }
    }

    private fun renderData(movie: Movie) {

        loadingLayout.visibility = View.GONE
        adapter.setMovie(movie)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context?.registerReceiver(
            MyBroadcastReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        context?.let {
            LocalBroadcastManager.getInstance(it)
                .registerReceiver(loadResultsReceiver, IntentFilter(DETAILS_INTENT_FILTER))
        }
    }

    fun onNetworkConnectionChanged(isConnected: Boolean) {
        showNetworkMessage(isConnected)
    }

    override fun onDestroy() {
        context?.let {
            LocalBroadcastManager.getInstance(it).unregisterReceiver(loadResultsReceiver)
        }
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        MyBroadcastReceiver.connectivityReceiverListener =
            MyBroadcastReceiver.connectivityReceiverListener
    }

    private fun showNetworkMessage(isConnected: Boolean) {
        if (!isConnected) {
            snackBar = Snackbar.make(
                requireView().findViewById(R.id.homeView),
                "You are offline",
                Snackbar.LENGTH_LONG
            )
            snackBar?.duration = BaseTransientBottomBar.LENGTH_INDEFINITE
            snackBar?.show()
        } else {
            snackBar?.dismiss()
        }
    }
}