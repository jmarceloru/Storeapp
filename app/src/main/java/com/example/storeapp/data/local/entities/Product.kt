package com.example.storeapp.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.storeapp.domain.models.Product as ProductDomain

@Entity(tableName = "product")
data class Product(
    val category: String,
    val description: String,
    @PrimaryKey
    val id: Int,
    val image: String,
    val price: Double,
    @Embedded
    val rating: Rating,
    val title: String,
    val favorite: Boolean = false
)

fun Product.toDomain(): ProductDomain =
    ProductDomain(
        this.category,
        this.description,
        this.id,
        this.image,
        this.price,
        this.rating.toDomain(),
        this.title,
        this.favorite
    )

