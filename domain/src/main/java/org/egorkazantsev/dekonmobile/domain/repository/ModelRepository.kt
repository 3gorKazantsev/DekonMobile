package org.egorkazantsev.dekonmobile.domain.repository

import org.egorkazantsev.dekonmobile.domain.model.Model
import java.util.*

interface ModelRepository {

    fun getModelList(): List<Model>

    fun getModelById(id: UUID): Model
}