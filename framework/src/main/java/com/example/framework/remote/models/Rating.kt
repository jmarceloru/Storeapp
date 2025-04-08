package com.example.framework.remote.models

import com.example.data.models.RatingData
import kotlinx.serialization.Serializable


@Serializable
data class Rating(
    val count: Int,
    val rate: Double
)

fun Rating.toRatingData(): RatingData =
    RatingData(
        this.count,
        this.rate
    )