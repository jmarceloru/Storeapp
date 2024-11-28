package com.example.storeapp.data.models

import kotlinx.serialization.Serializable
import com.example.storeapp.domain.models.Rating as RatingDomain

@Serializable
data class Rating(
    val count: Int,
    val rate: Double
)

fun Rating.toDomainRating(): RatingDomain =
    RatingDomain(
        this.count,
        this.rate
    )