package com.kharismarizqii.moviecatalogueuiux.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kharismarizqii.moviecatalogueuiux.R
import com.kharismarizqii.moviecatalogueuiux.model.FavoriteTVShowDB
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_favorite_tvshow.view.*

class FavoriteTVAdapter : RecyclerView.Adapter<FavoriteTVAdapter.CardViewViewHolder>() {

    private var pathPoster = "https://image.tmdb.org/t/p/w185"

    var listTVShow = ArrayList<FavoriteTVShowDB>()
        set(listTVShow) {
            if (listTVShow.size > 0) {
                this.listTVShow.clear()
            }
            this.listTVShow.addAll(listTVShow)
            notifyDataSetChanged()
        }

    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShowDB: FavoriteTVShowDB) {
            with(itemView) {
                tvm_title.text = tvShowDB.title
                tvm_overview.text = tvShowDB.overview
                tvm_rating.text = tvShowDB.rating.toString()
                Picasso.get().load(pathPoster + tvShowDB.posterPath).into(ivm_poster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite_tvshow, parent, false)
        return CardViewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.listTVShow.size
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(listTVShow[position])
    }

    fun addItem(tvShowDB: FavoriteTVShowDB) {
        this.listTVShow.add(tvShowDB)
        notifyItemInserted(this.listTVShow.size - 1)
    }

    fun updateItem(position: Int, tvShowDB: FavoriteTVShowDB) {
        this.listTVShow[position] = tvShowDB
        notifyItemChanged(position, tvShowDB)
    }

    fun removeItem(position: Int) {
        this.listTVShow.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.listTVShow.size)
    }
}