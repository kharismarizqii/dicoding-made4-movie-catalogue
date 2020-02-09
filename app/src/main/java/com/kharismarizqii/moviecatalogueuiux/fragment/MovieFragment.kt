package com.kharismarizqii.moviecatalogueuiux.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kharismarizqii.moviecatalogueuiux.adapter.MovieAdapter
import com.kharismarizqii.moviecatalogueuiux.BuildConfig
import com.kharismarizqii.moviecatalogueuiux.DetailMovieActivity
import com.kharismarizqii.moviecatalogueuiux.MainActivity
import com.kharismarizqii.moviecatalogueuiux.model.MovieDB
import com.kharismarizqii.moviecatalogueuiux.R
import com.kharismarizqii.moviecatalogueuiux.viewmodel.MovieViewModel


/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    private lateinit var rvMovie: RecyclerView
    private lateinit var prBar: ProgressBar
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter

    companion object {
        private val TAG = MainActivity::class.java.simpleName
        internal const val APP_ID = BuildConfig.TMDB_API_KEY
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvMovie = view.findViewById(R.id.rv_movie)
        prBar = view.findViewById(R.id.progressBar)

        showRecyclerCardView()

        rvMovie.setHasFixedSize(true)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            prBar.visibility = View.VISIBLE
        } else {
            prBar.visibility = View.GONE
        }
    }

    private fun showRecyclerCardView() {
        adapter = MovieAdapter()
        adapter.notifyDataSetChanged()

        rvMovie.layoutManager = LinearLayoutManager(context)
        rvMovie.adapter = adapter

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.setMovies()
        showLoading(true)


        movieViewModel.getMovies().observe(this, androidx.lifecycle.Observer { movieItems ->
            if (movieItems != null) {
                adapter.setData(movieItems)
                showLoading(false)
            }
        })

        adapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
            override fun onItemClicked(data: MovieDB) {
                showSelectedData(data)
            }
        })
    }

    private fun showSelectedData(movie: MovieDB) {
        val movieDB = MovieDB(
            movie.title,
            movie.rating,
            movie.overview,
            movie.releaseDate,
            movie.posterPath,
            movie.backdropPath
        )

        val moveObjectIntent = Intent(activity, DetailMovieActivity::class.java)
        moveObjectIntent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movieDB)
        startActivity(moveObjectIntent)
    }

}
