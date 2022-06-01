package org.egorkazantsev.dekonmobile.domain.usecase

import org.egorkazantsev.dekonmobile.domain.repository.ModelRepository
import org.egorkazantsev.dekonmobile.domain.testModelExpected
import org.egorkazantsev.dekonmobile.domain.testModelRepo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import java.util.*

class GetModelByIdUCTest {

    val modelRepository = mock<ModelRepository>()

    private val testId = UUID.fromString("abcefaf2-df49-465e-9afe-83ae7b91af6c")

    @Test
    fun `should return the same model as in a repository`() {

        Mockito.`when`(modelRepository.getModelById(testId))
            .thenReturn(testModelRepo)

        val useCase = GetModelByIdUC(modelRepository)

        val actual = useCase.execute(testId)
        val expected = testModelExpected

        Assertions.assertEquals(expected, actual)
    }
}
