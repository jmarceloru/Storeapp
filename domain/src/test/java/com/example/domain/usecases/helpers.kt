package com.example.domain.usecases

import com.example.domain.models.Category
import com.example.domain.models.Product
import com.example.domain.models.Rating

fun sampleCategory(title: String) = Category(
    title = title
)

fun sampleCategories(vararg titles: String) = titles.map {
    sampleCategory(it)
}

fun sampleProduct(id: Int) = Product(
    category = "jewerly",
    description = "description",
    id = id,
    image = "https://example.com/image.png",
    price = 10.0,
    rating = Rating(
        count = 100,
        rate = 4.5
    ),
    title = "Product $id",
    favorite = false
)

fun sampleProducts(vararg ids: Int) = ids.map {
    sampleProduct(it)
}