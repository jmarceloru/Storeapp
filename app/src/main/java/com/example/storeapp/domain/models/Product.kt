package com.example.storeapp.domain.models

data class Product(
    val category: String = "",
    val description: String = "",
    val id: Int = 0,
    val image: String = "",
    val price: Double = 0.0,
    val rating: Rating = Rating(),
    val title: String = "",
    val favorite: Boolean = false
)
