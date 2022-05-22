package org.egorkazantsev.dekonmobile.domain.model

import java.util.*

data class Criteria(
    override val id: UUID,
    override val name: String,
    override val value: Double
): BaseElement(id, name, value)