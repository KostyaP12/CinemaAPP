package com.example.cinemaapp.ui.top_rating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.R
import com.example.cinemaapp.ui.recycler_view.HomeFragmentAdapter


class TopRatingFragment : Fragment() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<HomeFragmentAdapter.ViewHolder>? = null

    private lateinit var topRatingViewModel: TopRatingViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        topRatingViewModel =
                ViewModelProvider(this).get(TopRatingViewModel::class.java)

        val root =  inflater.inflate(R.layout.fragment_top_rating, container, false)

        return root
    }


}