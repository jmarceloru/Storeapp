package com.example.framework.local.entities

import com.example.data.models.RatingData


data class RatingEntity(
    val count: Int,
    val rate: Double
)

fun RatingEntity.toRatingData(): RatingData =
    RatingData(
        this.count,
        this.rate
    )

fun RatingData.toRatingEntity(): RatingEntity =
    RatingEntity(
        this.count,
        this.rate
    )