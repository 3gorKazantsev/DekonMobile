package org.egorkazantsev.dekonmobile.domain.repository

import org.egorkazantsev.dekonmobile.domain.model.Criteria
import java.util.*

interface ElementRepository {

    fun getCriteriaById(modelId: UUID, criteriaId: UUID): Criteria

    fun setCriteriaValue(modelId: UUID, criteriaId: UUID, criteriaValue: Double)
}