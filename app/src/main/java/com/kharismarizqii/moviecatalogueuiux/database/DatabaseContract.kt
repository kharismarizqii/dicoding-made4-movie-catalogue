package com.kharismarizqii.moviecatalogueuiux.database

import android.provider.BaseColumns

internal class DatabaseContract {
    internal class FavoriteMovieColumns: BaseColumns{
        companion object{
            const val TABLE_NAME = "favorite_movie"
            const val ID = "_id"
            const val TITLE = "title"
            const val OVERVIEW = "overview"
            const val RATING = "rating"
            const val RELEASE = "release"
            const val POSTER_PATH = "poster_path"
            const val BACKDROP_PATH = "backdrop_path"
        }
    }

    internal class FavoriteTVColumns: BaseColumns{
        companion object{
            const val TABLE_NAME = "favorite_tv"
            const val ID = "_id"
            const val TITLE = "title"
            const val OVERVIEW = "overview"
            const val RATING = "rating"
            const val RELEASE = "release"
            const val POSTER_PATH = "poster_path"
            const val BACKDROP_PATH = "backdrop_path"
        }
    }
}