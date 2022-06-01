package org.egorkazantsev.dekonmobile.domain.usecase

import org.egorkazantsev.dekonmobile.domain.repository.ElementRepository
import org.egorkazantsev.dekonmobile.domain.testModelExpected
import org.egorkazantsev.dekonmobile.domain.testModelRepo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import java.util.*

class GetCriteriaByIdUCTest {

    val elementRepository = mock<ElementRepository>()

    private val testModelId = UUID.fromString("abcefaf2-df49-465e-9afe-83ae7b91af6c")
    private val testCriteriaId = UUID.fromString("b026f6fa-c834-4612-a3ce-71cad8bc92cb")

    @Test
    fun `should return the same criteria as in a repository`() {

        Mockito.`when`(elementRepository.getCriteriaById(testModelId, testCriteriaId))
            .thenReturn(testModelRepo.root.elements.find { it.id == testCriteriaId })

        val useCase = GetCriteriaByIdUC(elementRepository)

        val actual = useCase.execute(testModelId, testCriteriaId)
        val expected = testModelExpected.root.elements.find { it.id == testCriteriaId }

        Assertions.assertEquals(expected, actual)
    }
}
