package com.example.framework.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.models.CategoryData

@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey
    val title: String,
)

fun CategoryEntity.toCategoryData(): CategoryData =
    CategoryData(this.title)
