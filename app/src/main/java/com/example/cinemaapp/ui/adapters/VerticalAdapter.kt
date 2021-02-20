package com.example.cinemaapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.R
import com.example.cinemaapp.model.Movie
import com.example.cinemaapp.model.OriginalSourcePreview
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_view.view.*

class VerticalAdapter(private var onItemPreviewClickListener: OnItemPreviewClickListener?) :
    RecyclerView.Adapter<VerticalAdapter.ViewHolder>() {
    private var movie: List<Movie> = listOf()

    fun setMovie(movieL: Movie) {
        movie = listOf(movieL)
        notifyDataSetChanged()
    }

    fun removeClickListener() {
        onItemPreviewClickListener = null
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {
            itemView.apply {
                item_title.text = movie.original_title
                item_detail.text = movie.overview  }
            Picasso.get().load(movie.poster_path).placeholder(R.drawable.heroes).into(itemView.findViewById<ImageView>(R.id.item_poster))
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return movie.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movie[position])
    }
}