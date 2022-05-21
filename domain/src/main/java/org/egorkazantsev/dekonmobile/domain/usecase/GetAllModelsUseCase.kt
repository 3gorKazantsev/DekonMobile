package org.egorkazantsev.dekonmobile.domain.usecase

import org.egorkazantsev.dekonmobile.domain.model.Model
import org.egorkazantsev.dekonmobile.domain.repository.ModelRepository

class GetAllModelsUseCase(private val modelRepository: ModelRepository) {

    fun execute(): List<Model> =
        modelRepository.getModelList()

}