package com.example.opiniaodetudo.Repository

import com.example.opiniaodetudo.Model.Review
import java.util.*

class ReviewRepository {
    private constructor()

    companion object {
        val instance: ReviewRepository = ReviewRepository()
    }

    private val data = mutableListOf<Review>()
    fun save(name: String, review: String) {
        data.add(Review(UUID.randomUUID().toString(), name, review))
    }
}
