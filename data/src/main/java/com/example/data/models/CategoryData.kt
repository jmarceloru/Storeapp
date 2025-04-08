package com.example.data.models

import com.example.domain.models.Category

data class CategoryData(
    val title: String
)

fun CategoryData.toDomain(): Category =
    Category(
        this.title
    )