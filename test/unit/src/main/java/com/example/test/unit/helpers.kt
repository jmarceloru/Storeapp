package com.example.test.unit

import com.example.data.models.CategoryData
import com.example.data.models.ProductData
import com.example.data.models.RatingData
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

fun sampleCategorydata(title: String) = CategoryData(
    title = title
)

fun sampleCategoriesData(vararg titles: String) = titles.map {
    sampleCategorydata(it)
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
