package org.egorkazantsev.dekonmobile.domain.model

import java.util.*

data class Model(
    val id: UUID,
    val name: String,
    val matrix: Matrix
) {
    val elements: List<BaseElement> = decompose()

    private fun decompose(): List<BaseElement> {
        // TODO как-то разложить матрицу на все элементы
        return emptyList()
    }
}