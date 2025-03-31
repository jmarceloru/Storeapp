package com.example.storeapp.data.remote.models

import kotlinx.serialization.Serializable
import com.example.storeapp.data.local.entities.Rating as RatingEntity
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

fun Rating.toEntityRating(): RatingEntity =
    RatingEntity(
        this.count,
        this.rate
    )
