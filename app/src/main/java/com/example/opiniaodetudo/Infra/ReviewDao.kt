package com.example.opiniaodetudo.Infra

import android.content.Context
import androidx.room.*
import com.example.opiniaodetudo.Model.Review

@Dao
interface ReviewDao {
    @Insert
    fun save(review: Review)

    @Query("SELECT * from ${ReviewTableInfo.TABLE_NAME}")
    fun listAll(): List<Review>

    @Update
    fun update(review: Review)

    @Delete
    fun delete(item: Review)


}





