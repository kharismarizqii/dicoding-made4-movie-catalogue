package com.kharismarizqii.moviecatalogueuiux.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.kharismarizqii.moviecatalogueuiux.database.DatabaseContract.FavoriteMovieColumns.Companion.TABLE_NAME
import com.kharismarizqii.moviecatalogueuiux.database.DatabaseContract.FavoriteMovieColumns.Companion.ID
import java.sql.SQLException

class FavoriteMovieHelper(context: Context) {
    companion object{
        private const val DATABASE_TABLE = TABLE_NAME
        private lateinit var dataBaseMovieHelper: DatabaseMovieHelper
        private var INSTANCE: FavoriteMovieHelper? = null

        private lateinit var database: SQLiteDatabase

        fun getInstance(context: Context): FavoriteMovieHelper{
            if(INSTANCE == null){
                INSTANCE = FavoriteMovieHelper(context)
            }
            return INSTANCE as FavoriteMovieHelper
        }

    }

    init {
        dataBaseMovieHelper = DatabaseMovieHelper(context)
    }

    @Throws(SQLException::class)
    fun open(){
        database = dataBaseMovieHelper.writableDatabase
    }
    fun close(){
        dataBaseMovieHelper.close()

        if(database.isOpen)
            database.close()
    }

    fun queryAll(): Cursor{
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            null
        )
    }

    fun queryById(id: String): Cursor{
        return database.query(
            DATABASE_TABLE,
            null,
            "$ID = ?",
            arrayOf(id),
            null,
            null,
            null,
            null)
    }

    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

    fun update(id: String, values: ContentValues?): Int {
        return database.update(DATABASE_TABLE, values, "$ID = ?", arrayOf(id))
    }

}