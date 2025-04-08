package com.example.framework.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.models.ProductData

@Entity(tableName = "product")
data class ProductEntity(
    val category: String,
    val description: String,
    @PrimaryKey
    val id: Int,
    val image: String,
    val price: Double,
    @Embedded
    val rating: RatingEntity,
    val title: String,
    val favorite: Boolean = false
)

fun ProductEntity.toProductData(): ProductData =
    ProductData(
        this.category,
        this.description,
        this.id,
        this.image,
        this.price,
        this.rating.toRatingData(),
        this.title,
        this.favorite
    )

fun ProductData.toProductEntitty(): ProductEntity =
    ProductEntity(
        this.category,
        this.description,
        this.id,
        this.image,
        this.price,
        this.rating.toRatingEntity(),
        this.title,
        this.favorite
    )