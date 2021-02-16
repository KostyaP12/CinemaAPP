package com.example.cinemaapp.ui.original_source_preview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cinemaapp.R
import com.example.cinemaapp.model.OriginalSourcePreview
import kotlinx.android.synthetic.main.fragment_original_source_preview.*

class OriginalSourcePreviewFragment : Fragment() {
    private lateinit var originalSourcePreviewViewModel: OriginalSourcePreviewViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_original_source_preview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val originalSourcePreviewFragment = arguments?.getParcelable<OriginalSourcePreview>(BUNDLE_EXTRA)
        originalSourcePreviewFragment?.let { originalSourcePreview ->
            val fullInfo =originalSourcePreview.cardViewFilms
            full_text.text = fullInfo.description
            full_poster.setImageResource(fullInfo.poster)
        }
    }

    companion object {
        const val BUNDLE_EXTRA = "original_source_preview"

        fun newInstance(bundle: Bundle): OriginalSourcePreviewFragment {
            val fragment = OriginalSourcePreviewFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}