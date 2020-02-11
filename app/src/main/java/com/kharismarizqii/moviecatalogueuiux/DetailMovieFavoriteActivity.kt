package com.kharismarizqii.moviecatalogueuiux

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kharismarizqii.moviecatalogueuiux.model.FavoriteMovieDB
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_movie_favorite.*

class DetailMovieFavoriteActivity : AppCompatActivity() {

    private var pathPoster = "https://image.tmdb.org/t/p/w185"
    private var pathBackdrop = "https://image.tmdb.org/t/p/w300"
    private var favoriteMovieDB: FavoriteMovieDB? = null

    companion object{
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie_favorite)

        favoriteMovieDB = intent?.getParcelableExtra(EXTRA_MOVIE)

        show(favoriteMovieDB)
    }

    private fun show(movie: FavoriteMovieDB?) {
        tvm_title.text = movie?.title
        tvm_rating.text = movie?.rating.toString()
        tvm_overview.text = movie?.overview
        tvm_release.text = movie?.releaseDate
        Picasso.get().load(pathPoster + movie?.posterPath).into(ivt_poster)
        Picasso.get().load(pathBackdrop + movie?.backdropPath).into(ivt_backdrop)
    }
}
