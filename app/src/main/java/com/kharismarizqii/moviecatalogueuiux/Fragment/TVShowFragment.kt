package com.kharismarizqii.moviecatalogueuiux.Fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kharismarizqii.moviecatalogueuiux.Adapter.TVShowAdapter
import com.kharismarizqii.moviecatalogueuiux.DetailTVShowActivity
import com.kharismarizqii.moviecatalogueuiux.MainActivity
import com.kharismarizqii.moviecatalogueuiux.Model.TVShowDB


import com.kharismarizqii.moviecatalogueuiux.R
import com.kharismarizqii.moviecatalogueuiux.ViewModel.TVShowViewModel

/**
 * A simple [Fragment] subclass.
 */
class TVShowFragment : Fragment() {

    private lateinit var rvTVShow: RecyclerView
    private lateinit var tvShowViewModel: TVShowViewModel
    private lateinit var prBar: ProgressBar
    private lateinit var adapter: TVShowAdapter

    companion object {
        internal const val APP_ID = "f9987e3a05e2951834dc12b48850d089"
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tvshow, container, false)

        rvTVShow = view.findViewById(R.id.rv_tvshow)
        prBar = view.findViewById(R.id.progressBartv)

        showRecyclerCardView()

        rvTVShow.setHasFixedSize(true)
        return view
    }


    private fun showRecyclerCardView() {
        adapter = TVShowAdapter()
        adapter.notifyDataSetChanged()

        rvTVShow.layoutManager = LinearLayoutManager(context)
        rvTVShow.adapter = adapter

        tvShowViewModel = ViewModelProviders.of(this).get(TVShowViewModel::class.java)
        tvShowViewModel.setTVShows()
        showLoading(true)

        tvShowViewModel.getTVShows().observe(this, androidx.lifecycle.Observer { tvShowItems ->
            if (tvShowItems != null) {
                adapter.setData(tvShowItems)
                showLoading(false)
            }
        })

        adapter.setOnItemClickCallback(object : TVShowAdapter.OnItemClickCallback {
            override fun onItemClicked(data: TVShowDB) {
                showSelectedData(data)
            }
        })
    }

    private fun showSelectedData(tvShow: TVShowDB) {
        val tvShowDB = TVShowDB(
            tvShow.title,
            tvShow.rating,
            tvShow.overview,
            tvShow.firstAirDate,
            tvShow.posterPath,
            tvShow.backdropPath
        )

        val moveObjectIntent = Intent(activity, DetailTVShowActivity::class.java)
        moveObjectIntent.putExtra(DetailTVShowActivity.EXTRA_TVSHOW, tvShowDB)
        startActivity(moveObjectIntent)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            prBar.visibility = View.VISIBLE
        } else {
            prBar.visibility = View.GONE
        }
    }

}
