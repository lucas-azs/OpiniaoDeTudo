package com.example.opiniaodetudo.Extension

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.opiniaodetudo.Model.Review

fun SQLiteDatabase.selectAll(tableName:String, columns:Array<String>): Cursor {
    return this.query(
        tableName,
        columns,
        null,
        null,
        null,
        null,
        null
    )
}

fun createReview(cursor:Cursor): Review {
    val id = cursor.getString(0)
    val name = cursor.getString(1)
    val review = cursor.getString(2)
    return Review(id, name, review)
}

//fun listAll(): List<Review> {
//    val readableDatabase = dbHelper.readableDatabase
//    val cursor:Cursor = readableDatabase.selectAll(
//        ReviewTableInfo.TABLE_NAME,
//        arrayOf(
//            ReviewTableInfo.COLUMN_ID,
//            ReviewTableInfo.COLUMN_NAME,
//            ReviewTableInfo.COLUMN_REVIEW))
//    val list = mutableListOf<Review>()
//    while(cursor.moveToNext()){
//        list.add(createReview(cursor))
//    }
//    readableDatabase.close()
//    return list
//}

fun SQLiteDatabase.query(
    tableName:String,
    columns:Array<String>,
    selection:String? = null,
    selectionArgs:Array<String>? = null,
    groupBy:String? = null,
    having:String? = null,
    orderBy:String? = null): Cursor {
    return this.query(
        tableName,
        columns,
        selection,
        selectionArgs,
        groupBy,
        having,
        orderBy
    )
}



class SQLiteDatabaseTool{
    companion object {
        fun selectAll(db:SQLiteDatabase, tableName:String, columns:Array<String>): Cursor {
            return db.query(
                tableName,
                columns,
                null,
                null,
                null,
                null,
                null
            )
        }
    }
}