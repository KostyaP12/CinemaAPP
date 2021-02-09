package com.example.cinemaapp.ui.original_source_preview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cinemaapp.R

class OriginalSourcePreviewFragment : Fragment() {
    private lateinit var originalSourcePreviewViewModel: OriginalSourcePreviewViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_original_source_preview, container, false)
    }
}

