package org.egorkazantsev.dekonmobile.data.repository

import org.egorkazantsev.dekonmobile.domain.model.Model
import org.egorkazantsev.dekonmobile.domain.repository.ModelRepository

class ModelRepositoryImpl: ModelRepository {

    override fun getModelList(): List<Model> {
        return listOf(
            Model(Math.random().toString()),
            Model(Math.random().toString()),
            Model(Math.random().toString()),
            Model(Math.random().toString()),
            Model(Math.random().toString()),
            Model(Math.random().toString()),
            Model(Math.random().toString()),
            Model(Math.random().toString()),
            Model(Math.random().toString()),
            Model(Math.random().toString()),
            Model(Math.random().toString()),
            Model(Math.random().toString()),
            Model(Math.random().toString()),
            Model(Math.random().toString()),
            Model(Math.random().toString()),
            Model(Math.random().toString()),
            Model(Math.random().toString()),
            Model(Math.random().toString()),
            Model(Math.random().toString()),
            Model(Math.random().toString())
        )
    }
}