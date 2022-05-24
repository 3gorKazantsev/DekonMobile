package org.egorkazantsev.dekonmobile.domain.usecase

import org.egorkazantsev.dekonmobile.domain.model.Criteria
import org.egorkazantsev.dekonmobile.domain.repository.ElementRepository
import java.util.*

class GetCriteriaByIdUC(private val elementRepository: ElementRepository) {

    fun execute(modelId: UUID, criteriaId: UUID): Criteria {
        return elementRepository.getCriteriaById(modelId, criteriaId)
    }
}