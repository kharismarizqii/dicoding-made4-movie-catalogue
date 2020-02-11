package com.kharismarizqii.moviecatalogueuiux.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kharismarizqii.moviecatalogueuiux.DetailMovieFavoriteActivity
import com.kharismarizqii.moviecatalogueuiux.R
import com.kharismarizqii.moviecatalogueuiux.adapter.FavoriteMovieAdapter
import com.kharismarizqii.moviecatalogueuiux.database.FavoriteMovieHelper
import com.kharismarizqii.moviecatalogueuiux.helper.MappingHelper
import com.kharismarizqii.moviecatalogueuiux.model.FavoriteMovieDB
import kotlinx.android.synthetic.main.fragment_favorite_movie.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class FavoriteMovieFragment : Fragment() {

    private lateinit var adapter: FavoriteMovieAdapter
    private lateinit var favoriteMovieHelper: FavoriteMovieHelper

    companion object {
        private const val EXTRA_STATE = "EXTRA_STATE"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_movie.layoutManager = LinearLayoutManager(activity)
        rv_movie.setHasFixedSize(true)
        adapter = FavoriteMovieAdapter()
        rv_movie.adapter = adapter

        favoriteMovieHelper = FavoriteMovieHelper.getInstance(context!!)
        favoriteMovieHelper.open()

        adapter.setOnItemClickCallback(object : FavoriteMovieAdapter.OnItemClickCallback{
            override fun onItemClicked(data: FavoriteMovieDB) {
                showSelectedData(data)
            }

        })

        if (savedInstanceState == null){
            loadMoviesAsync()
        } else{
            val list = savedInstanceState.getParcelableArrayList<FavoriteMovieDB>(EXTRA_STATE)
            if(list != null){
                adapter.listMovie = list
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(EXTRA_STATE, adapter.listMovie)
    }


    private fun loadMoviesAsync() {
        GlobalScope.launch(Dispatchers.Main) {
            progressBar.visibility = View.VISIBLE
            val deferredMovies = async(Dispatchers.IO) {
                val cursor = favoriteMovieHelper.queryAll()
                Log.d("isi cursor", "cursor: ${cursor.count}")
                MappingHelper.mapCursorToArrayList(cursor)
            }
            progressBar.visibility = View.INVISIBLE
            val movies = deferredMovies.await()
            if (movies.size > 0) {
                adapter.listMovie = movies
            } else {
                adapter.listMovie = ArrayList()
            }
        }
    }

    private fun showSelectedData(movie: FavoriteMovieDB) {

        val moveObjectIntent = Intent(activity, DetailMovieFavoriteActivity::class.java)
        moveObjectIntent.putExtra(DetailMovieFavoriteActivity.EXTRA_MOVIE, movie)
        startActivity(moveObjectIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        favoriteMovieHelper.close()
    }
}
