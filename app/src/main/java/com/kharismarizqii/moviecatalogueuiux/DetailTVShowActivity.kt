package com.kharismarizqii.moviecatalogueuiux

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kharismarizqii.moviecatalogueuiux.database.DatabaseContract
import com.kharismarizqii.moviecatalogueuiux.database.FavoriteTVHelper
import com.kharismarizqii.moviecatalogueuiux.model.FavoriteTVShowDB
import com.kharismarizqii.moviecatalogueuiux.model.TVShowDB
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_tvshow.*

class DetailTVShowActivity : AppCompatActivity() {

    private var pathPoster = "https://image.tmdb.org/t/p/w185"
    private var pathBackdrop = "https://image.tmdb.org/t/p/w300"
    private var tvShow: TVShowDB? = null
    private var tvShowFav: FavoriteTVShowDB? = null
    private var position: Int = 0
    private var isFav = false
    private lateinit var favoriteTVHelper: FavoriteTVHelper

    companion object {
        const val EXTRA_TVSHOW = "extra_tvshow"
        const val REQUEST_ADD = 100
        const val RESULT_ADD = 101
        const val REQUEST_UPDATE = 200
        const val RESULT_UPDATE = 201
        const val RESULT_DELETE = 301
        const val EXTRA_TV = "extra_tv"
        const val EXTRA_POSITION_TV = "extra_position_tv"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tvshow)

        favoriteTVHelper = FavoriteTVHelper.getInstance(applicationContext)

        tvShow = intent.getParcelableExtra(EXTRA_TVSHOW)

        tvShowFav = intent.getParcelableExtra(EXTRA_TV)
        if (tvShowFav != null) {
            position = intent.getIntExtra(EXTRA_POSITION_TV, 0)
            isFav = true
        } else {
            tvShowFav = FavoriteTVShowDB()
        }

        if (isFav) {

        } else {

        }

        setup(tvShow!!)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.favorite_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> {
                addToFavorite(tvShow!!)
                return true
            }
            android.R.id.home ->{
                finish()
                return true
            }
            else -> return true
        }
    }

    private fun addToFavorite(tvShow: TVShowDB) {
        tvShowFav?.id = tvShow.id
        tvShowFav?.title = tvShow.title
        tvShowFav?.overview = tvShow.overview
        tvShowFav?.rating = tvShow.rating
        tvShowFav?.firstAirDate = tvShow.firstAirDate
        tvShowFav?.posterPath = tvShow.posterPath
        tvShowFav?.backdropPath = tvShow.backdropPath

        val intent = Intent()
        intent.putExtra(EXTRA_TV, tvShowFav)
        intent.putExtra(EXTRA_POSITION_TV, position)

        val values = ContentValues()
        values.put(DatabaseContract.FavoriteTVColumns.ID, tvShow.id)
        values.put(DatabaseContract.FavoriteTVColumns.TITLE, tvShow.title)
        values.put(DatabaseContract.FavoriteTVColumns.RATING, tvShow.rating)
        values.put(DatabaseContract.FavoriteTVColumns.OVERVIEW, tvShow.overview)
        values.put(DatabaseContract.FavoriteTVColumns.RELEASE, tvShow.firstAirDate)
        values.put(DatabaseContract.FavoriteTVColumns.POSTER_PATH, tvShow.posterPath)
        values.put(DatabaseContract.FavoriteTVColumns.BACKDROP_PATH, tvShow.backdropPath)

        favoriteTVHelper.open()
        val result = favoriteTVHelper.insert(values)

        if (result > 0) {
            tvShowFav?.id = result.toInt()
            setResult(RESULT_ADD, intent)
            Toast.makeText(this@DetailTVShowActivity,R.string.toast_success , Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this@DetailTVShowActivity, R.string.toast_failedtv, Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun setup(tvShow: TVShowDB) {
        tvt_title.text = tvShow.title
        tvm_overview.text = tvShow.overview
        tvm_rating.text = tvShow.rating.toString()
        tvt_release.text = tvShow.firstAirDate
        Picasso.get().load(pathPoster + tvShow.posterPath).into(ivt_poster)
        Picasso.get().load(pathBackdrop + tvShow.backdropPath).into(ivt_backdrop)
    }
}
