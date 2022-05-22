package org.egorkazantsev.dekonmobile.domain.model

import java.util.*

abstract class BaseElement (
    open val id: UUID,
    open val name: String,
    open val value: Double
)