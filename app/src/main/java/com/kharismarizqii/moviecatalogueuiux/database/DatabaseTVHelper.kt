package com.kharismarizqii.moviecatalogueuiux.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.kharismarizqii.moviecatalogueuiux.database.DatabaseContract.FavoriteTVColumns.Companion.TABLE_NAME

internal class DatabaseTVHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "dbfavoritetv"
        private const val DATABASE_VERSION = 3
        private val SQL_CREATE_TABLE_FAVORITE_TV = "CREATE TABLE $TABLE_NAME" +
                "(${DatabaseContract.FavoriteTVColumns.ID} INTEGER PRIMARY KEY," +
                "${DatabaseContract.FavoriteTVColumns.TITLE} TEXT NOT NULL," +
                "${DatabaseContract.FavoriteTVColumns.OVERVIEW} TEXT NOT NULL," +
                "${DatabaseContract.FavoriteTVColumns.RATING} DOUBLE NOT NULL," +
                "${DatabaseContract.FavoriteTVColumns.RELEASE} TEXT NOT NULL," +
                "${DatabaseContract.FavoriteTVColumns.POSTER_PATH} TEXT NOT NULL," +
                "${DatabaseContract.FavoriteTVColumns.BACKDROP_PATH} TEXT NOT NULL);"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_FAVORITE_TV)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }


}