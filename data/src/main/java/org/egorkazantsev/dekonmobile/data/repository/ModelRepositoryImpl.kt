package org.egorkazantsev.dekonmobile.data.repository

import org.egorkazantsev.dekonmobile.data.storage.models
import org.egorkazantsev.dekonmobile.domain.model.Criteria
import org.egorkazantsev.dekonmobile.domain.model.Matrix
import org.egorkazantsev.dekonmobile.domain.model.Model
import org.egorkazantsev.dekonmobile.domain.model.Owner
import org.egorkazantsev.dekonmobile.domain.repository.ModelRepository
import java.util.*

class ModelRepositoryImpl : ModelRepository {

    override fun getModelList(): List<Model> {
        return models
    }

    override fun getModelById(id: UUID): Model {
        return models.find { it.id == id }!!
    }
}