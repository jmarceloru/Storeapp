package com.example.domain.usecases

import com.example.domain.repository.ProductsRepository
import com.example.test.unit.sampleCategories
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock


class FetchCategoriesUseCaseTest {

    @Test
    fun `Invoke calls repository`() {
        //Given
        val categoriesFlow = flowOf(sampleCategories())
        val mockExample = mock<ProductsRepository> {
            on { fetchCategories() } doReturn categoriesFlow
        }
        //When
        val result = FetchCategoriesUseCase(mockExample).invoke()
        //Then
        assertEquals(categoriesFlow, result)
    }

}