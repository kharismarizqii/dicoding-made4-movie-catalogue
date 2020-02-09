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
import com.kharismarizqii.moviecatalogueuiux.BuildConfig
import com.kharismarizqii.moviecatalogueuiux.DetailTVShowActivity
import com.kharismarizqii.moviecatalogueuiux.MainActivity
import com.kharismarizqii.moviecatalogueuiux.R
import com.kharismarizqii.moviecatalogueuiux.adapter.TVShowAdapter
import com.kharismarizqii.moviecatalogueuiux.model.TVShowDB
import com.kharismarizqii.moviecatalogueuiux.viewmodel.TVShowViewModel

/**
 * A simple [Fragment] subclass.
 */
class TVShowFragment : Fragment() {

    private lateinit var rvTVShow: RecyclerView
    private lateinit var tvShowViewModel: TVShowViewModel
    private lateinit var prBar: ProgressBar
    private lateinit var adapter: TVShowAdapter

    companion object {
        internal const val APP_ID = BuildConfig.TMDB_API_KEY
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvTVShow = view.findViewById(R.id.rv_tvshow)
        prBar = view.findViewById(R.id.progressBartv)

        showRecyclerCardView()

        rvTVShow.setHasFixedSize(true)
    }


    private fun showRecyclerCardView() {
        adapter = TVShowAdapter()
        adapter.notifyDataSetChanged()

        rvTVShow.layoutManager = LinearLayoutManager(context)
        rvTVShow.adapter = adapter

        tvShowViewModel = ViewModelProvider(this).get(TVShowViewModel::class.java)
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
        val moveObjectIntent = Intent(activity, DetailTVShowActivity::class.java)
        moveObjectIntent.putExtra(DetailTVShowActivity.EXTRA_TVSHOW, tvShow)
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
