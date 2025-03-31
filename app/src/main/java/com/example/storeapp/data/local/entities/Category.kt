package com.example.storeapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.storeapp.domain.models.Category as CategoryDomain

@Entity(tableName = "category")
data class Category(
    @PrimaryKey
    val title: String,
    val image: Int
)

fun Category.toDomain() : CategoryDomain =
    CategoryDomain(
        title = this.title,
        image = this.image
    )
