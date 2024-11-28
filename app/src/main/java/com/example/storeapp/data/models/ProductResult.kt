package com.example.storeapp.data.models

import com.example.storeapp.domain.models.Product
import kotlinx.serialization.Serializable

@Serializable
data class ProductResult(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)

fun ProductResult.toDomainProduct(): Product =
    Product(
        this.category,
        this.description,
        this.id,
        this.image,
        this.price,
        this.rating.toDomainRating(),
        this.title
    )
