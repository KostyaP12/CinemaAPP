package com.example.cinemaapp.ui.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.R
import com.example.cinemaapp.repostitory.CardViewFilms

class RecyclerAdapter(var cardViewFilms: CardViewFilms) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView = itemView.findViewById(R.id.item_poster)
        var itemTitle: TextView = itemView.findViewById(R.id.item_title)
        var itemDetail: TextView = itemView.findViewById(R.id.item_detail)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_view, viewGroup, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = cardViewFilms.title.length


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemImage.setImageResource(cardViewFilms.poster)
        viewHolder.itemDetail.text = (cardViewFilms.description)
        viewHolder.itemTitle.text = (cardViewFilms.title)
    }
}

