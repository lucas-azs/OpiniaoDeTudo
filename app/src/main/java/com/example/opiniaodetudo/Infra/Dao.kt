package com.example.opiniaodetudo.Infra

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*


class ReviewDBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_NAME = "review_database"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE ${ReviewTableInfo.TABLE_NAME} (" +
                    " ${ReviewTableInfo.COLUMN_ID} TEXT PRIMARY KEY, " +
                    " ${ReviewTableInfo.COLUMN_NAME} TEXT NOT NULL, " +
                    " ${ReviewTableInfo.COLUMN_REVIEW} TEXT " +
                    ")"
        )
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//como eh a versao 1, podemos simplesmente criar a database
        onCreate(db)
    }
}

class ReviewDAOSQLite{
    private val dbHelper: SQLiteOpenHelper
    constructor(context:Context){
        dbHelper = ReviewDBHelper(context)
    }

    fun save(name: String, review: String) {
        val writableDatabase = dbHelper.writableDatabase
        writableDatabase.insert(ReviewTableInfo.TABLE_NAME, null, ContentValues().apply {
            put(ReviewTableInfo.COLUMN_ID, UUID.randomUUID().toString())
            put(ReviewTableInfo.COLUMN_NAME, name)
            put(ReviewTableInfo.COLUMN_REVIEW, review)
        })
        writableDatabase.close()
    }


}



object ReviewTableInfo {
    const val TABLE_NAME = "Review"
    const val COLUMN_ID = "id"
    const val COLUMN_NAME = "name"
    const val COLUMN_REVIEW = "review"
}