package com.example.opiniaodetudo.Infra

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.opiniaodetudo.Model.Review

@Database(entities = arrayOf(Review::class), version = 2)
abstract class ReviewDatabase() : RoomDatabase(), Parcelable {
    companion object {
        private var instance: ReviewDatabase? = null
        fun getInstance(context: Context): ReviewDatabase {
            if (instance == null) {
                instance = Room
                    .databaseBuilder(context, ReviewDatabase::class.java, "review_database")
                    .build()
            }
            return instance!!
        }

    }


    abstract fun reviewDao(): ReviewDao
}