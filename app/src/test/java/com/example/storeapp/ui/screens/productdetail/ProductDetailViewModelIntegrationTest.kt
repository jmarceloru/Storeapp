package com.example.storeapp.ui.screens.productdetail

import app.cash.turbine.test
import com.example.domain.models.Result
import com.example.domain.usecases.FavoriteClickUseCase
import com.example.domain.usecases.FetchProductByIdUseCase
import com.example.test.data.buildProductRepositoryService
import com.example.test.rule.CoroutinesTestRule
import com.example.test.unit.sampleProduct
import com.example.test.unit.sampleProductsData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class ProductDetailViewModelIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var productDetailViewModel: ProductDetailViewModel

    @Before
    fun setUp() {
        val products = sampleProductsData(1, 2, 3, 4)
        val productsRepository = buildProductRepositoryService(localDataProducts = products)
        productDetailViewModel = ProductDetailViewModel(
            FetchProductByIdUseCase(productsRepository),
            FavoriteClickUseCase(productsRepository)
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Favorite is updated in local data source`() = runTest {

        productDetailViewModel.fetchProductById(1)

        productDetailViewModel.state.test {
            assertEquals(Result.Loading, awaitItem())
            val product = sampleProduct(1)
            assertEquals(Result.Success(product), awaitItem())
            productDetailViewModel.onFavoriteClick()
            runCurrent()
            assertEquals(Result.Success(product.copy(favorite = true)), awaitItem())
        }
    }
}