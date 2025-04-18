package com.example.data

import com.example.data.models.CategoryData
import com.example.data.models.ProductData
import com.example.data.models.RatingData

fun sampleCategoryData(title: String) = CategoryData(
    title = title
)

fun sampleCategoriesData(vararg titles: String) = titles.map {
    sampleCategoryData(it)
}

fun sampleProductData(id: Int) = ProductData(
    category = "jewerly",
    description = "description",
    id = id,
    image = "https://example.com/image.png",
    price = 10.0,
    rating = RatingData(
        count = 100,
        rate = 4.5
    ),
    title = "Product $id",
    favorite = false
)

fun sampleProductsData(vararg ids: Int) = ids.map {
    sampleProductData(it)
}