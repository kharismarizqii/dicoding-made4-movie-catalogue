package com.kharismarizqii.moviecatalogueuiux.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kharismarizqii.moviecatalogueuiux.R
import com.kharismarizqii.moviecatalogueuiux.database.FavoriteMovieHelper
import com.kharismarizqii.moviecatalogueuiux.model.FavoriteMovieDB
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_favorite_movie.view.*

class FavoriteMovieAdapter : RecyclerView.Adapter<FavoriteMovieAdapter.CardViewViewHolder>() {

    private var onItemClickCallback : OnItemClickCallback? = null
    private var pathPoster = "https://image.tmdb.org/t/p/w185"
    private lateinit var favoriteMovieHelper: FavoriteMovieHelper

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: FavoriteMovieDB)
    }

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
                Picasso.get().load(pathPoster + movieDB.posterPath).into(ivm_poster)
                btn_remove.setOnClickListener {
                    favoriteMovieHelper = FavoriteMovieHelper.getInstance(context!!)
                    favoriteMovieHelper.open()
                    favoriteMovieHelper.deleteById(movieDB.id.toString())
                    removeItem(adapterPosition)
                }
                btn_detail.setOnClickListener{onItemClickCallback?.onItemClicked(movieDB)}
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_movie, parent, false)
        return CardViewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.listMovie.size
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }


    fun removeItem(position: Int) {
        this.listMovie.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.listMovie.size)
    }
}
