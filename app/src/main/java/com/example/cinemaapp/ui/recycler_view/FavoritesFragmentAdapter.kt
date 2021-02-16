package com.example.cinemaapp.ui.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.R
import com.example.cinemaapp.model.OriginalSourcePreview
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_view.view.*

class   FavoritesFragmentAdapter() : RecyclerView.Adapter<FavoritesFragmentAdapter.ViewHolder>() {
    private var originalSourcePreview : List<OriginalSourcePreview> = listOf()

    fun setOriginalSourcePreview(data: List<OriginalSourcePreview>) {
        originalSourcePreview = data
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(originalSourcePreview: OriginalSourcePreview){
            itemView.item_title.text = originalSourcePreview.cardViewFilms.title
            itemView.item_detail.text = originalSourcePreview.cardViewFilms.description
            //itemView.item_poster.setImageResource(originalSourcePreview.cardViewFilms.poster)
            Picasso.with(itemView.context).load(originalSourcePreview.cardViewFilms.poster).into(itemView.item_poster)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_view, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(originalSourcePreview[position])

    }

    override fun getItemCount() : Int{
        return originalSourcePreview.size
    }
}