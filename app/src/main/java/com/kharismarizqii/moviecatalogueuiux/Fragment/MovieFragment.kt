package com.kharismarizqii.moviecatalogueuiux.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kharismarizqii.moviecatalogueuiux.Adapter.MovieAdapter
import com.kharismarizqii.moviecatalogueuiux.DetailMovieActivity
import com.kharismarizqii.moviecatalogueuiux.MainActivity
import com.kharismarizqii.moviecatalogueuiux.Model.MovieDB
import com.kharismarizqii.moviecatalogueuiux.R
import com.kharismarizqii.moviecatalogueuiux.ViewModel.MovieViewModel


/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    private lateinit var rvMovie: RecyclerView
    private lateinit var prBar: ProgressBar
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter

    companion object{
        private val TAG = MainActivity::class.java.simpleName
        internal const val APP_ID = "f9987e3a05e2951834dc12b48850d089"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie, container, false)

        rvMovie = view.findViewById(R.id.rv_movie)
        prBar = view.findViewById(R.id.progressBar)

        showRecyclerCardView()

        rvMovie.setHasFixedSize(true)
        return view
    }

    private fun showLoading(state: Boolean){
        if(state){
            prBar.visibility = View.VISIBLE
        } else{
            prBar.visibility = View.GONE
        }
    }

    private fun showRecyclerCardView(){
        adapter = MovieAdapter()
        adapter.notifyDataSetChanged()

        rvMovie.layoutManager = LinearLayoutManager(context)
        rvMovie.adapter = adapter

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        movieViewModel.setMovies()
        showLoading(true)


        movieViewModel.getMovies().observe(this, androidx.lifecycle.Observer { movieItems ->
            if (movieItems != null){
                adapter.setData(movieItems)
                showLoading(false)
            }
        })

        adapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback{
            override fun onItemClicked(data: MovieDB) {
                showSelectedData(data)
            }
        })
    }

    private fun showSelectedData(movie: MovieDB){
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
