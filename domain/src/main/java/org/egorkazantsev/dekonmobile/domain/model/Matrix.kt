package org.egorkazantsev.dekonmobile.domain.model

import java.util.*

class Matrix(
    id: UUID,
    name: String,
    value: Double,

    val matrixValue: List<List<Int>> =
        List(4) { List(4) { 0 } },
    val leftElement: Criteria,
    val rightElement: Criteria
) : Criteria(id, name, value) {

    var elements: List<Criteria> = emptyList()

    init {
        elements = elements(level)
    }

    fun elements(rootLevel: Int): List<Criteria> {
        val elements = mutableListOf<List<Criteria>>(mutableListOf(this))
        level = rootLevel

        if (leftElement is Matrix) {
            elements.add(leftElement.elements(rootLevel + 1))
        } else {
            elements.add(listOf(leftElement))
            leftElement.level = rootLevel + 1
        }

        if (rightElement is Matrix) {
            elements.add(rightElement.elements(rootLevel + 1))
        } else {
            elements.add(listOf(rightElement))
            rightElement.level = rootLevel + 1
        }

        return elements.flatten()
    }

    override fun toString(): String {
        return "\n${this.javaClass.simpleName}(id=$id, name=$name, value=$value, level=$level, matrixValue=$matrixValue, leftElement=${leftElement.javaClass.simpleName}(name=${leftElement.name}), rightElement=${rightElement.javaClass.simpleName}(name=${rightElement.name}))"
    }
}