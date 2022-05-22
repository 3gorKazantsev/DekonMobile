package org.egorkazantsev.dekonmobile.domain.model

import java.awt.Color
import java.util.*

data class Criteria(
    override val id: UUID,
    override val name: String,
    override val value: Double,
    override val level: Int,
    override val color: Int
): BaseElement(id, name, value, level, color)