package org.egorkazantsev.dekonmobile.domain.model

import java.util.*

data class Matrix(
    override val id: UUID,
    override val name: String,
    override val value: Double,
    val matrixValue: List<List<Int>> =
        List(4) { List(4) { 0 } },
    val leftElement: BaseElement,
    val rightElement: BaseElement
) : BaseElement(id, name, value)