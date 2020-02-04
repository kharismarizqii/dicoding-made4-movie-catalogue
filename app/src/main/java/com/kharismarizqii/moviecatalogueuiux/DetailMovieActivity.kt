package com.kharismarizqii.moviecatalogueuiux

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kharismarizqii.moviecatalogueuiux.Model.MovieDB
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.activity_detail_movie.tvm_title



class DetailMovieActivity : AppCompatActivity() {

    private var pathPoster = "https://image.tmdb.org/t/p/w185"
    private var pathBackdrop = "https://image.tmdb.org/t/p/w300"

    companion object{
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val movie = intent?.getParcelableExtra<MovieDB>(EXTRA_MOVIE)
        show(movie!!)
    }

    fun show(movie: MovieDB){
        tvm_title.text = movie.title
        tvm_rating.text = movie.rating.toString()
        tvm_overview.text = movie.overview
        tvm_release.text = movie.releaseDate
        Picasso.get().load(pathPoster+movie.posterPath).into(ivt_poster)
        Picasso.get().load(pathBackdrop+movie.backdropPath).into(ivt_backdrop)

    }
}
