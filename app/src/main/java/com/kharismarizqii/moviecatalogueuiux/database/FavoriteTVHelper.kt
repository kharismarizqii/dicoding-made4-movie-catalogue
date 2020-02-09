package com.kharismarizqii.moviecatalogueuiux.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.kharismarizqii.moviecatalogueuiux.database.DatabaseContract.FavoriteTVColumns.Companion.TABLE_NAME
import com.kharismarizqii.moviecatalogueuiux.database.DatabaseContract.FavoriteTVColumns.Companion.ID
import java.sql.SQLException

class FavoriteTVHelper(context: Context) {
    companion object {
        private const val DATABASE_TABLE = TABLE_NAME
        private lateinit var databaseTVHelper: DatabaseTVHelper
        private var INSTANCE: FavoriteTVHelper? = null

        private lateinit var database: SQLiteDatabase

        fun getInstance(context: Context): FavoriteTVHelper {
            if (INSTANCE == null) {
                INSTANCE = FavoriteTVHelper(context)
            }
            return INSTANCE as FavoriteTVHelper
        }
    }

    init {
        databaseTVHelper = DatabaseTVHelper(context)
    }

    @Throws(SQLException::class)
    fun open() {
        database = databaseTVHelper.writableDatabase
    }

    fun close() {
        databaseTVHelper.close()

        if (database.isOpen)
            database.close()
    }

    fun queryAll(): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$ID ASC"
        )
    }

    fun queryById(id: String): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            "$ID = ?",
            arrayOf(id),
            null,
            null,
            null,
            null
        )
    }

    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

    fun update(id: String, values: ContentValues?): Int {
        return database.update(DATABASE_TABLE, values, "$ID = ?", arrayOf(id))
    }
}