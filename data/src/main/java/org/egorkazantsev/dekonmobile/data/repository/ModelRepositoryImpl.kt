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
                UUID.fromString("e015cd30-f085-49d8-b186-3475d86f1e2d"), "model 2 crt",
                Matrix(
                    UUID.randomUUID(), "main matrix", 2.4, 0,
                    leftElement = Criteria(
                        UUID.randomUUID(), "crt 1", 1.9, 1
                    ),
                    rightElement = Criteria(
                        UUID.randomUUID(), "crt 2", 2.1, 1,
                    )
                ), 1
            ),
            Model(
                UUID.fromString("765ad7c9-05aa-4a71-a76a-003298ad4b81"), "model 1 mtr 3 crt",
                Matrix(
                    UUID.randomUUID(), "main matrix", 2.4, 0,
                    leftElement = Criteria(
                        UUID.randomUUID(), "crt 1", 1.9, 1
                    ),
                    rightElement = Matrix(
                        UUID.randomUUID(), "mtr 1", 2.1, 1,
                        leftElement = Criteria(
                            UUID.randomUUID(), "crt 2", 1.1, 2
                        ),
                        rightElement = Criteria(
                            UUID.randomUUID(), "crt 3", 1.4, 2
                        )
                    )
                ), 2
            )
        )
    }

    override fun getModelById(id: UUID): Model {
        return getModelList()[0]
    }
}