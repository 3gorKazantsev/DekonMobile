package org.egorkazantsev.dekonmobile.domain.usecase

import org.egorkazantsev.dekonmobile.domain.repository.ElementRepository
import java.util.*

class SetCriteriaValueUC(private val elementRepository: ElementRepository) {

    fun execute(modelId: UUID, criteriaId: UUID, criteriaValue: Double) {
        elementRepository.setCriteriaValue(modelId, criteriaId, criteriaValue)
    }
}