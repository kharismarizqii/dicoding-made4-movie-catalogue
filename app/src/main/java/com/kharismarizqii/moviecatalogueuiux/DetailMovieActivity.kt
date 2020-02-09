package com.kharismarizqii.moviecatalogueuiux

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.kharismarizqii.moviecatalogueuiux.database.DatabaseContract
import com.kharismarizqii.moviecatalogueuiux.database.FavoriteMovieHelper
import com.kharismarizqii.moviecatalogueuiux.model.FavoriteMovieDB
import com.kharismarizqii.moviecatalogueuiux.model.MovieDB
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.activity_detail_movie.tvm_title


class DetailMovieActivity : AppCompatActivity() {

    private var pathPoster = "https://image.tmdb.org/t/p/w185"
    private var pathBackdrop = "https://image.tmdb.org/t/p/w300"
    private var movie: MovieDB? = null
    private var movieFav: FavoriteMovieDB? = null
    private var position: Int = 0
    private var isFav = false
    private lateinit var favoriteMovieHelper: FavoriteMovieHelper

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val REQUEST_ADD = 110
        const val RESULT_ADD = 111
        const val REQUEST_UPDATE = 210
        const val RESULT_UPDATE = 211
        const val RESULT_DELETE = 311
        const val EXTRA_MOVIE_FAV = "extra_movie_fav"
        const val EXTRA_POSITION_MOVIE = "extra_position_movie"
    }

    override fun onResume() {
        super.onResume()
        checkFavorite()
    }

    private fun checkFavorite() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        favoriteMovieHelper = FavoriteMovieHelper.getInstance(applicationContext)

        movie = intent?.getParcelableExtra(EXTRA_MOVIE)

        movieFav = intent?.getParcelableExtra(EXTRA_MOVIE_FAV)
        if (movieFav != null) {
            position = intent.getIntExtra(EXTRA_POSITION_MOVIE, 0)
            isFav = true
        } else {
            movieFav = FavoriteMovieDB()
        }


        show(movie!!)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.favorite_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> {
                addToFavorite(movie!!)
                return true
            }
            else -> return true
        }
    }

    private fun addToFavorite(movie: MovieDB) {
        movieFav?.id = movie.id
        movieFav?.title = movie.title
        movieFav?.overview = movie.overview
        movieFav?.rating = movie.rating
        movieFav?.releaseDate = movie.releaseDate
        movieFav?.posterPath = movie.posterPath
        movieFav?.backdropPath = movie.backdropPath

        val intent = Intent()
        intent.putExtra(EXTRA_MOVIE_FAV, movieFav)
        intent.putExtra(EXTRA_POSITION_MOVIE, position)

        val values = ContentValues()
        values.put(DatabaseContract.FavoriteMovieColumns.ID, movie.id)
        values.put(DatabaseContract.FavoriteMovieColumns.TITLE, movie.title)
        values.put(DatabaseContract.FavoriteMovieColumns.OVERVIEW, movie.overview)
        values.put(DatabaseContract.FavoriteMovieColumns.RATING, movie.rating)
        values.put(DatabaseContract.FavoriteMovieColumns.RELEASE, movie.releaseDate)
        values.put(DatabaseContract.FavoriteMovieColumns.POSTER_PATH, movie.posterPath)
        values.put(DatabaseContract.FavoriteMovieColumns.BACKDROP_PATH, movie.backdropPath)

        favoriteMovieHelper.open()
        val result = favoriteMovieHelper.insert(values)

        if (result > 0) {
            movieFav?.id = result.toInt()
            setResult(RESULT_ADD, intent)
            Toast.makeText(this@DetailMovieActivity,R.string.toast_success , Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this@DetailMovieActivity, R.string.toast_failedmovie, Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun show(movie: MovieDB) {
        tvm_title.text = movie.title
        tvm_rating.text = movie.rating.toString()
        tvm_overview.text = movie.overview
        tvm_release.text = movie.releaseDate
        Picasso.get().load(pathPoster + movie.posterPath).into(ivt_poster)
        Picasso.get().load(pathBackdrop + movie.backdropPath).into(ivt_backdrop)
    }
}
