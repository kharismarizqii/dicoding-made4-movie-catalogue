package com.kharismarizqii.moviecatalogueuiux

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kharismarizqii.moviecatalogueuiux.model.FavoriteTVShowDB
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_tvshow_favorite.*

class DetailTVShowFavoriteActivity : AppCompatActivity() {

    private var pathPoster = "https://image.tmdb.org/t/p/w185"
    private var pathBackdrop = "https://image.tmdb.org/t/p/w300"
    private var favoriteTVShowDB: FavoriteTVShowDB? = null

    companion object{
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tvshow_favorite)

        favoriteTVShowDB = intent?.getParcelableExtra(EXTRA_MOVIE)

        show(favoriteTVShowDB)
    }

    private fun show(movie: FavoriteTVShowDB?) {
        tvm_title.text = movie?.title
        tvm_rating.text = movie?.rating.toString()
        tvm_overview.text = movie?.overview
        tvm_release.text = movie?.firstAirDate
        Picasso.get().load(pathPoster + movie?.posterPath).into(ivt_poster)
        Picasso.get().load(pathBackdrop + movie?.backdropPath).into(ivt_backdrop)
    }
}
