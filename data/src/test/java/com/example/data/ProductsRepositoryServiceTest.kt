package com.example.data

import com.example.data.datasources.ProductLocalDataService
import com.example.data.datasources.ProductRemoteDataService
import com.example.data.models.ProductData
import com.example.data.models.toDomain
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class ProductsRepositoryServiceTest {

    @Mock
    private lateinit var productRemoteRepository: ProductRemoteDataService

    @Mock
    private lateinit var productLocalRepository: ProductLocalDataService

    private lateinit var productsRepositoryService: ProductsRepositoryService

    @Before
    fun setUp() {
        productsRepositoryService = ProductsRepositoryService(
            productRemoteDataService = productRemoteRepository,
            productLocalDataService = productLocalRepository
        )
    }

    @Test
    fun `Categories are taken from local data source if available`(): Unit = runBlocking {
        val localCategories = sampleCategoriesData("jewerly", "electronics")
        whenever(productLocalRepository.fetchCategories()).thenReturn(flowOf(localCategories))
        val result = productsRepositoryService.fetchCategories()
        assertEquals(localCategories.map { it.toDomain() }, result.first())
    }

    @Test
    fun `Products are saved to local data source when it's empty`(): Unit = runBlocking {
        val localProducts = emptyList<ProductData>()
        val remoteProducts = sampleProductsData(3, 4)
        whenever(productLocalRepository.fetchProductsByCategory("jewerly")).thenReturn(
            flowOf(
                localProducts
            )
        )
        whenever(productRemoteRepository.fetchProductsByCategory("jewerly")).thenReturn(
            remoteProducts
        )

        productsRepositoryService.fetchProductsByCategory("jewerly").first()

        verify(productLocalRepository).saveProducts(remoteProducts)
    }

    @Test
    fun `Switching favorite marks as favorite an unfavorite product`(): Unit = runBlocking {
        val product = sampleProductData(1).copy(favorite = false)
        productsRepositoryService.updateProduct(product.toDomain())
        verify(productLocalRepository).saveProduct(product)
    }

    @Test
    fun `Switching favorite marks as unfavorite an favorite product`(): Unit = runBlocking {
        val product = sampleProductData(1).copy(favorite = true)
        productsRepositoryService.updateProduct(product.toDomain())
        verify(productLocalRepository).saveProduct(product)
    }
}