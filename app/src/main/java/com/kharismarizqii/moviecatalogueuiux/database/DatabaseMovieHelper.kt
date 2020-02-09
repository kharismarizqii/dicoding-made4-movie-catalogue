package com.kharismarizqii.moviecatalogueuiux.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.kharismarizqii.moviecatalogueuiux.database.DatabaseContract.FavoriteMovieColumns.Companion.TABLE_NAME

internal class DatabaseMovieHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "dbfavoritemovie"
        private const val DATABASE_VERSION = 3
        private val SQL_CREATE_TABLE_FAVORITE = "CREATE TABLE $TABLE_NAME" +
                "(${DatabaseContract.FavoriteMovieColumns.ID} INTEGER PRIMARY KEY," +
                "${DatabaseContract.FavoriteMovieColumns.TITLE} TEXT NOT NULL," +
                "${DatabaseContract.FavoriteMovieColumns.OVERVIEW} TEXT NOT NULL," +
                "${DatabaseContract.FavoriteMovieColumns.RATING} DOUBLE NOT NULL," +
                "${DatabaseContract.FavoriteMovieColumns.RELEASE} TEXT NOT NULL," +
                "${DatabaseContract.FavoriteMovieColumns.POSTER_PATH} TEXT NOT NULL," +
                "${DatabaseContract.FavoriteMovieColumns.BACKDROP_PATH} TEXT NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_FAVORITE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

}