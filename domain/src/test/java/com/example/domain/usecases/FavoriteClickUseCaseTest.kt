package com.example.domain.usecases

import com.example.domain.repository.ProductsRepository
import com.example.test.unit.sampleProduct
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify


class FavoriteClickUseCaseTest {

    @Test
    fun `Invoke calls repository`(): Unit = runBlocking{
        val product = sampleProduct(1)
        val repository = mock<ProductsRepository>()
        val useCase = FavoriteClickUseCase(repository)
        useCase(product)

        verify(repository).updateProduct(product)
    }
}