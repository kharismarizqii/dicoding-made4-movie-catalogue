package com.kharismarizqii.moviecatalogueuiux.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kharismarizqii.moviecatalogueuiux.R
import com.kharismarizqii.moviecatalogueuiux.adapter.FavoriteTVAdapter
import com.kharismarizqii.moviecatalogueuiux.database.FavoriteTVHelper
import com.kharismarizqii.moviecatalogueuiux.helper.MappingHelper
import com.kharismarizqii.moviecatalogueuiux.model.FavoriteTVShowDB
import kotlinx.android.synthetic.main.fragment_favorite_tvshow.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTVShowFragment : Fragment() {

    private lateinit var adapter: FavoriteTVAdapter
    private lateinit var favoriteTVHelper: FavoriteTVHelper

    companion object {
        private const val EXTRA_STATE_TV = "EXTRA_STATETV"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tvshow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_tvshow.layoutManager = LinearLayoutManager(activity)
        rv_tvshow.setHasFixedSize(true)
        adapter = FavoriteTVAdapter()
        rv_tvshow.adapter = adapter

        favoriteTVHelper = FavoriteTVHelper.getInstance(context!!)
        favoriteTVHelper.open()

        if (savedInstanceState == null) {
            loadTVAsync()
        } else {
            val list = savedInstanceState.getParcelableArrayList<FavoriteTVShowDB>(EXTRA_STATE_TV)
            if (list != null) {
                adapter.listTVShow = list
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(EXTRA_STATE_TV, adapter.listTVShow)
    }

    private fun loadTVAsync() {
        GlobalScope.launch(Dispatchers.Main) {
            progressBartv.visibility = View.VISIBLE
            val deferredTVShows = async(Dispatchers.IO) {
                val cursor = favoriteTVHelper.queryAll()
                MappingHelper.mapCursorToArrayListTV(cursor)
            }
            progressBartv.visibility = View.INVISIBLE
            val tvShows = deferredTVShows.await()
            if (tvShows.size > 0) {
                adapter.listTVShow = tvShows
            } else {
                adapter.listTVShow = ArrayList()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        favoriteTVHelper.close()
    }


}
