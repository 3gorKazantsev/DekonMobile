package org.egorkazantsev.dekonmobile.data.repository

import org.egorkazantsev.dekonmobile.domain.model.Criteria
import org.egorkazantsev.dekonmobile.domain.model.Matrix
import org.egorkazantsev.dekonmobile.domain.model.Model
import org.egorkazantsev.dekonmobile.domain.repository.ModelRepository
import java.util.*

class ModelRepositoryImpl : ModelRepository {

    override fun getModelList(): List<Model> {
        return listOf(
            Model(
                UUID.randomUUID(), "model 2 crt",
                Matrix(
                    UUID.randomUUID(), "main matrix", 2.4,
                    leftElement = Criteria(
                        UUID.randomUUID(), "crt 1",1.9
                    ),
                    rightElement = Criteria(
                        UUID.randomUUID(), "crt 2", 2.1
                    )
                )
            ),
            Model(
                UUID.randomUUID(), "model 1 mtr 3 crt",
                Matrix(
                    UUID.randomUUID(), "main matrix", 2.4,
                    leftElement = Criteria(
                        UUID.randomUUID(), "crt 1",1.9
                    ),
                    rightElement = Matrix(
                        UUID.randomUUID(), "mtr 1", 2.1,
                        leftElement = Criteria(
                            UUID.randomUUID(), "crt 2", 1.1
                        ),
                        rightElement = Criteria(
                            UUID.randomUUID(), "crt 3", 1.4
                        )
                    )
                )
            )
        )
    }
}