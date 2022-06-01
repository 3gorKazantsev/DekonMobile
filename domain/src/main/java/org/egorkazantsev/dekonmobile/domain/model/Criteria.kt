package org.egorkazantsev.dekonmobile.domain.model

import java.util.*

open class Criteria(
    val id: UUID,
    val name: String,
    var value: Double
) {
    var level: Int = 0

    override fun toString(): String {
        return "\n${this.javaClass.simpleName}(id=$id, name=$name, value=$value, level=$level)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Criteria

        if (id != other.id) return false
        if (name != other.name) return false
        if (value != other.value) return false
        if (level != other.level) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + value.hashCode()
        result = 31 * result + level
        return result
    }
}