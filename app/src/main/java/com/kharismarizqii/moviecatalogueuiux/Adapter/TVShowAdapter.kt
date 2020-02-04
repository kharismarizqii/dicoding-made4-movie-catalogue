package com.kharismarizqii.moviecatalogueuiux.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kharismarizqii.moviecatalogueuiux.Model.TVShowDB
import com.kharismarizqii.moviecatalogueuiux.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_tv.view.*

class TVShowAdapter: RecyclerView.Adapter<TVShowAdapter.CardViewViewHolder>(){

    private var onItemClickCallback : OnItemClickCallback? = null
    private var pathPoster = "https://image.tmdb.org/t/p/w185"
    private val listTVShow = ArrayList<TVShowDB>()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(items: ArrayList<TVShowDB>){
        listTVShow.clear()
        listTVShow.addAll(items)
        notifyDataSetChanged()
    }

    inner class CardViewViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(tvShow: TVShowDB){
            with(itemView){
                tvt_title.text = tvShow.title
                tvm_rating.text = tvShow.rating.toString()
                tvm_overview.text = tvShow.overview
                Picasso.get().load(pathPoster+tvShow.posterPath).into(ivt_poster)

                btn_detail.setOnClickListener{onItemClickCallback?.onItemClicked(tvShow)}
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_tv, parent, false)

        return CardViewViewHolder(view)

    }

    override fun getItemCount(): Int {
        return listTVShow.size
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(listTVShow[position])
    }

    interface OnItemClickCallback{
        fun onItemClicked(data : TVShowDB)
    }
}