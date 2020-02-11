package com.kharismarizqii.moviecatalogueuiux.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kharismarizqii.moviecatalogueuiux.R
import com.kharismarizqii.moviecatalogueuiux.database.FavoriteTVHelper
import com.kharismarizqii.moviecatalogueuiux.model.FavoriteTVShowDB
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_favorite_tvshow.view.*

class FavoriteTVAdapter : RecyclerView.Adapter<FavoriteTVAdapter.CardViewViewHolder>() {

    private var onItemClickCallback : OnItemClickCallback? = null
    private var pathPoster = "https://image.tmdb.org/t/p/w185"
    private lateinit var favoriteTVHelper: FavoriteTVHelper

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: FavoriteTVShowDB)
    }

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
                btn_remove.setOnClickListener {
                    favoriteTVHelper = FavoriteTVHelper.getInstance(context!!)
                    favoriteTVHelper.open()
                    favoriteTVHelper.deleteById(tvShowDB.id.toString())
                    removeItem(adapterPosition)
                }
                btn_detail.setOnClickListener{onItemClickCallback?.onItemClicked(tvShowDB)}
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


    fun removeItem(position: Int) {
        this.listTVShow.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.listTVShow.size)
    }


}