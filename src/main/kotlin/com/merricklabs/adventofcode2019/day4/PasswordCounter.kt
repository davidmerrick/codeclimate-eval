package com.merricklabs.adventofcode2019.day4

data class PasswordCounter(val lowerBound: Int, val upperBound: Int) {

    // Todo: Initial implementation is brute-force
    // But I'm thinking a better approach would be to generate
    // a tree of valid values in a range, and count the nodes

    fun count() = (lowerBound..upperBound).count { meetsCriteria(it) }

    companion object {
        fun meetsCriteria(value: Int): Boolean {
            return isIncreasing(value) && hasDouble(value)
        }

        fun isIncreasing(value: Int): Boolean {
            val valList = value.split().toMutableList()
            var prev = valList.removeAt(0)
            for (i in 0 until valList.size) {
                val current = valList.removeAt(0)
                if (current < prev) {
                    return false
                }
                prev = current
            }
            return true
        }

        fun hasDouble(value: Int): Boolean {
            val valList = value.split().toMutableList()
            var prev = valList.removeAt(0)
            for (i in 0 until valList.size) {
                val current = valList.removeAt(0)
                if (current == prev) {
                    return true
                }
                prev = current
            }
            return false
        }
    }
}

fun Int.split(): List<Int> {
    return this.toString()
            .split("")
            .filter { it != "" }
            .map { it.toInt() }
            .toList()
}
