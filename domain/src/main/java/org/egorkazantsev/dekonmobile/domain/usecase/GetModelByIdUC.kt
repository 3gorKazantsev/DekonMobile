package org.egorkazantsev.dekonmobile.domain.usecase

import org.egorkazantsev.dekonmobile.domain.model.Model
import org.egorkazantsev.dekonmobile.domain.repository.ModelRepository
import java.util.*

class GetModelByIdUC(private val modelRepository: ModelRepository) {

    fun execute(id: UUID): Model =
        modelRepository.getModelById(id)

}