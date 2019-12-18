package com.merricklabs.adventofcode2019.day3

class Line(val input: String) {

    val pointSet by lazy { computePointSet() }

    private fun computePointSet(): Set<Pair<Int, Int>> {
        val pointSet = mutableSetOf<Pair<Int, Int>>()

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

            var newPairs = mutableListOf<Pair<Int, Int>>()

            // Todo: Decreasing range isn't generating. Need to use "downTo"
            for (i in getRange(prevX, x)) {
                for (j in getRange(prevY, y)) {
                    newPairs.add(Pair(i, j))
                }
            }

            pointSet.addAll(newPairs)
        }

        pointSet.remove(Pair(0, 0))
        return pointSet
    }

    private fun getRange(a: Int, b: Int): IntRange {
        if (a < b) {
            return a..b
        }
        return b..a
    }

}