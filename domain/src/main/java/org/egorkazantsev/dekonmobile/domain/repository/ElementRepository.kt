package org.egorkazantsev.dekonmobile.domain.repository

import java.util.*

interface ElementRepository {

    fun setCriteriaValue(modelId: UUID, criteriaId: UUID, criteriaValue: Double)
}