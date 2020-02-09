package com.kharismarizqii.moviecatalogueuiux.helper

import android.database.Cursor
import android.util.Log
import com.kharismarizqii.moviecatalogueuiux.database.DatabaseContract
import com.kharismarizqii.moviecatalogueuiux.model.FavoriteMovieDB
import com.kharismarizqii.moviecatalogueuiux.model.FavoriteTVShowDB

object MappingHelper {
    fun mapCursorToArrayList(moviesCursor: Cursor): ArrayList<FavoriteMovieDB> {
        val movieList = ArrayList<FavoriteMovieDB>()
        while (moviesCursor.moveToNext()){
            val id =
                moviesCursor.getInt(moviesCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.ID))
            val title =
                moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.TITLE))
            val rating =
                moviesCursor.getDouble(moviesCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.RATING))
            val overview =
                moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.OVERVIEW))
            val release =
                moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.RELEASE))
            val posterPath =
                moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.POSTER_PATH))
            val backdropPath =
                moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.BACKDROP_PATH))
            movieList.add(
                FavoriteMovieDB(
                    id,
                    title,
                    rating,
                    overview,
                    release,
                    posterPath,
                    backdropPath
                )
            )
        }
        Log.d("isi cursor", "movieList: ${movieList.count()}")
        return movieList
    }

    fun mapCursorToArrayListTV(tvShowsCursor: Cursor): ArrayList<FavoriteTVShowDB>{
        val tvShowList = ArrayList<FavoriteTVShowDB>()
        Log.d("isicursor", "tvShowsCursor: $tvShowsCursor")
        while (tvShowsCursor.moveToNext()) {
            val id =
                tvShowsCursor.getInt(tvShowsCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTVColumns.ID))
            val title =
                tvShowsCursor.getString(tvShowsCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTVColumns.TITLE))
            val rating =
                tvShowsCursor.getDouble(tvShowsCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTVColumns.RATING))
            val overview =
                tvShowsCursor.getString(tvShowsCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTVColumns.OVERVIEW))
            val release =
                tvShowsCursor.getString(tvShowsCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTVColumns.RELEASE))
            val posterPath =
                tvShowsCursor.getString(tvShowsCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTVColumns.POSTER_PATH))
            val backdropPath =
                tvShowsCursor.getString(tvShowsCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTVColumns.BACKDROP_PATH))
            tvShowList.add(
                FavoriteTVShowDB(
                    id,
                    title,
                    rating,
                    overview,
                    release,
                    posterPath,
                    backdropPath
                )
            )
        }
        Log.d("isilist", "tvshowList: $tvShowList")
        return tvShowList
    }
}