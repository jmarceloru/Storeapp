package com.example.domain.usecases

import com.example.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock


class FetchProductsByCategoryUseCaseTest {

    @Test
    fun `Invoke calls repository`() {
        val productosFlow = flowOf(sampleProducts(1,2,3))
        val mockExample = mock<ProductsRepository>() {
            on { fetchProductsByCategory("jewerly") } doReturn  productosFlow
        }
        val result = FetchProductsByCategoryUseCase(mockExample).invoke("jewerly")
        assertEquals(productosFlow, result)
    }
}