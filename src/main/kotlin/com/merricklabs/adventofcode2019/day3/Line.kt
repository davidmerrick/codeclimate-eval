package com.merricklabs.adventofcode2019.day3

class Line(val input: String) {

    val pointSet by lazy { pointList.toSet() }
    val pointList by lazy { computePointList() }

    private fun computePointList(): List<Pair<Int, Int>> {
        val pointList = mutableListOf<Pair<Int, Int>>()

        var x = 0
        var y = 0
        for (delta in input.split(",")) {
            val prevX = x
            val prevY = y

            // Decide on direction
            when (delta[0]) {
                'R' -> x += delta.substring(1).toInt()
                'U' -> y += delta.substring(1).toInt()
                'L' -> x -= delta.substring(1).toInt()
                'D' -> y -= delta.substring(1).toInt()
            }

            val newPairs: List<Pair<Int, Int>>

            // Todo: Decreasing range isn't generating. Need to use "downTo"
            val didChangeX = prevX != x
            newPairs = if (didChangeX) {
                getIntsBetween(prevX, x).map { Pair(it, y) }.toList()
            } else {
                getIntsBetween(prevY, y).map { Pair(x, it) }.toList()
            }

            pointList.addAll(newPairs)
        }

        return pointList
    }

    fun distanceTo(coord: Pair<Int, Int>) = pointList.indexOf(coord) - 1

    companion object {
        private fun getIntsBetween(start: Int, end: Int): List<Int> {
            if (start > end) {
                return (start downTo end).toList()
            }
            return (start..end).toList()
        }
    }
}