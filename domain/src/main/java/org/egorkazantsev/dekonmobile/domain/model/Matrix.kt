package org.egorkazantsev.dekonmobile.domain.model

import java.util.*

class Matrix(
    id: UUID,
    name: String,
    value: Double,
    level: Int,

    val matrixValue: List<List<Int>> =
        List(4) { List(4) { 0 } },
    val leftElement: Criteria,
    val rightElement: Criteria
) : Criteria(id, name, value, level) {

    fun elements(): List<Criteria> {
        val elements = mutableListOf<List<Criteria>>(mutableListOf(this))

        if (leftElement is Matrix)
            elements.add(leftElement.elements())
        else
            elements.add(listOf(leftElement))

        if (rightElement is Matrix)
            elements.add(rightElement.elements())
        else
            elements.add(listOf(rightElement))

        return elements.flatten()
    }

    override fun toString(): String {
        return "\n${this.javaClass.simpleName}(id=$id, name=$name, value=$value, level=$level, matrixValue=$matrixValue, leftElement=${leftElement.javaClass.simpleName}(name=${leftElement.name}), rightElement=${rightElement.javaClass.simpleName}(name=${rightElement.name}))"
    }

}