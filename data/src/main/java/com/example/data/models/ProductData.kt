package com.example.data.models

import com.example.domain.models.Product

data class ProductData(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: RatingData,
    val title: String,
    val favorite: Boolean
)

fun ProductData.toDomain(): Product =
    Product(
        this.category,
        this.description,
        this.id,
        this.image,
        this.price,
        this.rating.toDomain(),
        this.title,
        this.favorite
    )