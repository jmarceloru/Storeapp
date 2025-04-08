package com.example.data.models

import com.example.domain.models.Rating

data class RatingData(
    val count: Int,
    val rate: Double
)

fun RatingData.toDomain(): Rating =
    Rating(
        this.count,
        this.rate
    )