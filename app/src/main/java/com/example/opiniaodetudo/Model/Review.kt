package com.example.opiniaodetudo.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Review(
    @PrimaryKey
    val id: String,
    val name: String,
    val review: String
)