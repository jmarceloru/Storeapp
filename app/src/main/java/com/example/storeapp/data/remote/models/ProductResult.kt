package com.example.storeapp.data.remote.models

import kotlinx.serialization.Serializable
import com.example.storeapp.data.local.entities.Product as ProductEntity

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

fun ProductResult.toProductEntity(): ProductEntity =
    ProductEntity(
        this.category,
        this.description,
        this.id,
        this.image,
        this.price,
        this.rating.toEntityRating(),
        this.title
    )