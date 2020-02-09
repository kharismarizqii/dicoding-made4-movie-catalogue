package com.kharismarizqii.moviecatalogueuiux.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kharismarizqii.moviecatalogueuiux.model.FavoriteMovieDB
import com.kharismarizqii.moviecatalogueuiux.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_favorite_movie.view.*

class FavoriteMovieAdapter : RecyclerView.Adapter<FavoriteMovieAdapter.CardViewViewHolder>() {

    private var pathPoster = "https://image.tmdb.org/t/p/w185"

    var listMovie = ArrayList<FavoriteMovieDB>()
        set(listMovie) {
            if (listMovie.size > 0) {
                this.listMovie.clear()
            }
            this.listMovie.addAll(listMovie)
            Log.d("CEK ISI LIST", "listMovie: $listMovie")
            notifyDataSetChanged()
        }

    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieDB: FavoriteMovieDB) {
            with(itemView) {
                Log.d("CEK ISI LIST", "movieDB: $movieDB")

                tvm_title.text = movieDB.title
                Log.d("isi objek", "CardViewViewHolder ${movieDB.title}")
                tvm_overview.text = movieDB.overview
                tvm_rating.text = movieDB.rating.toString()
                Picasso.get().load(pathPoster+movieDB.posterPath).into(ivm_poster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return CardViewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.listMovie.size
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    fun addItem(movieDB: FavoriteMovieDB) {
        this.listMovie.add(movieDB)
        notifyItemInserted(this.listMovie.size - 1)
    }

    fun updateItem(position: Int, movieDB: FavoriteMovieDB) {
        this.listMovie[position] = movieDB
        notifyItemChanged(position, movieDB)
    }

    fun removeItem(position: Int) {
        this.listMovie.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.listMovie.size)
    }
}
