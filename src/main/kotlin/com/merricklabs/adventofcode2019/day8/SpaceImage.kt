package com.merricklabs.adventofcode2019.day8

data class SpaceImage(val digits: String, val width: Int, val height: Int) {

    fun getLayers() = digits.split("")
            .filter { it.isNotEmpty() }
            .map { it.toInt() }
            .chunked(width * height)
}