package org.egorkazantsev.dekonmobile.domain.model

import java.util.*

open class Criteria(
    val id: UUID,
    val name: String,
    val value: Double,
    val level: Int
) {
    override fun toString(): String {
        return "\n${this.javaClass.simpleName}(id=$id, name=$name, value=$value, level=$level)"
    }
}