package org.egorkazantsev.dekonmobile.data.repository

import org.egorkazantsev.dekonmobile.data.storage.models
import org.egorkazantsev.dekonmobile.domain.model.Criteria
import org.egorkazantsev.dekonmobile.domain.repository.ElementRepository
import java.util.*

class ElementRepositoryImpl: ElementRepository {
    override fun getCriteriaById(modelId: UUID, criteriaId: UUID): Criteria {
        return models.find { it.id == modelId }?.root?.elements?.find { it.id == criteriaId }!!
    }

    override fun setCriteriaValue(modelId: UUID, criteriaId: UUID, criteriaValue: Double) {
        models.find { it.id == modelId }!!.root.elements.find { it.id == criteriaId }!!.value = criteriaValue
    }
}