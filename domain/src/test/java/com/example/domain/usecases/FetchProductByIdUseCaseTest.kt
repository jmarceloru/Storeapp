package com.example.domain.usecases

import com.example.domain.repository.ProductsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import kotlin.test.assertEquals


class FetchProductByIdUseCaseTest {

    @Test
    fun `Invoke calls repository`(): Unit = runBlocking {
        //Given
        val product = sampleProduct(1)
        val mockExample = mock<ProductsRepository> {
            onBlocking {
                fetchProductById(1)
            } doReturn product
        }
        val result = FetchProductByIdUseCase(mockExample).invoke(1)
        assertEquals(product, result)
    }
}