package com.example.storeapp.ui.screens.categoriesdetail

import app.cash.turbine.test
import com.example.data.models.toDomain
import com.example.domain.models.Result
import com.example.domain.usecases.FetchProductsByCategoryUseCase
import com.example.test.data.buildProductRepositoryService
import com.example.test.rule.CoroutinesTestRule
import com.example.test.unit.sampleProductsData
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class CategoryDetailIntegrationTest {

    @get:Rule
    val coroutineTestRule = CoroutinesTestRule()

    private lateinit var categoryDetailViewModel: CategoryDetailViewModel

    @Test
    fun `Products are loaded from remote data source when local data source is empty`() = runTest{
        val remoteProducts = sampleProductsData(1,2,3,4)
        categoryDetailViewModel = CategoryDetailViewModel(
            FetchProductsByCategoryUseCase(
                buildProductRepositoryService(
                    remoteDataProducts = remoteProducts
                )
            )
        )

        categoryDetailViewModel.loadProducts("jewerly")

        categoryDetailViewModel.state.test {
            assertEquals(Result.Loading,awaitItem())
            assertEquals(Result.Success(emptyList()),awaitItem())
            val products = remoteProducts.map { it.toDomain() }
            assertEquals(Result.Success(products),awaitItem())
        }
    }

    @Test
    fun `Products are loaded from local data source if available`() = runTest{
        val localProducts = sampleProductsData(1,2,3,4)
        categoryDetailViewModel = CategoryDetailViewModel(
            FetchProductsByCategoryUseCase(
                buildProductRepositoryService(
                    localDataProducts = localProducts
                )
            )
        )

        categoryDetailViewModel.loadProducts("jewerly")

        categoryDetailViewModel.state.test {
            assertEquals(Result.Loading,awaitItem())
            val products = localProducts.map { it.toDomain() }
            assertEquals(Result.Success(products),awaitItem())
        }
    }
}