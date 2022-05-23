package org.egorkazantsev.dekonmobile.domain.model

import java.util.*

data class Model(
    val id: UUID,
    val name: String,
    val owner: Owner,
    val root: Matrix
) {
    val maxLevel: Int

    init {
        maxLevel = findMaxLevel()
    }

    private fun findMaxLevel(): Int {
        return root.elements.maxOf { it.level }
    }
}