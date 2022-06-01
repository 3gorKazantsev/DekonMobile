package org.egorkazantsev.dekonmobile.domain

import org.egorkazantsev.dekonmobile.domain.model.Criteria
import org.egorkazantsev.dekonmobile.domain.model.Matrix
import org.egorkazantsev.dekonmobile.domain.model.Model
import org.egorkazantsev.dekonmobile.domain.model.Owner
import java.util.*


val testModelExpected =
    Model(
        UUID.fromString("abcefaf2-df49-465e-9afe-83ae7b91af6c"),
        "testModel",
        Owner(UUID.fromString("b2d64a47-33bd-450b-a59d-f53f77f2041e"), "testOwner"),
        Matrix(
            UUID.fromString("90c68d89-65d6-4ffa-afb1-9c235a73a9d1"), "testMatrix", 1.0,
            leftElement = Criteria(
                UUID.fromString("b026f6fa-c834-4612-a3ce-71cad8bc92cb"),
                "testCriteria1",
                1.0
            ),
            rightElement = Criteria(
                UUID.fromString("bba54128-cb4e-41e4-bd26-a678a5062f9a"),
                "testCriteria2",
                1.0
            )
        )
    )


val testModelRepo =
    Model(
        UUID.fromString("abcefaf2-df49-465e-9afe-83ae7b91af6c"),
        "testModel",
        Owner(UUID.fromString("b2d64a47-33bd-450b-a59d-f53f77f2041e"), "testOwner"),
        Matrix(
            UUID.fromString("90c68d89-65d6-4ffa-afb1-9c235a73a9d1"), "testMatrix", 1.0,
            leftElement = Criteria(
                UUID.fromString("b026f6fa-c834-4612-a3ce-71cad8bc92cb"),
                "testCriteria1",
                1.0
            ),
            rightElement = Criteria(
                UUID.fromString("bba54128-cb4e-41e4-bd26-a678a5062f9a"),
                "testCriteria2",
                1.0
            )
        )
    )