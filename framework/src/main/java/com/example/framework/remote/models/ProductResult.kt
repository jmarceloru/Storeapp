package com.example.framework.remote.models

import com.example.data.models.ProductData
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


fun ProductResult.toProductData(): ProductData =
    ProductData(
        this.category,
        this.description,
        this.id,
        this.image,
        this.price,
        this.rating.toRatingData(),
        this.title,
        false
    )