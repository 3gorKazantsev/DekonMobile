package org.egorkazantsev.dekonmobile.domain.model

import java.awt.Color
import java.util.*

abstract class BaseElement (
    open val id: UUID,
    open val name: String,
    open val value: Double,
    open val level: Int,
    open val color: Int
)