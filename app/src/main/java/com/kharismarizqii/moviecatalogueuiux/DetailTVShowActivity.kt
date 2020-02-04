package com.kharismarizqii.moviecatalogueuiux

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kharismarizqii.moviecatalogueuiux.Model.TVShowDB
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_tvshow.*

class DetailTVShowActivity : AppCompatActivity() {

    private var pathPoster = "https://image.tmdb.org/t/p/w185"
    private var pathBackdrop = "https://image.tmdb.org/t/p/w300"

    companion object{
        const val EXTRA_TVSHOW = "extra_tvshow"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tvshow)
        val tvShow = intent.getParcelableExtra<TVShowDB>(EXTRA_TVSHOW)
        setup(tvShow!!)
    }

    fun setup(tvShow : TVShowDB){
        tvt_title.text = tvShow.title
        tvm_overview.text = tvShow.overview
        tvm_rating.text = tvShow.rating.toString()
        tvt_release.text = tvShow.firstAirDate
        Picasso.get().load(pathPoster+tvShow.posterPath).into(ivt_poster)
        Picasso.get().load(pathBackdrop+tvShow.backdropPath).into(ivt_backdrop)
    }
}
