package com.example.storeapp.data

import com.example.storeapp.R
import com.example.storeapp.data.local.ProductLocalDataService
import com.example.storeapp.data.local.entities.Rating
import com.example.storeapp.data.local.entities.toDomain
import com.example.storeapp.data.remote.models.Categories
import com.example.storeapp.data.remote.ProductRemoteDataService
import com.example.storeapp.data.remote.models.toProductEntity
import com.example.storeapp.domain.models.Category
import com.example.storeapp.domain.repository.ProductsRepository
import com.example.storeapp.data.local.entities.Category as CategoryEntity
import com.example.storeapp.data.local.entities.Product as ProductEntity
import com.example.storeapp.domain.models.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach


class ProductsRepositoryService(
    private val productRemoteDataService: ProductRemoteDataService,
    private val productLocalDataService: ProductLocalDataService
) : ProductsRepository {
    override fun fetchCategories(): Flow<List<Category>> =
        productLocalDataService.fetchCategories().onEach { categories ->
            if (categories.isEmpty()) {
                val categoriesEntities = productRemoteDataService.fetchCategories().map {
                    CategoryEntity(
                        it,
                        getImage(it)
                    )
                }
                productLocalDataService.saveCategories(categoriesEntities)
            }
        }.map { it.map { c -> c.toDomain() } }


    /*    override fun fetchCategories(): Flow<List<Category>> {
           val categories: Flow<List<Category>> =
               productLocalDataService.fetchCategories().transform { categoriesEntity ->
                   categoriesEntity.takeIf { it.isNotEmpty() }
                       ?: productRemoteDataService.fetchCategories().map {
                           CategoryEntity(
                               it,
                               getImage(it)
                           )
                       }.also{
                           productLocalDataService.saveCategories(it)
                       }
                   emit(categoriesEntity.map { it.toDomain() })
               }
           return categories
       }*/

    override fun fetchProductsByCategory(category: String): Flow<List<Product>> =
        productLocalDataService.fetchProductsByCategory(category).onEach { products ->
            if (products.isEmpty()) {
                val producstEntity =
                    productRemoteDataService.fetchProductsByCategory(category).map {
                        it.toProductEntity()
                    }
                productLocalDataService.saveProducts(producstEntity)
            }
        }.map {
            it.map { p -> p.toDomain() }
        }

    /*    override fun fetchProductsByCategory(category: String): Flow<List<Product>> {
        val products: Flow<List<Product>> = productLocalDataService.fetchProductsByCategory(category).transform { products->
            products.takeIf { it.isNotEmpty() }
                ?: productRemoteDataService.fetchProductsByCategory(category).map {
                    it.toProductEntity()
                }.also {
                    productLocalDataService.saveProducts(it)
                }
            emit(products.map { it.toDomain() })
        }
        return products
    }*/

    override suspend fun fetchProductById(idProduct: Int): Product {
        val product =
            productLocalDataService.fetchProductByIdProduct(idProduct).takeIf { it != null }
                ?: productRemoteDataService.fetchProductById(idProduct).toProductEntity()
                    .also { product ->
                        productLocalDataService.saveProduct(product)
                    }

        return product.toDomain()
    }

    override suspend fun updateProduct(product: Product) {
        val rating = Rating(
            count = product.rating.count,
            rate = product.rating.rate
        )
        val productEntity = ProductEntity(
            category = product.category,
            description = product.description,
            id = product.id,
            image = product.image,
            price = product.price,
            rating = rating,
            title = product.title,
            favorite = product.favorite
        )
        productLocalDataService.saveProduct(productEntity)
    }

    private fun getImage(title: String): Int =
        when (title) {
            Categories.Electronics.title -> R.drawable.electronics
            Categories.Jewerly.title -> R.drawable.jewelery
            Categories.MenClothing.title -> R.drawable.menclothes
            Categories.WomenClothing.title -> R.drawable.womenclotehs
            else -> R.drawable.notfound
        }
}

