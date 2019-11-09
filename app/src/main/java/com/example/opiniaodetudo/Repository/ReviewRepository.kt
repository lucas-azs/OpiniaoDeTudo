package com.example.opiniaodetudo.Repository

import android.content.Context
import com.example.opiniaodetudo.Model.Review
import java.util.*

//class ReviewRepository {
//    private constructor()
//
//    companion object {
//        val instance: ReviewRepository = ReviewRepository()
//    }
//
//    private val data = mutableListOf<Review>()
//    fun save(name: String, review: String) {
//        data.add(Review(UUID.randomUUID().toString(), name, review))
//    }
//
//    fun listAll(): List<Review> {
//        return data.toList()
//    }
//}

class ReviewRepository{
    private val reviewDao: ReviewDao
    constructor(context: Context){
        val reviewDatabase = ReviewDatabase.getInstance(context)
        reviewDao = reviewDatabase.reviewDao()
    }
    fun save(name: String, review: String) {
        reviewDao.save(Review(UUID.randomUUID().toString(), name, review))
    }
    fun listAll(): List<Review> {
        return reviewDao.listAll()
    }
}
