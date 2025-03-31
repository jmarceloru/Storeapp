package com.example.storeapp.data.local.entities

import com.example.storeapp.domain.models.Rating as RatingDomain


data class Rating(
    val count: Int,
    val rate: Double
)

fun Rating.toDomain(): RatingDomain =
    RatingDomain(
        this.count,
        this.rate
    )


