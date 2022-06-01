package org.egorkazantsev.dekonmobile.domain.usecase

import org.egorkazantsev.dekonmobile.domain.repository.ModelRepository
import org.egorkazantsev.dekonmobile.domain.testModelExpected
import org.egorkazantsev.dekonmobile.domain.testModelRepo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetAllModelsUCTest {

    val modelRepository = mock<ModelRepository>()

    @Test
    fun `should return the same model list as in a repository`() {

        Mockito.`when`(modelRepository.getModelList())
            .thenReturn(listOf(testModelRepo))

        val useCase = GetAllModelsUC(modelRepository)

        val actual = useCase.execute()
        val expected = listOf(testModelExpected)

        Assertions.assertEquals(expected, actual)
    }
}

