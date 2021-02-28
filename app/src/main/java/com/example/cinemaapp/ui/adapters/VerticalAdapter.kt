package com.example.cinemaapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.R
import com.example.cinemaapp.model.API_IMAGE_URL
import com.example.cinemaapp.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_view.view.*

class VerticalAdapter() :
    RecyclerView.Adapter<VerticalAdapter.ViewHolder>(), View.OnClickListener{

    private var moviesList = arrayListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }

    fun addItems(moviesList: ArrayList<Movie>) {
        this.moviesList.addAll(moviesList)
    }

    fun clearItems() = this.moviesList.clear()

    override fun getItemCount() = moviesList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {
            itemView.apply {
                val poster: ImageView = findViewById(R.id.item_poster)
                Picasso.get().load("${API_IMAGE_URL}${movie.poster_path}")
                    .placeholder(R.drawable.heroes).into(item_poster)
                item_title.text = movie.title
                item_detail.text = movie.overview
            }
        }
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}



