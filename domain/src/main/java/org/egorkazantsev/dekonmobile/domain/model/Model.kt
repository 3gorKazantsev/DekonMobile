package org.egorkazantsev.dekonmobile.domain.model

import java.util.*

data class Model(
    val id: UUID,
    val name: String,
    val matrixRoot: Matrix,
    val maxLevel: Int
)