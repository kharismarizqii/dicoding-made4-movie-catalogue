package com.kharismarizqii.moviecatalogueuiux.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.kharismarizqii.moviecatalogueuiux.Fragment.TVShowFragment
import com.kharismarizqii.moviecatalogueuiux.Model.TVShowDB
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class TVShowViewModel(application: Application) : AndroidViewModel(application){

    private lateinit var listTVShowDB : ArrayList<TVShowDB>

    companion object{
        private val TAG = TVShowViewModel::class.java.simpleName
    }

    val listTVShows = MutableLiveData<ArrayList<TVShowDB>>()

    fun getLang(): String{
        var currentLang = Locale.getDefault().toString()
        Log.d(TAG, "getLang: $currentLang")
        if(currentLang.equals("in_ID")){
            currentLang = "id-ID"
        }
        return currentLang
    }

    internal fun setTVShows(){
        Log.d("test masuk", "setTVShows: ")
        listTVShowDB = ArrayList<TVShowDB>()
        val lang = getLang()
        val url = "https://api.themoviedb.org/3/tv/popular?language=$lang&api_key=${TVShowFragment.APP_ID}"
        Log.d("URL", "setTVShows: $url")
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {response ->
            try {
                val jsonArray: JSONArray = response.getJSONArray("results")
                for (position in 0..jsonArray.length()-1){
                    var data: JSONObject = jsonArray.getJSONObject(position)
                    val tvShowDB = TVShowDB(
                        data.getString("name"),
                        data.getDouble("vote_average"),
                        data.getString("overview"),
                        data.getString("first_air_date"),
                        data.getString("poster_path"),
                        data.getString("backdrop_path")
                    )
                    listTVShowDB.add(tvShowDB)
                }
                listTVShows.postValue(listTVShowDB)
            } catch (e: JSONException){

            }
        }, Response.ErrorListener {response: VolleyError->
            Log.d("errorslider", " : $response")
        })
        Volley.newRequestQueue(getApplication()).add(jsonObjectRequest)
    }

    internal fun getTVShows(): LiveData<ArrayList<TVShowDB>>{
        return listTVShows
    }
}