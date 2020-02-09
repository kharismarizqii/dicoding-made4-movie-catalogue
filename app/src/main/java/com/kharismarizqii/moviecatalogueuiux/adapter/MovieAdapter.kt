package com.kharismarizqii.moviecatalogueuiux.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kharismarizqii.moviecatalogueuiux.model.MovieDB
import com.kharismarizqii.moviecatalogueuiux.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*


class MovieAdapter: RecyclerView.Adapter<MovieAdapter.CardViewViewHolder>(){

    private var onItemClickCallback : OnItemClickCallback? = null
    private var pathPoster = "https://image.tmdb.org/t/p/w185"
    private val listMovie = ArrayList<MovieDB>()

    fun setData(items: ArrayList<MovieDB>){
        listMovie.clear()
        listMovie.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardViewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return CardViewViewHolder(view)
    }

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(movieDB: MovieDB){
            with(itemView){
                tvm_title.text = movieDB.title
                tvm_rating.text = movieDB.rating.toString()
                Picasso.get().load(pathPoster+movieDB.posterPath).into(ivm_poster)
                tvm_overview.text = movieDB.overview
                btn_detail.setOnClickListener{onItemClickCallback?.onItemClicked(movieDB)}
            }
        }
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: MovieDB)
    }

}