package org.egorkazantsev.dekonmobile.data.storage

import org.egorkazantsev.dekonmobile.domain.model.Criteria
import org.egorkazantsev.dekonmobile.domain.model.Matrix
import org.egorkazantsev.dekonmobile.domain.model.Model
import org.egorkazantsev.dekonmobile.domain.model.Owner
import java.util.*

val models = listOf(
    Model(
        UUID.fromString("e015cd30-f085-49d8-b186-3475d86f1e2d"), "model 2 crt",
        Owner(UUID.randomUUID(), "Зарплатин Пупа Лупович"),
        Matrix(
            UUID.randomUUID(), "main matrix", 2.4,
            leftElement = Criteria(
                UUID.randomUUID(), "crt 1", 1.9
            ),
            rightElement = Criteria(
                UUID.randomUUID(), "crt 2", 2.1,
            )
        )
    ),
    Model(
        UUID.fromString("765ad7c9-05aa-4a71-a76a-003298ad4b81"), "model 1 mtr 3 crt",
        Owner(UUID.randomUUID(), "Зубенко Михаил Петрович"),
        Matrix(
            UUID.randomUUID(), "main matrix", 2.4,
            leftElement = Criteria(
                UUID.randomUUID(), "crt 1", 1.9
            ),
            rightElement = Matrix(
                UUID.randomUUID(), "mtr 1", 2.1,
                leftElement = Criteria(
                    UUID.randomUUID(), "crt 2", 1.1,
                ),
                rightElement = Criteria(
                    UUID.randomUUID(), "crt 3", 1.4
                )
            )
        )
    )
)