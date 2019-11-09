package com.example.opiniaodetudo.Repository

import android.content.Context
import androidx.room.*
import com.example.opiniaodetudo.Model.Review

@Dao
interface ReviewDao {
    @Insert
    fun save(review:Review)

    //verificar porqueo Review... não funciona ---- ReviewTableInfo
  //  @Query("SELECT * from ${ReviewTableInfo.TABLE_NAME}")
    fun listAll():List<Review>
}


@Database(entities = arrayOf(Review::class), version = 2)
abstract class ReviewDatabase : RoomDatabase(){
    companion object {
        private var instance: ReviewDatabase? = null
        fun getInstance(context: Context): ReviewDatabase {
            if(instance == null){
                instance = Room
                    .databaseBuilder(context, ReviewDatabase::class.java, "review_database")
                    .build()
            }
            return instance!!
        }
    }
    abstract fun reviewDao():ReviewDao
}
