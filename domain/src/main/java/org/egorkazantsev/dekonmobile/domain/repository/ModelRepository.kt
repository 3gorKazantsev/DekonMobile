package org.egorkazantsev.dekonmobile.domain.repository

import org.egorkazantsev.dekonmobile.domain.model.Model

interface ModelRepository {

    fun getModelList(): List<Model>
}